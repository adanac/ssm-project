<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/9
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    session=request.getSession();
    session.setAttribute("userName",request.getParameter("uName"));
    response.sendRedirect("/user/showOnline");
%>
</body>
</html>
