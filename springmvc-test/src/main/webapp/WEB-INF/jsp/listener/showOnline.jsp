<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/9
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.ArrayList" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList showList = (ArrayList) (getServletContext().getAttribute("list"));
    out.print("在线人数 " + showList.size() + "<br>");
    for (int i = 0; i < showList.size(); i++) {
        out.print(showList.get(i) + "在线" + "<br>");
    }
%>
<br>
<a href="/user/login">退出</a>

</body>
</html>
