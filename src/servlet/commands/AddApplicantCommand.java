package servlet.commands;

import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that process request for adding applicant
 */
public class AddApplicantCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddApplicantCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws ServletException, IOException {
        Administrator administrator = new Administrator();
        String name = request.getParameter(Parameters.NAME_PARAMETER);
        String faculty = request.getParameter(Parameters.FACULTY_PARAMETER);
        if (!"".equals(name) && !"".equals(faculty)) {
            administrator.addNewApplicant(name, faculty);
            int i = 0;
            while (request.getParameter(Parameters.SUBJECT_PARAMETER + i) != null) {
                String subject = request.getParameter(Parameters.SUBJECT_PARAMETER + i);
                double mark = Double.parseDouble(request.getParameter(Parameters.MARK_PARAMETER + i));
                administrator.addNewMark(name, subject, mark);
                i += 1;
            }
            request.getRequestDispatcher("/pages/add-applic-success.jsp").forward(request, response);
        }
    }
}
