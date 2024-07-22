
public class Controller {

    public static void main(String[] args) {
        int bombs = 20;
        Gameboard game = new Gameboard();
        game.fillarray(bombs);
        game.printArray();
    }
}
