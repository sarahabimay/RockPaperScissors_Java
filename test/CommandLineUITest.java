import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {
    private OutputStream output;
    private PrintStream printStream;

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
}
