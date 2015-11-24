public class Game {
    private Rules rules;
    private Throw winner;

    public Game(Rules rules) {
        this.rules = rules;
    }

    public Throw playGame(ConsolePlayer consolePlayer, FakeAIPlayer aiPlayer) {
        this.winner = rules.decideWinner(consolePlayer.getThrow(), aiPlayer.getThrow()).get();
        return this.winner;
    }
}
