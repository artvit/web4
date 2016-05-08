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
 * Command processes request for displaying applicants for faculty.
 */
public class ApplicantsForFacultyCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ApplicantsForFacultyCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws ServletException, IOException{
        Administrator administrator = new Administrator();
        String faculty = request.getParameter(Parameters.FACULTY_PARAMETER);
        List<Applicant> applicants = administrator.getApplicantsForFaculty(faculty);
        request.setAttribute(Attributes.RESULTS_LIST_ATTR, applicants);
        request.setAttribute(Attributes.FACULTY_ATTR, faculty);
        request.getRequestDispatcher(Pages.APPLICATS_FOR_FACULTY_PAGE).forward(request, response);
    }
}
