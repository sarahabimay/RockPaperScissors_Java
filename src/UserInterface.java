public interface UserInterface {
    void displayGreeting();

    Throw requestConsoleMove();

    void displayResult();

    void displayAIMove(Throw symbol);

    void play();

    void displayConsoleMove(Throw symbol);
}
