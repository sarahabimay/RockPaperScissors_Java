import java.util.HashMap;
import java.util.Map;

public enum SYMBOL {
    ROCK,
    SCISSORS,
    PAPER;

    static Map<Integer, SYMBOL> ordinalToSymbol = new HashMap<>();

    SYMBOL() {
    }

    public static void setOrdinalToSymbol() {
        ordinalToSymbol.put(1, ROCK);
        ordinalToSymbol.put(2, SCISSORS);
        ordinalToSymbol.put(3, PAPER);
    }
    public static SYMBOL getSymbolFromOrdinal(int ordinal){
        return ordinalToSymbol.get(ordinal);
    }
}
