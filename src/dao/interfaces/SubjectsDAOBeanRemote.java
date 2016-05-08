package dao.interfaces;

import entities.Applicant;
import entities.Subject;
import exceptions.DAOException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Remote interface for SubjectsDAOBean
 */
@Remote
public interface SubjectsDAOBeanRemote {
    /**
     * Returns subject for specified subject's id
     *
     * @param id id of Subject
     * @return Subject object
     * @throws DAOException
     */
    Subject getSubject(int id) throws DAOException;

    /**
     * Get subject by specified name of subject
     *
     * @param subject name of subject
     * @return subject object
     */
    Subject getSubject(String subject) throws DAOException;

    /**
     * Returns list of subjects for specified Applicant
     *
     * @param applicant Applicant object
     * @return List of Subjects
     * @throws DAOException
     */
    List<Subject> getSubjectsForApplicant(Applicant applicant) throws DAOException;
}
