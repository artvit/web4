package servlet.commands;

import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by artvi on 02/05/2016.
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
        request.setAttribute("result", average);
        request.setAttribute("faculty", faculty);
        request.getRequestDispatcher("/pages/average.jsp").forward(request, response);
    }
}