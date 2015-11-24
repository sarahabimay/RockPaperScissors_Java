import java.util.HashMap;
import java.util.Map;

public enum Throw {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    static Map<Integer, Throw> ordinalToSymbol = new HashMap<>();

    Throw(int identifier) {

    }

    public static void setOrdinalToSymbol() {
        ordinalToSymbol.put(1, ROCK);
        ordinalToSymbol.put(2, PAPER);
        ordinalToSymbol.put(3, SCISSORS);
    }

    public static Throw getSymbolFromOrdinal(int ordinal) {
        return ordinalToSymbol.get(ordinal);
    }
}
