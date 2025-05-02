import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                <a href="EnrollServlet?courseId=${course.id}">Enroll</a>
        

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.html");
            return;
        }

        
        request.setAttribute("username", username);

        
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Computer Science", "Dr. Smith"));
        courses.add(new Course("MATH201", "Calculus I", "Prof. Johnson"));
        courses.add(new Course("PHY301", "Physics Fundamentals", "Dr. Allen"));

        request.setAttribute("courses", courses);

        
        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }

        request.setAttribute("enrolledCourses", enrolledCourses);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
