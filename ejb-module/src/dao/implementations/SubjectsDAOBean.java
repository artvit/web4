package dao.implementations;

import dao.interfaces.SubjectsDAOBeanRemote;
import entities.Applicant;
import entities.SheetRecord;
import entities.Subject;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DAO class for subjects table.
 */
@Stateless(mappedName = "SubjectsDAOBean")
public class SubjectsDAOBean implements SubjectsDAOBeanRemote {

    @PersistenceContext(unitName = "Exams")
    private EntityManager em;

    /**
     * Returns subject for specified subject's id
     *
     * @param id id of Subject
     * @return Subject object
     * @throws DAOException
     */
    public Subject getSubject(int id) throws DAOException {
        Subject result;
        try {
            result = em.find(Subject.class, id);
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while extracting subject: "+ e.getClass().getName() +": " + e.getMessage());
        }
        return result;
    }

    /**
     * Get subject by specified name of subject
     *
     * @param subject name of subject
     * @return subject object
     */
    public Subject getSubject(String subject) throws DAOException {
        Subject result;
        try {
            Query query = em.createNamedQuery("Subject.findByName");
            query.setParameter("name", subject);
            result = (Subject) query.getSingleResult();
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting subject");
        }
        return result;
    }

    /**
     * Returns list of subjects for specified Applicant
     *
     * @param applicant Applicant object
     * @return List of Subjects
     * @throws DAOException
     */
    public List<Subject> getSubjectsForApplicant(Applicant applicant) throws DAOException {
        List<Subject> result = null;
        try {
            Query query = em.createNamedQuery("SheetRecord.findRecordsForApplicant");
            query.setParameter("applicant", applicant);
            List<SheetRecord> sheetRecords = (List<SheetRecord>) query.getResultList();
            result = sheetRecords.stream().map(SheetRecord::getSubject).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting subjects for applicant");
        }
        return result;
    }
}
