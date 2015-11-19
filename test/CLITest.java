import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CLITest {

    private CLI cli;
    private FakeCLI fakeCLI;

    @Before
    public void setUp() {
        cli = new CLI();
        fakeCLI = new FakeCLI();
    }

    @Test
    public void displayGreeting() {
        fakeCLI.displayGreeting();
        assertEquals(true, fakeCLI.hasDisplayGreetingBeenCalled());
    }
    @Test
    public void requestHumanMove(){
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.SCISSORS}));
        assertEquals(SYMBOL.SCISSORS, fakeCLI.requestConsoleMove());
        assertEquals(true, fakeCLI.hasRequestConsoleMoveBeenCalled());
    }

    @Test
    public void displayHumanChoice() {
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.ROCK}));
        fakeCLI.play();
        assertEquals(true, fakeCLI.hasUserChoiceBeenShown());
    }

    @Test
    public void displayAIChoice() {
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.ROCK}));
        fakeCLI.play();
        assertEquals(true, fakeCLI.hasAIChoiceBeenShown());
    }

    @Test
    public void displayResult() {
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.ROCK}));
        fakeCLI.play();
        assertEquals(true, fakeCLI.hasResultBeenDisplayed());
    }

    @Test
    public void displayReplayOption() {
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.ROCK, SYMBOL.PAPER}));
        fakeCLI.dummyReplayChoice(1);
        fakeCLI.play();
        assertEquals(true, fakeCLI.hasReplayChoiceBeenDisplayed());

    }

    @Test
    public void enterHumanMoveAndPlayGame() {
        fakeCLI.addDummyMoves(aListOfMoves(new SYMBOL[]{SYMBOL.ROCK}));
        fakeCLI.play();
        assertEquals(true, fakeCLI.hasDisplayGreetingBeenCalled());
        assertEquals(true, fakeCLI.hasRequestConsoleMoveBeenCalled());
        assertEquals(true, fakeCLI.hasPlayBeenCalled());
        assertEquals(true, fakeCLI.hasResultBeenDisplayed());
    }

    public List<SYMBOL> aListOfMoves(SYMBOL[] moves) {
        List<SYMBOL> listOfMoves = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            listOfMoves.add(moves[i]);
        }
        return listOfMoves;
    }

}
