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
        if (name == null || "".equals(name)) {
            System.out.println("用户名不为空");
            return;
        }
        if (pwd == null || "".equals(pwd)) {
            System.out.println("密码不为空");
            return;
        }
        if ("admin".equals(name) && "123".equals(pwd)) {
            System.out.println("登录成功");
            // 重定向
            response.sendRedirect("/index.jsp");
            // 请求转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            Writer out = response.getWriter();
            out.write("登录失败");
        }
    }
}
