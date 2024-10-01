
public class Controller {

    public static void main(String[] args) {
        int bombs = 10;
        Gameboard game = new Gameboard();
        game.fillArray(bombs);
        MainWindow main = new MainWindow(game);
        game.setMainWindow(main);
        game.checkSurrBombs();
        game.printArray();
        main.show();
    }
}
