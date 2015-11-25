import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {
    private OutputStream output;
    private PrintStream printStream;
    private String SCISSORS_THROW;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void displayGreeting() {
        CommandLineUI cli = new CommandLineUI(printStream);
        cli.displayGreeting();
        String expected = cli.GREETING_MESSAGE;
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayConsoleMoveRequest() {
        SCISSORS_THROW = "3\n";
        InputStream inputStream = new ByteArrayInputStream(SCISSORS_THROW.getBytes());
        CommandLineUI cli = new CommandLineUI(printStream);
        cli.requestConsoleMove();
        String expected = cli.CONSOLE_MOVE_REQUEST;
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void getConsoleMoveAndDisplay() {
        SCISSORS_THROW = "3\n";
        InputStream inputStream = new ByteArrayInputStream(SCISSORS_THROW.getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        cli.displayConsoleMove(cli.requestConsoleMove());
        String expected = "Please enter Rock(1), Paper(2) or Scissors(3): \n\nYou have selected: SCISSORS \n\n";
        assertThat(output.toString(), is(expected));
    }
}
