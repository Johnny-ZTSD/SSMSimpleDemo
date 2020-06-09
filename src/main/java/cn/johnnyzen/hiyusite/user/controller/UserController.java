package cn.johnnyzen.hiyusite.user.controller;

import cn.johnnyzen.hiyusite.user.domain.User;
import cn.johnnyzen.hiyusite.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: HiyuSite
 * @author: 千千寰宇
 * @date: 2020/6/4  17:07:30
 * @description: ...
 */

@Controller
@RequestMapping(value="/user")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * controller - demo
     * @param Model对象
     *      1 通过给方法添加引用Model对象入参，直接往Model对象添加属性值。
     *      那么哪些类型的入参才能够引用Model对象，有3种类型，分别是
     *          org.springframework.ui.Model
     *          org.springframework.ui.ModelMap
     *          java.util.Map
     *      只要是这些类型的入参，都是指向Model对象的，而且不管定义多少个这些类型的入参都是指向同一个Model对象！
     *      2 Model中存的数据，最终都会存放到HttpServletRequest对象中，页面上可通过HttpServletRequest对象获取数据
     */
    @Autowired
    private UserService userService;
    @RequestMapping(value="/hello")
    public String hello(HttpServletRequest request, Model model){
        model.addAttribute("msg", "你好spring mvc");
        return "index_WEBINF";
    }

    @RequestMapping("/userInfo.json")
    @ResponseBody
    public User userInfo(
            @RequestParam(value = "userId", required = true)Integer userId) throws Exception {
        LOGGER.info("userId:{} - [/userInfo.json]", userId);
        return userService.findUserById(userId);
    }

    @GetMapping("/usersInfo.json")
    @ResponseBody
    public List<Map<String, User>> usersInfo() throws Exception {
        LOGGER.info("[/usersInfo.json]");
        return userService.findAll();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
