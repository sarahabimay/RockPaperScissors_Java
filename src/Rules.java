public class Rules {
    public SYMBOL decideWinner(SYMBOL symbol1, SYMBOL symbol2) {
        if (symbolsAreTheSame(symbol1, symbol2)) {
            return symbol1;
        }
        if (isPaper(symbol1, symbol2) && isScissor(symbol1, symbol2)) {
            return SYMBOL.SCISSORS;
        }
        if (isPaper(symbol1, symbol2)  && isRock(symbol1, symbol2)) {
            return SYMBOL.PAPER;
        }
        if (isScissor(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return SYMBOL.ROCK;
        }
        return null;
    }

    private boolean isRock(SYMBOL symbol1, SYMBOL symbol2) {
        return symbol1 == SYMBOL.ROCK || symbol2 == SYMBOL.ROCK;
    }

    private boolean isScissor(SYMBOL symbol1, SYMBOL symbol2) {
        return symbol1 == SYMBOL.SCISSORS || symbol2 == SYMBOL.SCISSORS;
    }

    private boolean isPaper(SYMBOL symbol1, SYMBOL symbol2) {
        return symbol1 == SYMBOL.PAPER || symbol2 == SYMBOL.PAPER;
    }

    private boolean symbolsAreTheSame(SYMBOL symbol1, SYMBOL symbol2) {
        return symbol1 == symbol2;
    }
}
