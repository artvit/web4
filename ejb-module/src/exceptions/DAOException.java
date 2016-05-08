package exceptions;

/**
 * General exception class for all DAO classes.
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException() {
        super();
    }
}
