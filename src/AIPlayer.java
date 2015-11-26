import java.util.Optional;
import java.util.Random;

import static java.util.Optional.of;

public class AIPlayer implements Player {
    private Throw aThrow;

    public Throw getThrow() {
        return aThrow;
    }

    public Throw generateThrow() {
        this.aThrow = getRandomSymbolInRange(Throw.values().length);
        return aThrow;
    }

    private Throw getRandomSymbolInRange(int range) {
        int randomOrdinal = randomNumberInRange(randomFractionFromRange(range));
        return convertToThrow(randomOrdinal).get();
    }

    private Optional<Throw> convertToThrow(int throwIdentifier) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.equalsChoice(throwIdentifier)) {
                return of(aThrow);
            }
        }
        return Optional.empty();
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
