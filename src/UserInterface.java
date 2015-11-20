public interface UserInterface {
    String displayGreeting();

    Symbol requestConsoleMove();

    void displayResult();

    void displayAIMove(Symbol symbol);

    void play();

    void displayConsoleMove(Symbol symbol);
}
