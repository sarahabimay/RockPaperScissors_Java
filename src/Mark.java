
public enum Mark {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private int identifier;

    Mark(int identifier) {
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }

    public boolean equalsChoice(int moveChoice) {
        return identifier == moveChoice;
    }
}
