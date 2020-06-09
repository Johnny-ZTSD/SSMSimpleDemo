package cn.johnnyzen.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @IDE: Created by IntelliJ IDEA.
 * @Author: 千千寰宇
 * @Date: 2018/10/7  17:00:46
 * @modifyDate: 2020-06-06 16:37 - zengtai(更换日志器及打日志的方法)
 * @Description: 跨域过滤器
 * @Reference
 *      [1] 跨域资源共享  CORS 详解 http://www.ruanyifeng.com/blog/2016/04/cors.html
 *      [2] 跨域问题小结[推荐]      https://www.cnblogs.com/johnnyzen/p/9930295.html
 */

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)//控制过滤器的级别
public class CosFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CosFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("[CosFilter.init()] ...");
    }

    /**
     * 跨域过滤器执行方法
     * @param req
     * @param res
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest reqs = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", reqs.getHeader("Origin"));//也可是 *，但不是太安全
        LOGGER.info("request.header.Origin:" + reqs.getHeader("Origin"));//log for cos
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否允许发送Cookie;默认情况下，Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization, token, Origin");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.addHeader("JSESSIONIDCOSFILTER", ((HttpServletRequest) req).getSession().getId());
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            LOGGER.info("PASS/SUCCESS - COSFILTER");
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}