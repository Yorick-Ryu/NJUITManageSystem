package servlet

import entity.Department
import service.DeptService
import service.impl.DeptServiceImpl
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "servlet.DeptServlet", value = ["/DeptServlet"])
class DeptServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        doPost(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.characterEncoding = "utf-8"
        response.contentType = "text/html;charset=utf-8"
        val dept = Department()
        dept.apply {
            deptId = request.getParameter("dept_id").let { it?.toInt() ?: 0 }
            deptName = request.getParameter("dept_name") ?: ""
            deptLoc = request.getParameter("dept_loc") ?: ""
            deptMan = request.getParameter("dept_man").let { it?.toInt() ?: 0 }
        }
        val deptService: DeptService = DeptServiceImpl()
        println(dept)
        println(request.getParameter("flag"))
        when (request.getParameter("flag")) {
            "add" -> deptService.addDept(dept)
            "update" -> deptService.updateDept(dept)
            "del" -> deptService.delDept(dept.deptId)
        }
        response.sendRedirect(request.contextPath + "/depts.jsp")
    }
}