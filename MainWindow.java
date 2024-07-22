
import javax.swing.JFrame;

public final class MainWindow {

    private JFrame window;

    public MainWindow() {
        initialize();
    }

    public void initialize() {
        this.window = new JFrame();
        this.window.setTitle("Minesweeper");
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setSize(800, 800);
        this.window.setLocationRelativeTo(null);
    }

    public void show() {
        window.setVisible(true);
    }

}
