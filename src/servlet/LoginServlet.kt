package servlet

import service.UserService
import service.impl.UserServiceImpl
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "servlet.LoginServlet", value = ["/servlet.LoginServlet"])
class LoginServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.characterEncoding = "utf-8"
        response.contentType = "text/html;charset=utf-8"
        val name = request.getParameter("name")
        val pwd = request.getParameter("pwd")
        val userService: UserService = UserServiceImpl()
        val user = userService.login(name, pwd)
        println("登录成功，ID:${user.id} UserName:${user.userName} Password:${user.pwd}")
        if (user.userName != "") {
            response.sendRedirect(request.contextPath + "/index.jsp")
        } else {
            response.sendRedirect(request.contextPath + "/login.jsp?message=login_error")
        }
    }
}