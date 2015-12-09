import java.util.Optional;
import java.util.Random;

import static java.util.Optional.of;

public class AIPlayer implements Player {
    private final int START_POSITION = 1;
    private Random random;
    private Throw aThrow;

    public AIPlayer(Random random) {
        this.random = random;
    }

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
        if (throwIdentifier <= Throw.values().length){
            Throw aThrow= Throw.values()[throwIdentifier-1];
            return of(aThrow);
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
