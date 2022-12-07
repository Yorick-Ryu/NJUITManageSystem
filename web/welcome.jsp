<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/12/2
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href=css/welcome.css>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    } else {
%>
<div id="welcomeDiv">
    <h3>欢迎<%=user.getName()%>
    </h3>
</div>
<%}%>
</body>
</html>
