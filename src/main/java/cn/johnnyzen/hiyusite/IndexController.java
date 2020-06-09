package cn.johnnyzen.hiyusite;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  10:58:40
 * @description: ...
 */
@Controller
@Scope(value="singleton") //只实例化1个bean对象（即每次请求都使用同一bean对象），默认:singleton
public class IndexController extends AbstractController {
    /**
     * 目的: 本方法 是为了使在页面(index_WEBINF)中获取 contextPath
     * 方式: 使IndexController extends AbstractController,并实现其 handleRequestInternal(...)方法
     */
    @RequestMapping("/test")
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        Map<String ,Object> model=new HashMap<String,Object>();
        String contextPath;
        contextPath = request.getScheme() +"://" + request.getServerName()  + ":" +request.getServerPort() +request.getContextPath();
        //Scheme: http ; ServerName: 127.0.0.1 ; ServerPort: 8080 ; ContextPath: /SSMDemo2
        model.put("contextPath", contextPath);
        return new ModelAndView("index_WEBINF", model);
    }

    @RequestMapping(value = {"/", "/index","index.jsp"}, produces ="application/json;charset=UTF-8")
    public String index(HttpServletRequest request){
        request.setAttribute("indexJSPMessage","hello~(This message fron IndexController:index)");
        return "index_WEBINF";// /webapp/WEB-INF/view/index_WEBINF.jsp
    }

    @RequestMapping("/index.mav")
    public ModelAndView indexMAV(){
        ModelAndView mav = new ModelAndView("index_WEBINF");
        mav.addObject("indexJSPMessage", "hello~(This message fron IndexController:indexMAV)");
        return mav;
    }

    @RequestMapping("/index.strModel")
    public String indexStrModel(Model model){
        model.addAttribute("indexJSPMessage", "hello~(This message fron IndexController:index.strModel)");
        return "index_WEBINF";
    }


    @RequestMapping("/index.strMap")
    public String indexStrMap(Map<String,Object> map){
        map.put("indexJSPMessage", "hello~(This message fron IndexController:index.strMap)");
        return "index_WEBINF";
    }

    /**
     * 返回静态页面
     *      避坑点1： 务必加上 @ResponseBody，否则识别不了静态资源(页面)
     * @return
     */
    @RequestMapping("/index.static")
    @ResponseBody
    public String indexStatic(Map<String,Object> map){
        return "index.html"; // /webapp/index.html
    }

    /**
     * 返回字符串/文本
     *      此处 加不加 @ResponseBody 都不影响
     * @return
     */
    @RequestMapping("/index.content")
    @ResponseBody
    public String indexContent(){
        return "indexContent~~~~"; // 文本内容(并非静态资源)
    }

    /**
     * 转发示例 - Java Web的传统方式
     * @return
     */
    @RequestMapping("/indexForward/JAVAWEB")
    public void indexForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.strMap").forward(request, response);
    }

    /**
     * 转发示例 - Spring MVC方式
     * @return
     */
    @RequestMapping("/indexForward/SPRINGMVC")
    public String indexForward() throws ServletException, IOException {
        return "forward:/index.content";
    }

    /**
     * 重定向示例 - Java Web的传统方式
     * @return
     */
    @RequestMapping("/indexRedirect/JAVAWEB")
    public void indexRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.strMap").forward(request, response);
    }

    /**
     * 重定向示例 - Spring MVC方式
     * @return
     */
    @RequestMapping("/indexRedirect/SPRINGMVC")
    public String indexRedirect() throws ServletException, IOException {
        return "forward:/index.content";
    }
}
