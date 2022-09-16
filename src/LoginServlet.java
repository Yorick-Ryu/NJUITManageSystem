import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        System.out.println("name:" + name);
        System.out.println("pwd:" + pwd);
        if ("admin".equals(name) && "123".equals(pwd)) {
            System.out.println("登录成功");
            response.sendRedirect("/index.jsp");
        }else {
            Writer out = response.getWriter();
            out.write("登录失败");
        }
    }
}
