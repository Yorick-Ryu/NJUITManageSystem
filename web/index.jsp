<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/9/9
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>主页_NJUIT</title>
    <%--    <link rel="stylesheet" href=css/main.css>--%>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://www.niit.edu.cn/_upload/article/images/05/72/3d7c0f7244f591364e5f37e32223/640246aa-8a61-4495-920f-91b4163803f2.jpg"
                 alt="" height="40" class="d-inline-block align-text-bottom">南工管理系统
        </a>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
        %>
        <span class="navbar-text">
            <%=user.getUserName()%>
        </span>
        <button type="button" class="btn btn-outline-primary"
                onclick="window.location.href='/UserServlet?flag=quit' ">退出
        </button>
        <%}%>
    </div>
</nav>
<div class="container">
    <div class="row align-items-center ">
        <div class="col-2 align-self-start">
            <nav id="navbar-example3"
                 class="navbar navbar-light bg-light flex-column align-items-stretch p-3">
                <a class="navbar-brand" href="welcome.jsp" target="contentPage">首页</a>
                <nav class="nav nav-pills flex-column">
                    <a class="nav-link" href="#">人事管理</a>
                    <nav class="nav nav-pills flex-column">
                        <a class="nav-link ms-3 my-1" href="users.jsp"
                           target="contentPage">人员管理</a>
                        <a class="nav-link ms-3 my-1" href="depts.jsp"
                           target="contentPage">部门管理</a>
                    </nav>
                    <a class="nav-link" href="#">教务管理</a>
                    <nav class="nav nav-pills flex-column">
                        <a class="nav-link ms-3 my-1" href="#">班级管理</a>
                        <a class="nav-link ms-3 my-1" href="#">学生管理</a>
                    </nav>
                    <a class="nav-link" href="#">系统管理</a>
                    <nav class="nav nav-pills flex-column">
                        <a class="nav-link ms-3 my-1" href="#">账户管理</a>
                        <a class="nav-link ms-3 my-1" href="reset_pwd.jsp"
                           target="contentPage">密码重置</a>
                        <a class="nav-link ms-3 my-1"
                           href="${pageContext.request.contextPath}/UserServlet?flag=quit">系统退出</a>
                    </nav>
                </nav>
            </nav>
        </div>
        <div id="content-right" class="col-lg-10">
            <iframe name="contentPage" width="800px" height="530px" src="welcome.jsp"></iframe>
        </div>
    </div>

    <div class="row align-items-end justify-content-center">
        <div class="col-4">
            &copy;版权归属南京工业职业技术大学
        </div>
    </div>
</div>
</body>
</html>
