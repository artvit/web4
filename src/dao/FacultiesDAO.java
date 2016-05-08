package dao;

import entities.Faculty;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;
import utils.EMFS;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO class for faculties table.
 */
public class FacultiesDAO {

    /**
     * Get faculty by specified name of faculty
     *
     * @param faculty name of faculty
     * @return Faculty object
     */
    public Faculty getFaculty(String faculty) throws DAOException {
        Faculty result;
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            Query query = em.createNamedQuery("Faculty.findByName");
            query.setParameter("name", faculty);
            result = (Faculty) query.getSingleResult();
            
        } catch (Exception e){
            throw new NoDataFoundDAOException();
            
        }
        em.getTransaction().commit();
        
        em.close();
        return result;
        
    }

    /**
     * Get faculty by specified id of faculty
     *
     * @param id id of faculty
     * @return Faculty object
     */
    public Faculty getFaculty(int id) throws DAOException {
        Faculty result;
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            result = em.find(Faculty.class, id);
            
        } catch (Exception e){
            throw new NoDataFoundDAOException("Error while getting faculty by id");
        }
        em.getTransaction().commit();
        
        em.close();
        return result;
        
    }

    public List<Faculty> getAllFaculties() throws DAOException {
        List<Faculty> result = null;
        EntityManager em = EMFS.getEntityManager();
        
        em.getTransaction().begin();
        
        try {
            Query query = em.createNamedQuery("Faculty.findAll");
            result = (List<Faculty>) query.getResultList();
            
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting list of all faculties");
        }
        em.getTransaction().commit();
        
        em.close();
        
        if (result.size() == 0) {
            throw new NoDataFoundDAOException("Empty list was returned while getting all faculties");
        }
        return result;
    }

}
