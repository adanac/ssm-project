<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/9
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession(false);
    if(session == null){
        session.invalidate();
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/isOnline" method="post">
        用户名：<input type="text" name="uName"/>
        <input type="submit" value="上线">
    </form>
</body>
</html>
