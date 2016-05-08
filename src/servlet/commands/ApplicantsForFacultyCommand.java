package servlet.commands;

import entities.Applicant;
import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        request.setAttribute("results", applicants);
        request.setAttribute("faculty", faculty);
        request.getRequestDispatcher("/pages/applic-of-fac.jsp").forward(request, response);
    }
}
