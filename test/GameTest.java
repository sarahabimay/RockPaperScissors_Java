import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTest {
    private OutputStream output;
    private PrintStream printStream;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void askCommandLineToDisplayGreeting() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.askUIToDisplayGreeting();
        assertThat(output.toString(), containsString(cli.GREETING_PROMPT));
    }

    @Test
    public void askUIToGetAndDisplayConsoleUserPrompt() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        String expected = String.format(cli.CONSOLE_MOVE, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void checkConsolePlayerWasCreated() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void createAIPlayer() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        game.createAIPlayer();
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void generateAnAIMove() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        assertThat(game.generateAIMove(game.createAIPlayer()),
                isIn(Arrays.asList(Throw.ROCK, Throw.PAPER, Throw.SCISSORS)));
    }

    @Test
    public void playTheGameAndDisplayWinningResult() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        ConsolePlayer consolePlayer = generateConsolePlayerAndMove(cli);
        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.PAPER);
        Optional<Throw> result = game.throwPlayerMoves(consolePlayer.getThrow(), aiPlayer.getThrow());
        game.askUIToDisplayResult(result);
        String expected = String.format(cli.WINNING_RESULT, Throw.PAPER);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void playTheGameAndDisplayDrawResult() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        ConsolePlayer consolePlayer = generateConsolePlayerAndMove(cli);
        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.ROCK);
        Optional<Throw> result = game.throwPlayerMoves(consolePlayer.getThrow(), aiPlayer.getThrow());
        game.askUIToDisplayResult(result);
        assertThat(output.toString(), containsString(cli.ANNOUNCE_DRAW));
    }

    private FakeAIPlayer generateFakeAIPlayerAndMove(Throw dummyMove) {
        FakeAIPlayer aiPlayer = new FakeAIPlayer();
        aiPlayer.nextThrow(dummyMove);
        return aiPlayer;
    }

    private ConsolePlayer generateConsolePlayerAndMove(CommandLineUI cli) {
        ConsolePlayer consolePlayer = new ConsolePlayer(cli);
        consolePlayer.generateThrow();
        return consolePlayer;
    }
}
