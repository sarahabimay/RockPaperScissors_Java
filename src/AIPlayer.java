import java.util.Optional;
import java.util.Random;

import static java.util.Optional.of;

public class AIPlayer implements Player {
    private final int START_POSITION = 1;
    private Random random;
    private Mark mark;

    public AIPlayer(Random random) {
        this.random = random;
    }

    public Mark getMark() {
        return mark;
    }

    public Mark generateMark() {
        this.mark = getRandomSymbolInRange(Mark.values().length);
        return mark;
    }

    private Mark getRandomSymbolInRange(int range) {
        int randomOrdinal = randomNumberInRange(randomFractionFromRange(range));
        return convertToThrow(randomOrdinal).get();
    }

    private Optional<Mark> convertToThrow(int throwIdentifier) {
        if (throwIdentifier <= Mark.values().length) {
            Mark mark = Mark.values()[throwIdentifier - 1];
            return of(mark);
        }
        return Optional.empty();
    }

    private long randomFractionFromRange(long range) {
        return (long) (range * random.nextDouble());
    }

    private int randomNumberInRange(long fraction) {
        return (int) (fraction + START_POSITION);
    }
}
