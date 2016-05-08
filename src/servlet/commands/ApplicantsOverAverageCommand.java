package servlet.commands;

import entities.Applicant;
import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by artvi on 02/05/2016.
 */
public class ApplicantsOverAverageCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ApplicantsOverAverageCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws ServletException, IOException {
        Administrator administrator = new Administrator();
        String faculty = request.getParameter(Parameters.FACULTY_PARAMETER);
        List<Applicant> applicants = administrator.getApplicantsForFacultyOverAverage(faculty);
        request.setAttribute("results", applicants);
        request.setAttribute("faculty", faculty);
        request.getRequestDispatcher("/pages/applic-over-average.jsp").forward(request, response);
    }
}
