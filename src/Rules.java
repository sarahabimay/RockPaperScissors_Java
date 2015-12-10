import java.util.Optional;

public class Rules {
    public Optional<Mark> decideWinner(Mark symbol1, Mark symbol2) {
        if (symbolsAreTheSame(symbol1, symbol2)) {
            return Optional.empty();
        }
        if (isPaper(symbol1, symbol2) && isScissor(symbol1, symbol2)) {
            return Optional.of(Mark.SCISSORS);
        }
        if (isPaper(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Optional.of(Mark.PAPER);
        }
        if (isScissor(symbol1, symbol2) && isRock(symbol1, symbol2)) {
            return Optional.of(Mark.ROCK);
        }
        return Optional.empty();
    }

    private boolean isRock(Mark symbol1, Mark symbol2) {
        return symbol1 == Mark.ROCK || symbol2 == Mark.ROCK;
    }

    private boolean isScissor(Mark symbol1, Mark symbol2) {
        return symbol1 == Mark.SCISSORS || symbol2 == Mark.SCISSORS;
    }

    private boolean isPaper(Mark symbol1, Mark symbol2) {
        return symbol1 == Mark.PAPER || symbol2 == Mark.PAPER;
    }

    private boolean symbolsAreTheSame(Mark symbol1, Mark symbol2) {
        return symbol1 == symbol2;
    }
}
