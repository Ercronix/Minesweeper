
import javax.swing.SwingUtilities;

public class Controller {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow main = new MainWindow();
                main.show();
            }
        });

        int bombs = 20;
        Gameboard game = new Gameboard();
        game.fillarray(bombs);
        game.printArray();
    }
}
