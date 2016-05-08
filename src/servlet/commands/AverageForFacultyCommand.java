package servlet.commands;

import servlet.constants.Attributes;
import servlet.constants.Pages;
import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command processes request for displaying average mark for faculty.
 */
public class AverageForFacultyCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public AverageForFacultyCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws ServletException, IOException {
        Administrator administrator = new Administrator();
        String faculty = request.getParameter(Parameters.FACULTY_PARAMETER);
        double average = administrator.getAverageForFaculty(faculty);
        request.setAttribute(Attributes.RESULT_ATTR, average);
        request.setAttribute(Attributes.FACULTY_ATTR, faculty);
        request.getRequestDispatcher(Pages.AVERAGE_FOR_FACULTY_PAGE).forward(request, response);
    }
}