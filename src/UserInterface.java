import java.util.Optional;

public interface UserInterface {
    void displayGreeting();

    void displayResult(Optional<Mark> result);

    Mark requestConsoleMove();

    void displayAIMove(Mark symbol);

    void displayConsoleMove(Mark symbol);

    boolean requestReplay();
}
