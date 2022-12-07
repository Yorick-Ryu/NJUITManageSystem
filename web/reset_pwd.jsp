<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/12/7
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>reset_pwd</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
<div class="container">
    <div class="row justify-content-center">
        <form action="${pageContext.request.contextPath}/UserServlet?flag=update&name=<%=user.getName()%>&userName=<%=user.getUserName()%>&dept=<%=user.getDept()%>"
              method="post">
            <div class="mb-3">
                <label for="userName" class="form-label">用户名：</label>
                <input name="userName" type="text" disabled class="form-control" id="userName"
                       value=" <%=user.getUserName()%>">
            </div>
            <div class="mb-3">
                <label for="new_pwd" class="form-label">新密码：</label>
                <input name="pwd" type="password" class="form-control" id="new_pwd">
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">重复密码：</label>
                <input type="password" class="form-control" id="pwd">
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>
    </div>
</div>
</body>
</html>
