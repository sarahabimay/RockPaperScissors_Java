public interface UserInterface {
    void displayGreeting();

    Symbol requestConsoleMove();

    void displayResult();

    void displayAIMove(Symbol symbol);

    void play();

    void displayConsoleMove(Symbol symbol);
}
