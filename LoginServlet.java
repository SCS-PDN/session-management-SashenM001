import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    // Hardcoded user credentials (username -> password)
    private static final Map<String, String> VALID_USERS = new HashMap<>();
    
    static {
        // Initialize with some sample users
        VALID_USERS.put("student1", "pass1");
        VALID_USERS.put("student2", "pass2");
        VALID_USERS.put("admin", "adminpass");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 2. Validate credentials (hardcode a few users)
        if (username != null && password != null && 
            VALID_USERS.containsKey(username) && 
            VALID_USERS.get(username).equals(password)) {
            
            // 3. If valid:
            //    - Create session
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            //    - Store username in cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(24 * 60 * 60); // Set cookie to expire in 24 hours
            response.addCookie(userCookie);
            
            //    - Redirect to DashboardServlet
            response.sendRedirect("DashboardServlet");
        } else {
            // 4. If invalid, redirect back to login.html
            response.sendRedirect("login.html");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect to login page if accessed directly via GET
        response.sendRedirect("login.html");
    }
}
