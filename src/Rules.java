public class Rules {
    public SYMBOL decideWinner(SYMBOL symbol1, SYMBOL symbol2) {
        if (symbol1 == SYMBOL.PAPER && symbol2 == SYMBOL.SCISSORS){
            return SYMBOL.SCISSORS;
        }
        if (symbol1 == SYMBOL.SCISSORS && symbol2 == SYMBOL.PAPER){
            return SYMBOL.SCISSORS;
        }
        if (symbol1 == SYMBOL.PAPER && symbol2 == SYMBOL.ROCK){
            return SYMBOL.PAPER;
        }
        if (symbol1 == SYMBOL.ROCK && symbol2 == SYMBOL.PAPER){
            return SYMBOL.PAPER;
        }
        if (symbol1 == SYMBOL.SCISSORS && symbol2 == SYMBOL.ROCK){
            return SYMBOL.ROCK;
        }
        if (symbol1 == SYMBOL.ROCK && symbol2 == SYMBOL.SCISSORS){
            return SYMBOL.ROCK;
        }
        if (symbolsAreTheSame(symbol1, symbol2)){
            return symbol1;
        }
        return null;
    }

    private boolean symbolsAreTheSame(SYMBOL symbol1, SYMBOL symbol2) {
        return symbol1 == symbol2;
    }
}
