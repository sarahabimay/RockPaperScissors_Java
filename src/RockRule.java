public class RockRule{

    public SYMBOL decideWinner(SYMBOL player1Signal, SYMBOL player2Signal) {
        if (player1Signal != SYMBOL.ROCK && player2Signal != SYMBOL.ROCK) {
            return null;
        }
        if (player2Signal == SYMBOL.SCISSORS || player1Signal == SYMBOL.SCISSORS) {
            return SYMBOL.ROCK;
        }
        if (player1Signal == SYMBOL.PAPER || player2Signal == SYMBOL.PAPER) {
            return SYMBOL.PAPER;
        }
        return SYMBOL.ROCK;
    }
}
