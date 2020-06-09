<%@ page language="java" contentType="text/html; UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta content="text/html;charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../static/css/index.css">
        <title>千千寰宇</title>
    </head>
    <body>
        <h1 style="text-align: center">${ indexJSPMessage }</h1>
        <div>
            /webapp/WEB-INF/jsp/index_WEBINF.jsp
        </div>
        <h3>pageContext.servletContext: ${pageContext.servletContext}</h3>
        <h3>contextPath: ${contextPath}</h3>
    </body>
</html>
