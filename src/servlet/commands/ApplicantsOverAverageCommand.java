package servlet.commands;

import entities.Applicant;
import servlet.constants.Attributes;
import servlet.constants.Pages;
import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command process request for displaying applicants for faculty that have total mark over average.
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
        request.setAttribute(Attributes.RESULTS_LIST_ATTR, applicants);
        request.setAttribute(Attributes.FACULTY_ATTR, faculty);
        request.getRequestDispatcher(Pages.APPLICANT_OVER_AVERAGE_PAGE).forward(request, response);
    }
}
