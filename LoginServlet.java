import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Map<String, String> VALID_USERS = new HashMap<>();
    
    static {
        VALID_USERS.put("student1", "pass1");
        VALID_USERS.put("student2", "pass2");
        VALID_USERS.put("admin", "adminpass");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null && 
            VALID_USERS.containsKey(username) && 
            VALID_USERS.get(username).equals(password)) {
        
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(24 * 60 * 60); // Set cookie to expire in 24 hours
            response.addCookie(userCookie);
         
            response.sendRedirect("DashboardServlet");
        } else {
           
            response.sendRedirect("login.html");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
