package dao.interfaces;

import entities.Applicant;
import entities.Subject;
import exceptions.DAOException;

import javax.ejb.Remote;

/**
 * Remote interface for SheetDAOBean
 */
@Remote
public interface SheetDAOBeanRemote {
    /**
     * Sets mark for specified applicant and subject.
     *
     * @param applicant Applicant object
     * @param subject Subject object
     * @param mark mark for specified applicant and subject
     * @throws DAOException
     */
    void setMark(Applicant applicant, Subject subject, double mark) throws DAOException;

    /**
     * Returns mark for student and subject
     *
     * @param applicant Applicant object
     * @param subject Subject object
     * @return mark
     * @throws DAOException
     */
    double getMark(Applicant applicant, Subject subject) throws DAOException;

    /**
     * Add new record in sheet table
     *
     * @param applicant Applicant
     * @param subject Subject
     * @param mark mark
     * @throws DAOException
     */
    void newMark(Applicant applicant, Subject subject, double mark) throws DAOException;
}
