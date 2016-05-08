package servlet;

import servlet.commands.*;
import servlet.constants.Commands;
import servlet.constants.Pages;
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

    /**
     * Method determines which command must be executed and creates and executes that one.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Method determines if there is session with user and if updates cookies and no creates one.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);
        HttpSession session = request.getSession(false);
        if (session == null) {
            Cookie lastVisitedCookie = getCookie(request, LAST_VISITED_COOKIE_NAME);
            if (lastVisitedCookie != null) {
                lastVisitedCookie.setValue(getCurrentDateTime());
            } else {
                lastVisitedCookie = new Cookie(LAST_VISITED_COOKIE_NAME, getCurrentDateTime());
            }
            response.addCookie(lastVisitedCookie);

            Cookie visitsCookie = getCookie(request, VISITS_COOKIE_NAME);
            if (visitsCookie != null) {
                int visits = Integer.parseInt(visitsCookie.getValue());
                visitsCookie.setValue(Integer.toString(visits + 1));
            } else {
                visitsCookie = new Cookie(VISITS_COOKIE_NAME, "1");
            }
            response.addCookie(visitsCookie);
        }
        request.getSession();
        request.getRequestDispatcher(Pages.INDEX_PAGE).forward(request, response);
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
