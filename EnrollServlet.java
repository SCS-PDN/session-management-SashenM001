import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String courseId = request.getParameter("courseId");

        Course selected = null;
        List<Course> allCourses = getHardcodedCourses();
        for (Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                selected = c;
                break;
            }
        }

        if (selected != null) {
            HttpSession session = request.getSession();
            List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolled == null) enrolled = new ArrayList<>();
            if (!enrolled.contains(selected)) enrolled.add(selected);
            session.setAttribute("enrolledCourses", enrolled);
        }

        response.sendRedirect("DashboardServlet");
    }

    private List<Course> getHardcodedCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to CS", "Dr. Smith"));
        courses.add(new Course("CS102", "Data Structures", "Prof. Lee"));
        courses.add(new Course("CS103", "Operating Systems", "Dr. Adams"));
        return courses;
    }
}
