package utils;

import entities.Applicant;
import entities.Faculty;
import entities.Subject;
import exceptions.AdministratorException;
import exceptions.DAOException;
import dao.interfaces.ApplicantsDAOBeanRemote;
import dao.interfaces.FacultiesDAOBeanRemote;
import dao.interfaces.SheetDAOBeanRemote;
import dao.interfaces.SubjectsDAOBeanRemote;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class for executing complicated queries.
 */
public class Administrator {

    private ApplicantsDAOBeanRemote applicantsDAO;
    private FacultiesDAOBeanRemote facultiesDAO;
    private SheetDAOBeanRemote sheetDAO;
    private SubjectsDAOBeanRemote subjectsDAO;

    public Administrator(ApplicantsDAOBeanRemote applicantsDAO, FacultiesDAOBeanRemote facultiesDAO, SheetDAOBeanRemote sheetDAO, SubjectsDAOBeanRemote subjectsDAO) {
        this.applicantsDAO = applicantsDAO;
        this.facultiesDAO = facultiesDAO;
        this.sheetDAO = sheetDAO;
        this.subjectsDAO = subjectsDAO;
    }

    /**
     * Set marks for all applicants.
     */
    public void rateApplicants() throws AdministratorException {
        try {
            Random random = new Random();
            List<Applicant> applicants = applicantsDAO.getAllApplicants();
            for (Applicant applicant : applicants) {
                List<Subject> subjects = subjectsDAO.getSubjectsForApplicant(applicant);
                for (Subject subject : subjects) {
                    sheetDAO.setMark(applicant, subject, random.nextInt(11));
                }
            }
        } catch (DAOException e){
            throw new AdministratorException("Can't rate applicants");
        }
    }

    /**
     * Counts total mark for each applicant
     */
    public void countTotal() throws AdministratorException {
        try {
            List<Applicant> applicants = applicantsDAO.getAllApplicants();
            for (Applicant applicant : applicants) {
                countTotal(applicant);
            }
        } catch (DAOException e){
            throw new AdministratorException("Can't count total marks for applicants");
        }
    }

    /**
     * Counts total mark for Applicant
     * @param applicant Applicant object
     */
    public void countTotal(Applicant applicant) throws AdministratorException {
        try {
            List<Subject> subjects = subjectsDAO.getSubjectsForApplicant(applicant);
            double total = 0;
            for (Subject subject : subjects) {
                total += sheetDAO.getMark(applicant, subject);
            }
            total /= subjects.size();
            applicantsDAO.setTotalForApplicant(applicant, total);
        } catch (DAOException e){
            throw new AdministratorException("Can't count total for applicant " + applicant.getName());
        }
    }

    /**
     * Take applicants if they good enough
     */
    public void takeApplicants() throws AdministratorException {
        try {
            List<Faculty> faculties = facultiesDAO.getAllFaculties();
            for (Faculty faculty : faculties) {
                int plan = faculty.getPlan();
                List<Applicant> applicants = applicantsDAO.getApplicantsForFaculty(faculty);
                for(int i = 0; i < Math.min(plan, applicants.size()); ++i) {
                    applicantsDAO.setEntered(applicants.get(i), true);
                }
            }
        } catch (DAOException e) {
            throw new AdministratorException("Can't take applicants to faculties");
        }
    }

    /**
     * Does all initial jobs.
     */
    public void exams() throws AdministratorException {
        rateApplicants();
        countTotal();
        takeApplicants();
    }

    /**
     * Returns all applicants for specified faculty
     *
     * @param facultyName faculty name
     * @return list of Applicant objects
     */
    public List<Applicant> getApplicantsForFaculty(String facultyName) throws AdministratorException {
        List<Applicant> result = null;
        try {
            Faculty faculty = facultiesDAO.getFaculty(facultyName);
            result =  applicantsDAO.getApplicantsForFaculty(faculty);
        } catch (DAOException e){
            throw new AdministratorException("Can't get applicants for faculty " + facultyName);
        }
        return result;
    }

    /**
     * Returns average total mark for specified faculty
     *
     * @param facultyName name of faculty
     * @return average on faculty
     */
    public double getAverageForFaculty(String facultyName) throws AdministratorException {
        double result = 0;
        try {
            Faculty faculty = facultiesDAO.getFaculty(facultyName);
            result = applicantsDAO.getAverageForFaculty(faculty);
        } catch (DAOException e){
            throw new AdministratorException("Can't get average mark for faculty " + facultyName);
        }
        return result;
    }

    /**
     * Returns list of Applicants that have total mark over average on faculty
     */
    public List<Applicant> getApplicantsForFacultyOverAverage(String facultyName) throws AdministratorException {
        List<Applicant> result = null;
        try {
            Faculty faculty = facultiesDAO.getFaculty(facultyName);
            List<Applicant> applicantsFromFaculty = applicantsDAO.getApplicantsForFaculty(faculty);
            double average = applicantsDAO.getAverageForFaculty(faculty);
            result = new LinkedList<>();
            for (Applicant applicant : applicantsFromFaculty) {
                if (applicant.getTotal() >= average) {
                    result.add(applicant);
                }
            }
        } catch (DAOException e){
            throw new AdministratorException("Can't get applicants with total mark over average for faculty " + facultyName);
        }
        return result;
    }

    public void addNewApplicant(String applicantName, String facultyName) throws AdministratorException {
        try {
            Faculty faculty = facultiesDAO.getFaculty(facultyName);
            Applicant applicant =  new Applicant();
            applicant.setName(applicantName);
            applicantsDAO.addNewApplicant(applicant, faculty);
        } catch (DAOException e){
            throw new AdministratorException("Can't add applicant " + applicantName +" to faculty " + facultyName);
        }
    }

    /**
     * Adds new mark for applicant and subject.
     * Recounts total mark for specified applicant.
     *
     * @param applicantName applicant's name
     * @param subjectName subject's name
     * @param mark mark
     */
    public void addNewMark(String applicantName, String subjectName, double mark) throws AdministratorException {
        try {
            Applicant applicant = applicantsDAO.getApplicant(applicantName);
            Subject subject = subjectsDAO.getSubject(subjectName);
            sheetDAO.newMark(applicant, subject, mark);
            countTotal(applicant);
            takeApplicants();
        } catch (DAOException e){
            throw new AdministratorException("Can't add mark for applicant " + applicantName + " for subject " + subjectName);
        }
    }

    public void close() {
        EMFS.closeFactory();
    }
}
