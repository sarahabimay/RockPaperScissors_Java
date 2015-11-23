import java.io.BufferedInputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Rules());
        CLI cli = new CLI( new BufferedInputStream(System.in), new PrintStream(System.out), game);
        cli.play();
    }
}
