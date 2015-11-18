public class Rules {
    public SYMBOL decideWinner(SYMBOL symbol1, SYMBOL symbol2) {
        if (symbol1 == symbol2){
            return symbol1;
        }
        if ((symbol1 == SYMBOL.ROCK  || symbol2 == SYMBOL.ROCK) &&
                (symbol1 == SYMBOL.PAPER || symbol2 == SYMBOL.PAPER)){
            return SYMBOL.PAPER;
        }
        if ((symbol1 == SYMBOL.ROCK || symbol2 == SYMBOL.ROCK) &&
                (symbol2 == SYMBOL.SCISSORS || symbol1 == SYMBOL.SCISSORS)) {
            return SYMBOL.ROCK;
        }
//        if (player1Signal == SYMBOL.PAPER || player2Signal == SYMBOL.PAPER) {
//            return SYMBOL.PAPER;
//        }
        return null;
    }
}
