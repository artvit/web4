package exceptions;

import javax.servlet.ServletException;

/**
 * Exception that can pe thrown from methods of Administrator class.
 * Also contains message that will be shown in error page
 */
public class AdministratorException extends ServletException {
    public AdministratorException(String message) {
        super(message);
    }

    public AdministratorException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
