package dao.implementations;

import dao.interfaces.ApplicantsDAOBeanRemote;
import entities.Applicant;
import entities.Faculty;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;
import exceptions.NoDataUpdatedDAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO class for applicants table.
 */
@Stateless(mappedName = "ApplicantsDAOBean")
public class ApplicantsDAOBean implements ApplicantsDAOBeanRemote {
    @PersistenceContext(unitName = "Exams")
    private EntityManager em;

    /**
     * Returns list of applicants for specified faculty.
     *
     * @param faculty Faculty object
     * @return List of Applicants
     * @throws DAOException
     */
    public List<Applicant> getApplicantsForFaculty(Faculty faculty) throws DAOException {
        return faculty.getApplicants();
    }

    /**
     * Sets entered column
     *
     * @param applicant Applicant object
     * @param entered if applicant has entered or not
     * @throws DAOException
     */
    public void setEntered(Applicant applicant, boolean entered) throws DAOException {
        applicant.setEntered(entered);
        em.merge(applicant);
    }

    /**
     * Returns list of all applicants.
     *
     * @return List of Applicants
     * @throws DAOException
     */
    public List<Applicant> getAllApplicants() throws DAOException {
        List<Applicant> result = null;
        try {
            Query q = em.createNamedQuery("Applicant.findAll");
            result = (List<Applicant>) q.getResultList();
        } catch (Exception e) {
            throw new NoDataFoundDAOException("No data was found");
        }
        if (result.size() == 0) {
            throw new NoDataFoundDAOException("No data was found");
        }
        return result;
    }

    /**
     * Sets total mark for applicant.
     *
     * @param applicant Applicant object
     * @param total total mark
     * @throws DAOException
     */
    public void setTotalForApplicant(Applicant applicant, double total) throws DAOException {
        try {
            applicant.setTotal(total);
            em.merge(applicant);
        } catch (Exception e) {
            throw new NoDataUpdatedDAOException("No data was updated for applicant");
        }
    }

    /**
     * Returns average total mark for specified faculty.
     *
     * @param faculty Faculty object
     * @return average total mark
     * @throws DAOException
     */
    public double getAverageForFaculty(Faculty faculty) throws DAOException {
        double average = 0.0;
        List<Applicant> applicants = faculty.getApplicants();
        for (Applicant applicant : applicants)
            average += applicant.getTotal();
        average /= applicants.size();
        return average;
    }

    /**
     * Add new applicant
     *
     * @param applicant Applicant object
     * @param faculty Faculty object
     * @throws DAOException
     */
    public void addNewApplicant(Applicant applicant, Faculty faculty) throws DAOException {
        try {
            applicant.setFaculty(faculty);
            em.persist(applicant);
        } catch (Exception e) {
            throw new NoDataUpdatedDAOException("No new data was added to DB");
        }
    }

    /**
     * Get applicant with specified name.
     *
     * @param applicantName applicant's name
     * @return Applicant object
     * @throws DAOException
     */
    public Applicant getApplicant(String applicantName) throws DAOException {
        Applicant result;
        try {
            Query query = em.createNamedQuery("Applicant.findByName");
            query.setParameter("name", applicantName);
            result = (Applicant) query.getSingleResult();
        } catch (Exception e){
            throw new NoDataFoundDAOException("Error while getting applicant");
        }
        return result;
    }
}
