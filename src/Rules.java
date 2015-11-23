public class Rules {
    public Symbol decideWinner(Symbol symbol1, Symbol symbol2) {
        if (symbolsAreTheSame(symbol1, symbol2)) {
            return symbol1;
        }
        if (isPaper(symbol1, symbol2) && isScissor(symbol1, symbol2)) {
            return Symbol.SCISSORS;
        }
        if (isPaper(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Symbol.PAPER;
        }
        if (isScissor(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Symbol.ROCK;
        }
        return null;
    }

    private boolean isRock(Symbol symbol1, Symbol symbol2) {
        return symbol1 == Symbol.ROCK || symbol2 == Symbol.ROCK;
    }

    private boolean isScissor(Symbol symbol1, Symbol symbol2) {
        return symbol1 == Symbol.SCISSORS || symbol2 == Symbol.SCISSORS;
    }

    private boolean isPaper(Symbol symbol1, Symbol symbol2) {
        return symbol1 == Symbol.PAPER || symbol2 == Symbol.PAPER;
    }

    private boolean symbolsAreTheSame(Symbol symbol1, Symbol symbol2) {
        return symbol1 == symbol2;
    }
}
