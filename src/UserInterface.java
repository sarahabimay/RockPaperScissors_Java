public interface UserInterface {
    String displayGreeting();
    SYMBOL requestConsoleMove();

    void displayResult();

    void displayAIMove(SYMBOL symbol);

    void play();

    void displayConsoleMove(SYMBOL symbol);
}
