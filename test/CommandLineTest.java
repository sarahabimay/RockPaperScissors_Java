import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineTest{
    private OutputStream output;
    private PrintStream printStream;
    private CommandLine cli;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void requestHumanTurn() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        assertEquals(Throw.ROCK, cli.requestConsoleMove());
    }

    @Test
    public void humanTurnIsInvalid() {
        InputStream inputStream = new ByteArrayInputStream("99\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        assertEquals(false, cli.isValidThrow(Optional.empty()));
    }

    @Test
    public void invalidThenValidHumanChoice() {
        byte[] buf = "9\n2\n".getBytes();// 9 == invalid and 2 == Paper
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLine(inputStream, printStream);
        assertEquals(Throw.PAPER, cli.requestConsoleMove());
        assertThat(output.toString(), containsString(cli.INVALID_CHOICE));
    }

    @Test
    public void displayReplayChoices() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.requestReplay();
        assertThat(output.toString(), containsString(cli.REPLAY_OPTION));
    }

    @Test
    public void invalidThenReplayChosen() {
        byte[] buf = "9\n1\n".getBytes();// 9 == invalid and 1 == Replay
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLine(inputStream, printStream);
        assertEquals(true, cli.requestReplay());
        assertThat(output.toString(), containsString(cli.INVALID_CHOICE));
    }

    @Test
    public void requestCommandLineDisplaysGreeting() {
        InputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.displayGreeting();
        assertThat(output.toString(), containsString(cli.GREETING_PROMPT));
    }

    @Test
    public void requestDisplayConsoleMove() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.displayConsoleMove(cli.requestConsoleMove());
        String expected = String.format(cli.CONSOLE_MOVE, Throw.PAPER);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void requestDisplayAIMove() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.displayAIMove(Throw.ROCK);
        String expected = String.format(cli.AI_MOVE, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void requestDisplayResultWithWinner() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.displayResult(Optional.of(Throw.ROCK));
        String expected = String.format(cli.WINNING_RESULT, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));

    }

    @Test
    public void requestDisplayResultWithDraw() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        cli.displayResult(Optional.empty());
        assertThat(output.toString(), containsString(cli.ANNOUNCE_DRAW));
    }
}
