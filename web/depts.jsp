<%@ page import="service.DeptService" %>
<%@ page import="service.impl.DeptServiceImpl" %>
<%@ page import="entity.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page import="service.impl.UserServiceImpl" %>
<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/12/2
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dept</title>
    <%--    <link rel="stylesheet" href=css/users.css>--%>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</head>
<body>
<%
    DeptService deptService = new DeptServiceImpl();
    UserService userService = new UserServiceImpl();
    List<User> users = userService.queryAllUser();
%>
<%--面包屑导航--%>
<nav aria-label="Breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="welcome.jsp">首页</a></li>
        <li class="breadcrumb-item active" aria-current="page">部门管理</li>
    </ol>
</nav>
<%--增加用户--%>
<button class="btn btn-primary btn-sm" data-bs-toggle="modal"
        data-bs-target="#addDept">
    添加部门
</button>
<div class="modal fade" id="addDept" tabindex="-1"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">添加部门</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="add"
                      action="${pageContext.request.contextPath}/DeptServlet?flag=add"
                      method="post">
                    <div class="mb-2">
                        <label for="add_dept_name"
                               class="form-label">部门名称：</label>
                        <input type="text" class="form-control" id="add_dept_name"
                               name="dept_name" value="">
                    </div>
                    <div class="mb-2">
                        <label for="add_dept_loc" class="form-label">部门地点：</label>
                        <input class="form-control" id="add_dept_loc"
                               name="dept_loc" value="">
                    </div>
                    <div class="mb-2">
                        <label for="add_dept_man" class="form-label">部门主管：</label>
                        <select id="add_dept_man" name="dept_man" class="form-select"
                                aria-label="Default select example">
                            <%
                                for (User user : users) {
                            %>
                            <option value="<%=user.getId()%>"><%=user.getName()%>
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
        <th>部门名称</th>
        <th>部门地点</th>
        <th>部门主管</th>
        <th>操作</th>
    </tr>
    <%
        request.setCharacterEncoding("utf-8");
        List<Department> departmentList = deptService.queryAllDept();
        for (Department dept : departmentList) {
    %>
    <tr>
        <td><%=dept.getDeptId()%>
        </td>
        <td><%=dept.getDeptName()%>
        </td>
        <td><%=dept.getDeptLoc()%>
        </td>
        <%
            User man = userService.queryUserByID(dept.getDeptMan());
        %>
        <td><%=man.getName()%>
        </td>
        <td>
            <button class="btn btn-primary btn-sm" data-bs-toggle="modal"
                    data-bs-target="#exampleModal<%=dept.getDeptId()%>">删除
            </button>
            <div class="modal fade" id="exampleModal<%=dept.getDeptId()%>" tabindex="-1"
                 aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">删除部门</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            确定删除此部门吗？
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                取消
                            </button>
                            <button type="button" class="btn btn-primary"
                                    onclick="window.location.href='/DeptServlet?flag=del&dept_id=<%=dept.getDeptId()%>'">
                                确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary btn-sm" data-bs-toggle="modal"
                    data-bs-target="#dept<%=dept.getDeptId()%>">
                修改
            </button>
            <div class="modal fade" id="dept<%=dept.getDeptId()%>" tabindex="-1"
                 aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">修改部门信息</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="update<%=dept.getDeptId()%>"
                                  action="${pageContext.request.contextPath}/DeptServlet?flag=update&dept_id=<%=dept.getDeptId()%>"
                                  method="post">
                                <div class="mb-2">
                                    <label for="dept_name"
                                           class="form-label">部门名称：</label>
                                    <input type="text" class="form-control" id="dept_name"
                                           name="dept_name" value="<%=dept.getDeptName()%>">
                                </div>
                                <div class="mb-2">
                                    <label for="dept_loc" class="form-label">部门地点：</label>
                                    <input class="form-control" id="dept_loc"
                                           name="dept_loc" value="<%=dept.getDeptLoc()%>">
                                </div>
                                <div class="mb-2">
                                    <label for="dept_man" class="form-label">部门主管：</label>
                                    <select id="dept_man" name="dept_man" class="form-select"
                                            aria-label="Default select example">
                                        <option value="<%=userService.queryUserByID(dept.getDeptMan()).getId()%>"
                                                selected><%=userService.queryUserByID(dept.getDeptMan()).getName()%>
                                        </option>
                                        <%
                                            for (User user : users) {
                                        %>
                                        <option value="<%=user.getId()%>"><%=user.getName()%>
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
                            <button form="update<%=dept.getDeptId()%>" type="submit"
                                    class="btn btn-primary">提交
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
