import java.io.*;

public class CommandLineUI {
    private PrintStream writeStream;
    private BufferedReader readStream;

    public CommandLineUI(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void requestConsoleTurn() {
        writeStream.println("Please select Rock(1), Paper(2), or Scissors(3): \n");
        int consoleMove = readLine();
        writeStream.println(String.format("Your move was: %s", consoleMove));
    }

    private int readLine() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
        }
        return 0;
    }
}
