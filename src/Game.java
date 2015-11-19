import java.util.Random;

public class Game {

    private SYMBOL aiPlayer;
    private Rules rules;

    public Game() {
        aiPlayer = null;
        rules = new Rules();
        SYMBOL.setOrdinalToSymbol();
    }

    public SYMBOL generateAIMove() {
        int range = SYMBOL.ordinalToSymbol.size();
        aiPlayer = getRandomSymbolInRange(range);
        return aiPlayer;
    }

    public SYMBOL play(SYMBOL symbol1, SYMBOL symbol2) {
        return rules.decideWinner(symbol1, symbol2);
    }

    public SYMBOL startGame(SYMBOL inputMove) {
        return play(inputMove, generateAIMove());
    }

    private SYMBOL getRandomSymbolInRange(int range) {
        int randomOrdinal = randomNumberInRange(randomFractionFromRange(range));
        return SYMBOL.getSymbolFromOrdinal(randomOrdinal);
    }

    long randomFractionFromRange(long range) {
        Random randomGenerator = new Random();
        return (long) (range * randomGenerator.nextDouble());
    }

    int randomNumberInRange(long fraction) {
        int start = 1;
        return (int) (fraction + start);
    }
}
