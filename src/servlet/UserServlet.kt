package servlet

import entity.User
import service.UserService
import service.impl.UserServiceImpl
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "servlet.UserServlet", value = ["/UserServlet"])
class UserServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        doPost(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.characterEncoding = "utf-8"
        response.contentType = "text/html;charset=utf-8"
        val session = request.session
        val user = User()
        user.apply {
            id = request.getParameter("id").let { it?.toInt() ?: 0 }
            name = request.getParameter("name") ?: ""
            pwd = request.getParameter("pwd") ?: ""
            userName = request.getParameter("userName") ?: ""
            dept = request.getParameter("dept").let { it?.toInt() ?: 0 }
        }
        println(user)
        println(request.getParameter("flag"))
        val userService: UserService = UserServiceImpl()
        when (request.getParameter("flag")) {
            "add" -> userService.addUser(user)
            "update" -> userService.updateUser(user)
            "del" -> userService.delUser(user.id)
            "quit" -> session.invalidate()
        }
        response.sendRedirect(request.contextPath + "/users.jsp")
    }
}