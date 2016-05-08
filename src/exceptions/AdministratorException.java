package exceptions;

import javax.servlet.ServletException;

/**
 * Created by artvi on 07/05/2016.
 */
public class AdministratorException extends ServletException {
    public AdministratorException(String message) {
        super(message);
    }

    public AdministratorException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
