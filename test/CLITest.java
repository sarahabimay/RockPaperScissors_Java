import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CLITest {

    private CLI cli;
    private OutputStream output;
    private PrintStream printStream;
    private Game game;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        game = new Game(new Rules());
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        cli = new CLI(inputStream, printStream, game);
    }

    @Test
    public void displayGreeting() {
        cli.displayGreeting();
        String expected = "Welcome to the Rock Paper Scissors Game.\n\n";
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayConsoleMoveRequest() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        CLI cli = new CLI(inputStream, printStream, game);
        cli.requestConsoleMove();
        String expected = cli.CONSOLE_MOVE_REQUEST;
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void requestHumanMove() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        CLI cli = new CLI(inputStream, printStream, game);
        assertThat(cli.requestConsoleMove(), is(Symbol.SCISSORS));
    }

    @Test
    public void displayHumanChoicePrompt() {
        cli.displayConsoleMove(Symbol.ROCK);
        String expected = String.format(cli.CONSOLE_MOVE_DISPLAY, Symbol.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayAIChoice() {
        cli.displayAIMove(Symbol.ROCK);
        String expected = String.format(cli.AI_MOVE_DISPLAY, Symbol.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayResult() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CLI cli = new CLI(inputStream, printStream, game);
        cli.play();
        String expected = String.format(cli.RESULT_DISPLAY, game.getWinner());
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void displayReplayOption() {
        byte[] buf = "1\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        CLI cli = new CLI(inputStream, printStream, game);
        cli.play();
        String expected = cli.REPLAY_REQUEST;
        assertThat(output.toString(), containsString(expected));
    }
}
