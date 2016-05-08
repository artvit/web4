package servlet.commands;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by artvi on 01/05/2016.
 */
public interface Command {
    void execute() throws ServletException, IOException;
}
