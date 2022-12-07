<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<%@ page import="service.impl.UserServiceImpl" %>
<%@ page import="util.SplitPage" %>
<%@ page import="service.impl.DeptServiceImpl" %>
<%@ page import="service.DeptService" %>
<%@ page import="entity.Department" %>
<%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/11/30
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
    <%--    <link rel="stylesheet" href=css/users.css>--%>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</head>
<body>
<jsp:useBean id="spage" class="util.SplitPage" scope="session">

</jsp:useBean>

<%
    UserService userService = new UserServiceImpl();
    String flag = request.getParameter("flag");
    int totalRows;
    DeptService deptService = new DeptServiceImpl();
    List<Department> depts = deptService.queryAllDept();
%>
<%--面包屑导航--%>
<nav aria-label="Breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="welcome.jsp">首页</a></li>
        <li class="breadcrumb-item active" aria-current="page">人员管理</li>
    </ol>
</nav>
<div class="row">
    <div class="col">
        <%--增加用户--%>
        <button class="btn btn-primary btn-sm" data-bs-toggle="modal"
                data-bs-target="#addUser">
            添加用户
        </button>
    </div>
    <div class="col">
        <%--搜索用户--%>
        <form method="post" action="users.jsp?flag=first">
            <label>
                <select name="dept" class="form-select form-select-sm">
                    <%
                        for (Department dept : depts) {
                    %>
                    <option value="<%=dept.getDeptId()%>"><%=dept.getDeptName()%>
                    </option>
                    <%}%>
                </select>
            </label>
            <label>
                <% String p = request.getParameter("name");
                    String content = p == null || p.equals("") || p.equals("null") ? "" : request.getParameter("name"); %>
                <input class="form-control form-control-sm" type="text" name="name"
                       value="<%=content%>">
            </label>
            <input class="btn btn-primary btn-sm" type="submit" value="搜索">
        </form>
    </div>
</div>

<div class="modal fade" id="addUser" tabindex="-1"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">添加用户</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="add"
                      action="${pageContext.request.contextPath}/UserServlet?flag=add"
                      method="post">
                    <div class="mb-2">
                        <label for="add_user_name"
                               class="form-label">用户名：</label>
                        <input type="text" class="form-control" id="add_user_name"
                               name="userName" value="">
                    </div>
                    <div class="mb-2">
                        <label for="add_pwd" class="form-label">密码：</label>
                        <input class="form-control" id="add_pwd"
                               name="pwd" value="">

                    </div>
                    <div class="mb-2">
                        <label for="add_name" class="form-label">姓名：</label>
                        <input class="form-control" id="add_name"
                               name="name" value="">

                    </div>
                    <div class="mb-2">
                        <label for="add_dept" class="form-label">部门：</label>
                        <select id="add_dept" name="dept" class="form-select"
                                aria-label="Default select example">
                            <%
                                for (Department dept : depts) {
                            %>
                            <option value="<%=dept.getDeptId()%>"><%=dept.getDeptName()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">取消
                </button>
                <button form="add" type="submit"
                        class="btn btn-primary">提交
                </button>
            </div>
        </div>
    </div>
</div>


<table class="table">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>部门</th>
        <th>姓名</th>
        <th>操作</th>
    </tr>
    <%
        request.setCharacterEncoding("utf-8");
        String deptID = request.getParameter("dept");
        String name = request.getParameter("name");
        List<User> users;
        totalRows = userService.getRows(deptID, name);
        spage.setTotalRows(totalRows);
        System.out.println("flag" + flag);
        spage.confirmPage(flag);
        if (name == null || name.equals("") || name.equals("null")) {
            users = userService.queryUsers(spage);
        } else {
            users = userService.queryUserByLike(deptID, name, spage);
        }
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getUserName()%>
        </td>
        <td><%=user.getPwd()%>
        </td>
        <td><%=deptService.queryDept(user.getDept()).getDeptName()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td>
            <button class="btn btn-primary btn-sm" data-bs-toggle="modal"
                    data-bs-target="#exampleModal<%=user.getId()%>">删除
            </button>
            <div class="modal fade" id="exampleModal<%=user.getId()%>" tabindex="-1"
                 aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">删除用户</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            确定删除此用户吗？
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                取消
                            </button>
                            <button type="button" class="btn btn-primary"
                                    onclick="window.location.href='/UserServlet?flag=del&id=<%=user.getId()%>'">
                                确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary btn-sm" data-bs-toggle="modal"
                    data-bs-target="#exampleModalUP<%=user.getId()%>">
                修改
            </button>
            <!-- Modal -->
            <div class="modal fade" id="exampleModalUP<%=user.getId()%>" tabindex="-1"
                 aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabelUP">修改用户信息</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="update<%=user.getId()%>"
                                  action="${pageContext.request.contextPath}/UserServlet?flag=update"
                                  method="post">
                                <div class="mb-2">
                                    <label for="recipient-name"
                                           class="form-label">用户名：</label>
                                    <input type="text" class="form-control" id="recipient-name"
                                           name="userName" value="<%=user.getUserName()%>">
                                </div>
                                <div class="mb-2">
                                    <label for="pwd" class="form-label">密码：</label>
                                    <input class="form-control" id="pwd"
                                           name="pwd" value="<%=user.getPwd()%>">

                                </div>
                                <div class="mb-2">
                                    <label for="name" class="form-label">姓名：</label>
                                    <input class="form-control" id="name"
                                           name="name" value="<%=user.getName()%>">
                                </div>
                                <div class="mb-2">
                                    <label for="dept" class="form-label">部门：</label>
                                    <select id="dept" name="dept" class="form-select"
                                            aria-label="Default select example">
                                        <option value="<%=deptService.queryDept(user.getDept()).getDeptId()%>"
                                                selected><%=deptService.queryDept(user.getDept()).getDeptName()%>
                                        </option>
                                        <%
                                            for (Department dept : depts) {
                                        %>
                                        <option value="<%=dept.getDeptId()%>"><%=dept.getDeptName()%>
                                        </option>
                                        <%}%>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">取消
                            </button>
                            <button form="update<%=user.getId()%>" type="submit"
                                    class="btn btn-primary">提交
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    <%}%>
    <%--页码--%>
    <tr>
        <td colspan="6">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link"
                                             href="users.jsp?flag=<%=SplitPage.FIRSTPAGE%>&dept=<%=deptID%>&name=<%=name%>">首页</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="users.jsp?flag=<%=SplitPage.PREVIOUSEPAGE%>&dept=<%=deptID%>&name=<%=name%>"><span
                            aria-hidden="true">&laquo;</span></a>
                    </li>
                    <li class="page-item">
                        <a class="page-link"
                           href="users.jsp?flag=<%=SplitPage.PREVIOUSEPAGE%>&dept=<%=deptID%>&name=<%=name%>"><%=(spage.getCurrentPage() - 1)%>
                        </a>
                    </li>
                    <li class="page-item active">
                        <a class="page-link" href="#"><%=spage.getCurrentPage()%>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link"
                           href="users.jsp?flag=<%=SplitPage.NEXTPAGE%>&dept=<%=deptID%>&name=<%=name%>"><%=(spage.getCurrentPage() + 1)%>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="users.jsp?flag=<%=SplitPage.NEXTPAGE%>&dept=<%=deptID%>&name=<%=name%>"><span
                            aria-hidden="true">&raquo;</span></a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="users.jsp?flag=<%=SplitPage.LASTPAGE%>&dept=<%=deptID%>&name=<%=name%>">尾页</a>
                    </li>
                </ul>
            </nav>
        </td>
    </tr>
</table>
</body>
</html>
