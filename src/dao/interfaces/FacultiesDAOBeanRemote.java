package dao.interfaces;

import entities.Faculty;
import exceptions.DAOException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Remote interface for FacultiesDAOBean
 */
@Remote
public interface FacultiesDAOBeanRemote {
    /**
     * Get faculty by specified name of faculty
     *
     * @param faculty name of faculty
     * @return Faculty object
     */
    Faculty getFaculty(String faculty) throws DAOException;

    /**
     * Get faculty by specified id of faculty
     *
     * @param id id of faculty
     * @return Faculty object
     */
    Faculty getFaculty(int id) throws DAOException;

    /**
     * Extract list of all faculties
     *
     * @return list of faculties
     * @throws DAOException
     */
    List<Faculty> getAllFaculties() throws DAOException;
}
