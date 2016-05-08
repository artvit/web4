package servlet.commands;

import dao.interfaces.ApplicantsDAOBeanRemote;
import dao.interfaces.FacultiesDAOBeanRemote;
import dao.interfaces.SheetDAOBeanRemote;
import dao.interfaces.SubjectsDAOBeanRemote;
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

    private ApplicantsDAOBeanRemote applicantsDAO;
    private FacultiesDAOBeanRemote facultiesDAO;
    private SheetDAOBeanRemote sheetDAO;
    private SubjectsDAOBeanRemote subjectsDAO;

    public AverageForFacultyCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public AverageForFacultyCommand(HttpServletRequest request, HttpServletResponse response, ApplicantsDAOBeanRemote applicantsDAO, FacultiesDAOBeanRemote facultiesDAO, SheetDAOBeanRemote sheetDAO, SubjectsDAOBeanRemote subjectsDAO) {
        this.request = request;
        this.response = response;
        this.applicantsDAO = applicantsDAO;
        this.facultiesDAO = facultiesDAO;
        this.sheetDAO = sheetDAO;
        this.subjectsDAO = subjectsDAO;
    }

    @Override
    public void execute() throws ServletException, IOException {
        Administrator administrator = new Administrator(applicantsDAO, facultiesDAO, sheetDAO, subjectsDAO);
        String faculty = request.getParameter(Parameters.FACULTY_PARAMETER);
        double average = administrator.getAverageForFaculty(faculty);
        request.setAttribute(Attributes.RESULT_ATTR, average);
        request.setAttribute(Attributes.FACULTY_ATTR, faculty);
        request.getRequestDispatcher(Pages.AVERAGE_FOR_FACULTY_PAGE).forward(request, response);
    }
}