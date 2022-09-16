<%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/9/9
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href=css/styles.css>
    <title>登录_NJUIT</title>
</head>
<body>
<div class="container_form">
    <div class="title">
        NJUIT管理系统
    </div>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post" id="login">
        <div class="input">
            <label for="name">用户名：</label>
            <input type="text" id="name" name="name">
        </div>
        <div class="input">
            <label for="pwd">密码：</label>
            <input type="password" id="pwd" name="pwd">
        </div>
    </form>
    <div class="btn">
        <button type="submit" form="login">提交</button>
        <button type="reset" form="login">清空</button>
    </div>

</div>
</body>
</html>
