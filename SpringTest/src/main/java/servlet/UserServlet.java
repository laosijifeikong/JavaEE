//package servlet;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import service.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/user") // 可选注解，如果 web.xml 已配置则无需重复
//public class UserServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        // 1. 从 Spring 容器中获取 UserService
//        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        UserService userService = context.getBean(UserService.class);
//
//        // 2. 调用方法
//        resp.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = resp.getWriter()) {
//            out.println("<html><body>");
//            userService.GetUser(); // 控制台输出 "UserDao"
//            out.println("<h1>调用成功！请查看控制台输出</h1>");
//            out.println("</body></html>");
//        }
//    }
//}
