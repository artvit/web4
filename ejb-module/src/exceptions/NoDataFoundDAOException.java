package exceptions;

/**
 * Exception is raised when DAO class have not find any information
 */
public class NoDataFoundDAOException extends DAOException {
    public NoDataFoundDAOException(String message) {
        super(message);
    }

    public NoDataFoundDAOException() {
        super();
    }
}
