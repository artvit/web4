package servlet;

import servlet.commands.*;
import servlet.constants.Commands;
import servlet.constants.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Controller Servlet
 */
public class ControllerServlet extends HttpServlet {
    private static final String LAST_VISITED_COOKIE_NAME = "lastvisited";
    private static final String VISITS_COOKIE_NAME = "visits";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Commands.APPLICANTS_OF_FACULTY.equals(request.getParameter(Parameters.COMMAND_PARAMETER))) {
            Command command = new ApplicantsForFacultyCommand(request, response);
            command.execute();
        } else if (Commands.APPLICANTS_OVER_AVERAGE_OF_FACULTY.equals(request.getParameter(Parameters.COMMAND_PARAMETER))) {
            Command command = new ApplicantsOverAverageCommand(request, response);
            command.execute();
        } else if (Commands.AVERAGE_FOR_FACULTY.equals(request.getParameter(Parameters.COMMAND_PARAMETER))) {
            Command command = new AverageForFacultyCommand(request, response);
            command.execute();
        } else if (Commands.ADD_APPLICANT.equals(request.getParameter(Parameters.COMMAND_PARAMETER))) {
            Command command = new AddApplicantCommand(request, response);
            command.execute();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        HttpSession session = req.getSession(false);
        if (session == null) {
            Cookie lastVisitedCookie = getCookie(req, LAST_VISITED_COOKIE_NAME);
            if (lastVisitedCookie != null) {
                lastVisitedCookie.setValue(getCurrentDateTime());
            } else {
                lastVisitedCookie = new Cookie(LAST_VISITED_COOKIE_NAME, getCurrentDateTime());
            }
            resp.addCookie(lastVisitedCookie);

            Cookie visitsCookie = getCookie(req, VISITS_COOKIE_NAME);
            if (visitsCookie != null) {
                int visits = Integer.parseInt(visitsCookie.getValue());
                visitsCookie.setValue(Integer.toString(visits + 1));
            } else {
                visitsCookie = new Cookie(VISITS_COOKIE_NAME, "1");
            }
            resp.addCookie(visitsCookie);
        }
        req.getSession();
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
