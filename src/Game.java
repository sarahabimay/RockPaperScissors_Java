import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Rules rules;
    private final List<Symbol> moves = new ArrayList<>();

    public Game(Rules rules) {
        this.rules = rules;
        Symbol.setOrdinalToSymbol();
    }

    public Symbol generateAIMove() {
        Symbol aiPlayer = getRandomSymbolInRange(Symbol.ordinalToSymbol.size());
        moves.add(aiPlayer);
        return aiPlayer;
    }

    public void addPlayerMove(Symbol playerMove) {
        moves.add(playerMove);
    }

    public Symbol playGame() {
        return moves.stream().reduce((a, b) -> rules.decideWinner(a, b)).get();
    }

    public Symbol getWinner() {
        return moves.stream().reduce((a, b) -> rules.decideWinner(a, b)).get();
    }

    private Symbol getRandomSymbolInRange(int range) {
        int randomOrdinal = randomNumberInRange(randomFractionFromRange(range));
        return Symbol.getSymbolFromOrdinal(randomOrdinal);
    }

    private long randomFractionFromRange(long range) {
        Random randomGenerator = new Random();
        return (long) (range * randomGenerator.nextDouble());
    }

    private int randomNumberInRange(long fraction) {
        int start = 1;
        return (int) (fraction + start);
    }
}
