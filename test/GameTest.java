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
    private InputStream inputStream;
    private CommandLineUI cli;
    private Game game;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
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
    public void requestToPlayAgain() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        assertEquals(true, game.playAgain());
    }

    @Test
    public void requestToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli, new Rules());
        assertEquals(false, game.playAgain());
    }

    @Test
    public void gameIntegrationTest() {
        game.startGame();
        assertThat(output.toString(), containsString(cli.GAME_OVER));
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
