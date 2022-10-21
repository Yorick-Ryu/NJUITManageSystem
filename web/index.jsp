<%--
  Created by IntelliJ IDEA.
  User: yurui
  Date: 2022/9/9
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href=css/main.css>
    <title>主页_NJUIT</title>

</head>
<body>
<div id="mainDiv">
    <div id="header">
        <div id="logoDiv" class="lft">
            南京工业职业技术大学
        </div>
        <div id="userDiv" class="rft">
            admin
        </div>
    </div>
    <div id="welcomeDiv">
        欢迎使用南京工业职业技术大学管理系统
    </div>
    <div id="contentDiv">
        <div id="content-left" class="lft">
            <ul>
                <li class="menu">人事管理
                    <ul>
                        <li class="sun-menu"><a href="https://www.baidu.com" id="deptManage" target="contentPage">部门管理</a></li>
                        <li class="sun-menu"><a href="https://www.niit.edu.cn/4052/list.htm" id="personManage" target="contentPage">员工管理</a></li>
                    </ul>
                </li>
                <li class="menu">
                    教务管理
                    <ul>
                        <li class="sun-menu">班级管理</li>
                        <li class="sun-menu">学生管理</li>
                    </ul>
                </li>
                <li class="menu">
                    系统管理
                    <ul>
                        <li class="sun-menu">账户维护</li>
                        <li class="sun-menu">密码重置</li>
                        <li class="sun-menu">系统退出</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div id="content-right" class="rft">
            <iframe name="contentPage" width="795px" height="497px"></iframe>
        </div>
    </div>

    <div id="footer">
        &copy;版权归属南京工业职业技术大学
    </div>
</div>
</body>
</html>
