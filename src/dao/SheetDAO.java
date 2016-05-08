package dao;

import entities.Applicant;
import entities.SheetRecord;
import entities.Subject;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;
import exceptions.NoDataUpdatedDAOException;
import utils.EMFS;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * DAO class for sheet table.
 */
public class SheetDAO {
    /**
     * Sets mark for specified applicant and subject.
     *
     * @param applicant Applicant object
     * @param subject Subject object
     * @param mark mark for specified applicant and subject
     * @throws DAOException
     */
    public void setMark(Applicant applicant, Subject subject, double mark) throws DAOException {
        EntityManager em = EMFS.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("SheetRecord.findByApplicantSubject");
            query.setParameter("applicant", applicant);
            query.setParameter("subject", subject);
            SheetRecord sheetRecord = (SheetRecord) query.getSingleResult();
            sheetRecord.setMark(mark);
            em.merge(sheetRecord);
        } catch (Exception e){
            throw new NoDataFoundDAOException("Error while updating SheetRecord");
        }
        em.getTransaction().commit();
        
        em.close();
        
    }

    /**
     * Returns mark for student and subject
     *
     * @param applicant Applicant object
     * @param subject Subject object
     * @return mark
     * @throws DAOException
     */
    public double getMark(Applicant applicant, Subject subject) throws DAOException {
        double mark;
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            Query query = em.createNamedQuery("SheetRecord.findByApplicantSubject");
            query.setParameter("applicant", applicant);
            query.setParameter("subject", subject);
            mark = ((SheetRecord) query.getSingleResult()).getMark();
            
        } catch (Exception e){
            throw new NoDataFoundDAOException("Error while extracting mark");
        }
        em.getTransaction().commit();
        
        em.close();
        return mark;
        
    }

    /**
     * Add new record in sheet table
     *
     * @param applicant Applicant
     * @param subject Subject
     * @param mark mark
     * @throws DAOException
     */
    public void newMark(Applicant applicant, Subject subject, double mark) throws DAOException {
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            SheetRecord sheetRecord = new SheetRecord();
            sheetRecord.setApplicant(applicant);
            sheetRecord.setSubject(subject);
            sheetRecord.setMark(mark);
            em.persist(sheetRecord);
        } catch (Exception e) {
            throw new NoDataUpdatedDAOException("Error while adding new SheetRecord", e);
        }
        em.getTransaction().commit();
        em.close();
    }
}
