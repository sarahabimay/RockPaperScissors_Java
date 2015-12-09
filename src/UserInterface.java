import java.util.Optional;

public interface UserInterface {
    void displayGreeting();

    void displayResult(Optional<Throw> result);

    Throw requestConsoleMove();

    void displayAIMove(Throw symbol);

    void displayConsoleMove(Throw symbol);

    boolean requestReplay();
}
