package servlet.commands;

import dao.interfaces.ApplicantsDAOBeanRemote;
import dao.interfaces.FacultiesDAOBeanRemote;
import dao.interfaces.SheetDAOBeanRemote;
import dao.interfaces.SubjectsDAOBeanRemote;
import servlet.constants.Pages;
import servlet.constants.Parameters;
import utils.Administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command processes request for adding applicant
 */
public class AddApplicantCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private ApplicantsDAOBeanRemote applicantsDAO;
    private FacultiesDAOBeanRemote facultiesDAO;
    private SheetDAOBeanRemote sheetDAO;
    private SubjectsDAOBeanRemote subjectsDAO;

    public AddApplicantCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public AddApplicantCommand(HttpServletRequest request, HttpServletResponse response, ApplicantsDAOBeanRemote applicantsDAO, FacultiesDAOBeanRemote facultiesDAO, SheetDAOBeanRemote sheetDAO, SubjectsDAOBeanRemote subjectsDAO) {
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
            request.getRequestDispatcher(Pages.ADD_APPLICAT_SUCCESS_PAGE).forward(request, response);
        }
    }
}
