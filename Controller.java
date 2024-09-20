
public class Controller {

    public static void main(String[] args) {
        Gameboard game = new Gameboard();
        int bombs = 10;
        game.fillarray(bombs);
        game.checkSurrBombs();
        game.printArray();
        MainWindow main = new MainWindow(game);
        main.show();
    }

}
