package cn.johnnyzen.hiyusite;

import cn.johnnyzen.common.exception.BusinessException;
import cn.johnnyzen.common.reuslt.Result;
import cn.johnnyzen.common.reuslt.ResultCode;
import cn.johnnyzen.common.reuslt.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @project: SSMDemo
 * @author: 千千寰宇
 * @date: 2020/6/9  20:06:21
 * @description: ...
 */

@ControllerAdvice//增强版的controller
@ResponseBody
public class WebExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return ResultUtil.error(ResultCode.FAIL, null,"could not resolve message "+e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return ResultUtil.error(ResultCode.FAIL, null,"request method not supported "+e.getMessage());
    }

    /**
     * 415 - Unsupported Media Type [检查型异常]
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return ResultUtil.error(ResultCode.FAIL, null,"content type not supported "+e.getMessage());
    }

    /**
     * 捕获了: RuntimeException / NullPointerException 等非检查型异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exceptionHandler(RuntimeException e)
    {
        ModelAndView mv = new ModelAndView( new MappingJackson2JsonView() );
        mv.addObject( "success", false );
        mv.addObject( "message", "请 求 发 生 了 异 常，请 后 再 试 "+e.getMessage());
        return(mv);
    }

    /**
     * 500 - Internal Server Error
     * 捕获了: SQLException / IOException 等检查型异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        if (e instanceof BusinessException){
            return ResultUtil.error(ResultCode.SERVER_INTERNAL_ERROR, null,"BUSINESS ERROR "+e.getMessage());
        }
        logger.error("服务运行异常", e);
        e.printStackTrace();
        return ResultUtil.error(ResultCode.SERVER_INTERNAL_ERROR, null,"SERVER ERROR "+e.getMessage());
    }

    /**
     * 如果 @ExceptionHandler 注解中未声明要处理的异常类型，则默认为参数列表中的异常类型。
     */
/*  @ExceptionHandler()
    @ResponseBody
    String handleException(Exception e){
        return "Exception Deal! " + e.getMessage();
    }  */
}
