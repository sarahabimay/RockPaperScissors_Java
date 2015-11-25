public class Game {
    private UserInterface userInterface;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void askUIToDisplayGreeting() {
        this.userInterface.displayGreeting();
    }
}
