package dao.implementations;

import dao.interfaces.FacultiesDAOBeanRemote;
import entities.Faculty;
import exceptions.DAOException;
import exceptions.NoDataFoundDAOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO class for faculties table.
 */
@Stateless(mappedName = "FacultiesDAOBean")
public class FacultiesDAOBean implements FacultiesDAOBeanRemote {

    @PersistenceContext(unitName = "Exams")
    private EntityManager em;

    /**
     * Get faculty by specified name of faculty
     *
     * @param faculty name of faculty
     * @return Faculty object
     */
    public Faculty getFaculty(String faculty) throws DAOException {
        Faculty result;
        try {
            Query query = em.createNamedQuery("Faculty.findByName");
            query.setParameter("name", faculty);
            result = (Faculty) query.getSingleResult();
        } catch (Exception e){
            throw new NoDataFoundDAOException();
        }
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
        try {
            result = em.find(Faculty.class, id);
        } catch (Exception e){
            throw new NoDataFoundDAOException("Error while getting faculty by id");
        }
        return result;
    }

    /**
     * Extract list of all faculties
     *
     * @return list of faculties
     * @throws DAOException
     */
    public List<Faculty> getAllFaculties() throws DAOException {
        List<Faculty> result = null;
        try {
            Query query = em.createNamedQuery("Faculty.findAll");
            result = (List<Faculty>) query.getResultList();
        } catch (Exception e) {
            throw new NoDataFoundDAOException("Error while getting list of all faculties");
        }
        if (result.size() == 0) {
            throw new NoDataFoundDAOException("Empty list was returned while getting all faculties");
        }
        return result;
    }
}
