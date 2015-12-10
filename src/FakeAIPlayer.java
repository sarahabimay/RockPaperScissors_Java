import java.util.Random;

public class FakeAIPlayer extends AIPlayer {

    private Mark mark;

    public FakeAIPlayer(Mark mark) {
        super(new Random());
        this.mark = mark;
    }

    public Mark generateMark() {
        return mark;
    }

    public Mark getMark() {
        return mark;
    }
}
