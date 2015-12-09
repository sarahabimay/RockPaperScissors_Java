import java.util.Random;

public class FakeAIPlayer extends AIPlayer {

    private Throw aThrow;

    public FakeAIPlayer(Throw aThrow) {
        super(new Random());
        this.aThrow = aThrow;
    }

    @Override
    public Throw generateThrow() {
        return aThrow;
    }

    @Override
    public Throw getThrow() {
        return aThrow;
    }
}
