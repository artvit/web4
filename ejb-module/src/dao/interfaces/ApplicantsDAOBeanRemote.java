package dao.interfaces;

import entities.Applicant;
import entities.Faculty;
import exceptions.DAOException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Remote interface for ApplicantsDAOBean
 */
@Remote
public interface ApplicantsDAOBeanRemote {
    /**
     * Returns list of applicants for specified faculty.
     *
     * @param faculty Faculty object
     * @return List of Applicants
     * @throws DAOException
     */
    List<Applicant> getApplicantsForFaculty(Faculty faculty) throws DAOException;

    /**
     * Sets entered column
     *
     * @param applicant Applicant object
     * @param entered if applicant has entered or not
     * @throws DAOException
     */
    void setEntered(Applicant applicant, boolean entered) throws DAOException;

    /**
     * Returns list of all applicants.
     *
     * @return List of Applicants
     * @throws DAOException
     */
    List<Applicant> getAllApplicants() throws DAOException;

    /**
     * Sets total mark for applicant.
     *
     * @param applicant Applicant object
     * @param total total mark
     * @throws DAOException
     */
    void setTotalForApplicant(Applicant applicant, double total) throws DAOException;

    /**
     * Returns average total mark for specified faculty.
     *
     * @param faculty Faculty object
     * @return average total mark
     * @throws DAOException
     */
    double getAverageForFaculty(Faculty faculty) throws DAOException;

    /**
     * Add new applicant
     *
     * @param applicant Applicant object
     * @param faculty Faculty object
     * @throws DAOException
     */
    void addNewApplicant(Applicant applicant, Faculty faculty) throws DAOException;

    /**
     * Get applicant with specified name.
     *
     * @param applicantName applicant's name
     * @return Applicant object
     * @throws DAOException
     */
    Applicant getApplicant(String applicantName) throws DAOException;
}
