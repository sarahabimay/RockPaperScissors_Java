import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CommandLineTest implements UserInterface{
    private OutputStream output;
    private PrintStream printStream;
    private CommandLineUI cli;

    @Test
    public void requestHumanTurn() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        cli.requestConsoleTurn();
        assertThat(output.toString(), containsString("Your move was: 1"));
    }
}
