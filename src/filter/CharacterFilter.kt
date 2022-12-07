package filter

import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "CharacterFilter")
class CharacterFilter : Filter {
    @Throws(ServletException::class)
    override fun init(config: FilterConfig) {
    }

    override fun destroy() {}

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val request1 = request as HttpServletRequest
        val response1 = response as HttpServletResponse
        //拦截所有请求 解决全站中文乱码
        //指定request和response的编码
        request1.characterEncoding = "utf-8"
        response1.characterEncoding = "utf-8"
        response1.contentType = "text/html;charset=utf-8"
        //对request进行包装
        CharacterRequest(request1)
        chain.doFilter(request, response)
    }
}

internal class CharacterRequest(request: HttpServletRequest) :
    HttpServletRequestWrapper(request) {
    override fun getParameter(name: String): String? {
        var value = super.getParameter(name) ?: return null
        //判断请求方式
        val method = super.getMethod()
        if ("get".equals(method, ignoreCase = true)) {
            value = String(value.toByteArray(StandardCharsets.UTF_8), StandardCharsets.UTF_8)
        }
        //解决乱码后返回结果
        return value
    }
}