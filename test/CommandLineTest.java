import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineTest implements UserInterface{
    private OutputStream output;
    private PrintStream printStream;
    private CommandLineUI cli;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void requestHumanTurn() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(Throw.ROCK, cli.requestConsoleTurn());
    }

    @Test
    public void humanTurnIsInvalid() {
        InputStream inputStream = new ByteArrayInputStream("99\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(false, cli.isValidThrow(99));
    }

    @Test
    public void requestInvalidHumanInput() {
        InputStream inputStream = new ByteArrayInputStream("99\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(Optional.empty(), cli.requestConsoleTurn());
        assertThat(output.toString(), containsString("Invalid choice. \n"));
    }

    @Test
    public void invalidThenValidHumanChoice() {
        byte[] buf = "9\n2\n".getBytes();// 9 == invalid and 2 == Paper
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(Throw.PAPER, cli.requestConsoleTurn().get());
        assertThat(output.toString(), containsString("Invalid choice. \n"));
    }

    @Test
    public void displayReplayChoices() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(2, cli.requestReplay());
        assertThat(output.toString(), containsString("Do you want to play again? Yes(1) or No(2): \n"));
    }

    @Test
    public void replayChoiceIsInvalid() {
        InputStream inputStream = new ByteArrayInputStream("99\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(99, cli.requestReplay());
        assertThat(output.toString(), containsString("Invalid selection: \n"));
    }
    @Test
    public void requestReplayChoiceTillInvalid() {
        byte[] buf = "9\n2\n".getBytes();// 9 == invalid and 2 == No
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(2, cli.requestReplay());
        assertThat(output.toString(), containsString("Invalid selection: \n"));
    }
}
