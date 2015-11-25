import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {
    private OutputStream output;
    private PrintStream printStream;
    private String SCISSORS_THROW;
    private CommandLineUI cli;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
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
    public void displayConsoleChoicePrompt() {
        cli.displayConsoleMove(Optional.of(Throw.ROCK));
        String expected = String.format(cli.CONSOLE_MOVE_DISPLAY, Throw.ROCK);
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

    @Test
    public void displayAIChoice() {
        cli.displayAIMove(Throw.ROCK);
        String expected = String.format(cli.AI_MOVE_DISPLAY, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayResult() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        cli.displayResult(Throw.ROCK);
        String expected = String.format(cli.RESULT_DISPLAY, "ROCK");
        assertThat(output.toString(), containsString(expected));
    }
}
