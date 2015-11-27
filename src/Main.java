import java.io.BufferedInputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        CommandLineUI cli = new CommandLineUI( new BufferedInputStream(System.in), new PrintStream(System.out));
        Game game = new Game(cli, new Rules());
        game.startGame();
    }
}
