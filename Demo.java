package src;

public class Demo {
    public static void main(String[] args) {
        BoardT model = new BoardT();
        Display view = Display.getInstance();
        GameController controller = GameController.getInstance(model, view);
        controller.runGame();
    }
}
