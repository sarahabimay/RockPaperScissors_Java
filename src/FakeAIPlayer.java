public class FakeAIPlayer extends AIPlayer {

    private Throw aThrow;

    public void nextThrow(Throw paper) {
        this.aThrow = paper;
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
