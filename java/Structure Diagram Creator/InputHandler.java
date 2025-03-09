import java.util.Scanner;

public class InputHandler {
    Scanner inp = new Scanner(System.in);

    public InputHandler() {}

    public String getText() {
        return inp.nextLine();
    }
}
