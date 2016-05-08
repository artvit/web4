package dao;

import entities.Applicant;
import entities.SheetRecord;
import entities.Subject;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;
import utils.EMFS;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DAO class for subjects table.
 */
public class SubjectsDAO {

    /**
     * Returns subject for specified subject's id
     *
     * @param id id of Subject
     * @return Subject object
     * @throws DAOException
     */
    public Subject getSubject(int id) throws DAOException {
        Subject result;
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            result = em.find(Subject.class, id);
            
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Got Subject object successfully");
        }
        em.getTransaction().commit();
        
        em.close();
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
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            Query query = em.createNamedQuery("Subject.findByName");
            query.setParameter("name", subject);
            result = (Subject) query.getSingleResult();
            
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting subject");
        }
        em.getTransaction().commit();
        
        em.close();
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
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            Query query = em.createNamedQuery("SheetRecord.findRecordsForApplicant");
            query.setParameter("applicant", applicant);
            List<SheetRecord> sheetRecords = (List<SheetRecord>) query.getResultList();
            
            result = sheetRecords.stream().map(SheetRecord::getSubject).collect(Collectors.toList());
            
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting subjects for applicant");
        }
        em.getTransaction().commit();
        
        em.close();
        return result;
        
    }
}
