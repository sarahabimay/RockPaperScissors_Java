import org.junit.Before;
import org.junit.Ignore;
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
    private InputStream inputStream;
    private CommandLine cli;
    private Game game;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLine(inputStream, printStream);
        game = new Game(cli, new Rules());
    }

    @Test
    public void askCommandLineToDisplayGreeting() {
        game.askUIToDisplayGreeting();
        assertThat(output.toString(), containsString(cli.GREETING_PROMPT));
    }

    @Test
    public void getAndDisplayConsoleMoveToCommandLine() {
        game.displayConsoleMove(game.createConsolePlayer().generateThrow());
        String expected = String.format(cli.CONSOLE_MOVE, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void checkValidAIMoveIsGenerate() {
        assertThat(game.createAIPlayer().generateThrow(),
                isIn(Arrays.asList(Throw.ROCK, Throw.PAPER, Throw.SCISSORS)));
    }

    @Test
    public void displayAIMoveToCommandLine() {
        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.PAPER);
        Throw aiMove = aiPlayer.generateThrow();
        game.displayAIMove(aiMove);
        String expected = String.format(cli.AI_MOVE, Throw.PAPER);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void confirmTwoPlayersAddedToGame() {
        game.createConsolePlayer();
        game.createAIPlayer();
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void playTheGameAndDisplayWinningResult() {
        ConsolePlayer consolePlayer = generateConsolePlayerAndMove(cli);
        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.PAPER);
        Optional<Throw> result = game.throwPlayerMoves(consolePlayer, aiPlayer);
        game.askUIToDisplayResult(result);
        String expected = String.format(cli.WINNING_RESULT, Throw.PAPER);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void playTheGameAndDisplayDrawResult() {
        ConsolePlayer consolePlayer = generateConsolePlayerAndMove(cli);
        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.ROCK);
        Optional<Throw> result = game.throwPlayerMoves(consolePlayer, aiPlayer);
        game.askUIToDisplayResult(result);
        assertThat(output.toString(), containsString(cli.ANNOUNCE_DRAW));
    }

    @Test
    @Ignore
    public void playTheGameAndyDrawResult() {
//        ConsolePlayer consolePlayer = generateConsolePlayerAndMove(cli);
//        FakeAIPlayer aiPlayer = generateFakeAIPlayerAndMove(Throw.SCISSORS);
//        Optional<Throw> result = game.throwPlayerMoves(consolePlayer, aiPlayer);
//        game.askUIToDisplayResult(result);
        game.startGame();
        String expected = String.format(cli.WINNING_RESULT, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void requestToPlayAgain() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLine cli = new CommandLine(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        assertEquals(true, game.playAgain());
    }

    @Test
    public void requestToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLine cli = new CommandLine(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        assertEquals(false, game.playAgain());
    }

    @Test
    public void invalidMoveValidMoveReplayQuitIntegration() {
        int INVALID = 9;
        int REPLAY = 1;
        int QUIT = 2;
        String buffer = String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
                INVALID,
                Throw.ROCK.getIdentifier(),
                REPLAY,
                Throw.SCISSORS.getIdentifier(),
                INVALID,
                QUIT);
        byte[] buf = buffer.getBytes();// invalid, Rock, Replay, Scissors, Invalid, Quit
        InputStream inputStream = new ByteArrayInputStream(buf);
        CommandLine cli = new CommandLine(inputStream,printStream);
        Game game = new Game(cli, new Rules());
        game.startGame();
        assertThat(output.toString(), containsString("REPLAY"));
        assertThat(output.toString(), containsString("QUIT"));
        assertThat(output.toString(), containsString(cli.INVALID_CHOICE));
    }

    private FakeAIPlayer generateFakeAIPlayerAndMove(Throw dummyMove) {
        FakeAIPlayer aiPlayer = new FakeAIPlayer();
        aiPlayer.nextThrow(dummyMove);
        return aiPlayer;
    }

    private ConsolePlayer generateConsolePlayerAndMove(CommandLine cli) {
        ConsolePlayer consolePlayer = new ConsolePlayer(cli);
        consolePlayer.generateThrow();
        return consolePlayer;
    }
}
