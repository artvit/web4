package servlet.commands;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * General interface for all commands.
 */
public interface Command {
    /**
     * Command executing
     *
     * @throws ServletException
     * @throws IOException
     */
    void execute() throws ServletException, IOException;
}
