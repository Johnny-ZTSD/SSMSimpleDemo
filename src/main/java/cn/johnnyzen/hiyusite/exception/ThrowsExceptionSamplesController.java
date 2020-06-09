package cn.johnnyzen.hiyusite.exception;

import cn.johnnyzen.common.exception.BusinessException;
import cn.johnnyzen.common.reuslt.Result;
import cn.johnnyzen.common.reuslt.ResultCode;
import cn.johnnyzen.common.reuslt.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @project: SSMDemo
 * @author: 千千寰宇
 * @date: 2020/6/9  20:30:28
 * @description: 为了试验各种异常而故意制造异常的控制器
 */
@Controller
@RequestMapping("/Exception")
public class ThrowsExceptionSamplesController {
    @RequestMapping("/IOException.json")
    public Result IOException() throws IOException {
        throw new IOException("IO异常!");
    }

    @RequestMapping("/SQLException.json")
    public Result SQLException() throws SQLException {
        throw new SQLException("数据库异常!");
    }

    @RequestMapping("/HttpMessageNotReadableException.json")
    public Result HttpMessageNotReadableException() throws SQLException {
        throw new SQLException("0.0~~HttpMessageNotReadableException.json~~0.0");
    }

    @RequestMapping("/HttpMediaTypeNotSupportedException.json")
    public Result HttpMediaTypeNotSupportedException() throws SQLException {
        throw new SQLException("0.0~~HttpMediaTypeNotSupportedException.json~~0.0");
    }

    @RequestMapping("/RuntimeException.json")
    public Result runtimeException(){
        throw new RuntimeException("运行时异常!");
    }

    @RequestMapping("/NullPointerException.json")
    public Result NullPointerException(){
        throw new NullPointerException("空指针异常!");
    }

    @RequestMapping("/BusinessException.json")
    public Result BusinessException(){
        throw new BusinessException("自定义业务异常!");
    }

    @RequestMapping("/CatchBusinessException.json")
    public Result CatchBusinessException(){
        Result result = null;
        try{
            throw new BusinessException("自定义业务异常!");
        }catch(Exception e){
            result = ResultUtil.error(ResultCode.FAIL, "服务器故障，请稍后再试！","业务异常啦（CatchBusinessException.json）");
        }
        return result;
    }
}
