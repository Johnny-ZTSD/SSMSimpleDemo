package cn.johnnyzen.hiyusite.book.controller;

import cn.johnnyzen.common.pager.Page;
import cn.johnnyzen.common.reuslt.Result;
import cn.johnnyzen.common.reuslt.ResultCode;
import cn.johnnyzen.common.reuslt.ResultUtil;
import cn.johnnyzen.hiyusite.book.domain.Book;
import cn.johnnyzen.hiyusite.book.service.BookService;
import cn.johnnyzen.hiyusite.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:29:56
 * @description: ...
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Qualifier("bookServiceImpl")/*合格器(Qualifier): 避免同名的N个Spring的Bean的冲突*/
    @Autowired
    private BookService bookService;

    @RequestMapping("/findAll.json")
    @ResponseBody
    public List<Book> findAll(){
        LOGGER.info("← 0.0 → ");
        return bookService.findAll();
    }

    /**
     * 用户书单
     *  - mybatis 多对多映射Demo
     *  - 分页Demo
     * @return
     */
    @RequestMapping("/userBookList.json")
    @ResponseBody
    public Result<Page<Book>> userBookList(
            @RequestParam(value = "userId", required = true)Integer userId,
            @RequestParam(value = "curPage", required = true)Integer curPage,
            @RequestParam(value = "pageSize", required = true)Integer pageSize){
        Result result = null;
        try{
            Page<Book> page = null;
            page = bookService.userBookList(userId, curPage, pageSize);
            if(page!=null){
                result = ResultUtil.success("获取用户书单成功！", page);
            } else {
                result = ResultUtil.error(ResultCode.FAIL, "获取用户书单失败!", ResultCode.FAIL.significance);
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
            result = ResultUtil.error(ResultCode.FAIL, "获取用户书单失败!", e.getMessage());
        }
        return result;
    }
}
