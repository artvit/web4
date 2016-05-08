package exceptions;

/**
 * Exception is raised when DAO class have not updated any records
 */
public class NoDataUpdatedDAOException extends DAOException {
    public NoDataUpdatedDAOException(String message) {
        super(message);
    }

    public NoDataUpdatedDAOException() {

    }
}
