import java.util.HashMap;
import java.util.Map;

public enum Symbol {
    ROCK,
    PAPER,
    SCISSORS;

    static Map<Integer, Symbol> ordinalToSymbol = new HashMap<>();

    public static void setOrdinalToSymbol() {
        ordinalToSymbol.put(1, ROCK);
        ordinalToSymbol.put(2, PAPER);
        ordinalToSymbol.put(3, SCISSORS);
    }

    public static Symbol getSymbolFromOrdinal(int ordinal) {
        return ordinalToSymbol.get(ordinal);
    }
}
