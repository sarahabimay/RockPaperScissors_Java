import java.util.Random;

public class Game {

    private Rules rules;
    private Symbol aiPlayer;
    private Symbol winningSymbol;

    public Game(Rules rules) {
        aiPlayer = null;
        this.rules = rules;
        Symbol.setOrdinalToSymbol();
    }

    public Symbol generateAIMove() {
        int range = Symbol.ordinalToSymbol.size();
        aiPlayer = getRandomSymbolInRange(range);
        return aiPlayer;
    }


    public Symbol passConsoleMoveToGameThenPlay(Symbol inputMove) {
        winningSymbol = play(inputMove, aiPlayer);
        return winningSymbol;
    }

    public Symbol getWinner() {
        return winningSymbol;
    }

    Symbol play(Symbol symbol1, Symbol symbol2) {
        return rules.decideWinner(symbol1, symbol2);
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
