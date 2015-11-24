public class Rules {
    public Throw decideWinner(Throw symbol1, Throw symbol2) {
        if (symbolsAreTheSame(symbol1, symbol2)) {
            return symbol1;
        }
        if (isPaper(symbol1, symbol2) && isScissor(symbol1, symbol2)) {
            return Throw.SCISSORS;
        }
        if (isPaper(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Throw.PAPER;
        }
        if (isScissor(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Throw.ROCK;
        }
        return null;
    }

    private boolean isRock(Throw symbol1, Throw symbol2) {
        return symbol1 == Throw.ROCK || symbol2 == Throw.ROCK;
    }

    private boolean isScissor(Throw symbol1, Throw symbol2) {
        return symbol1 == Throw.SCISSORS || symbol2 == Throw.SCISSORS;
    }

    private boolean isPaper(Throw symbol1, Throw symbol2) {
        return symbol1 == Throw.PAPER || symbol2 == Throw.PAPER;
    }

    private boolean symbolsAreTheSame(Throw symbol1, Throw symbol2) {
        return symbol1 == symbol2;
    }
}
