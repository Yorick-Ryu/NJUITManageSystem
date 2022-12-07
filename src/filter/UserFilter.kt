package filter

import entity.User
import java.io.IOException
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

@WebFilter(filterName = "UserFilter", urlPatterns = ["/users.jsp", "/depts.jsp"])
class UserFilter : Filter {
    @Throws(ServletException::class)
    override fun init(config: FilterConfig) {
    }

    override fun destroy() {}

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest
        val user: User? = httpServletRequest.session.getAttribute("user") as User?
        if (user != null) {
            chain.doFilter(request, response)
        } else {
            val out = response.writer
            out.println("当前用户未登录，无法访问")
        }
    }
}