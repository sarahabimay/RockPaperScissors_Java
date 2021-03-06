import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ConsolePlayerTest {
    private OutputStream output;
    private PrintStream printStream;
    private CommandLine cli;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void createConsolePlayerMark() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        ConsolePlayer consolePlayer = new ConsolePlayer(cli);
        consolePlayer.generateMark();
        assertEquals(Mark.ROCK, consolePlayer.getMark());
    }
}
