import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

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
}
