
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public final class MainWindow implements ActionListener, MouseListener {

    private JFrame window;
    private JButton button1;
    private Gameboard board;
    private JButton[][] buttonArray;

    public MainWindow(Gameboard board) {
        this.board = board;
        initialize();
    }

    public void initialize() {
        this.window = new JFrame();
        this.window.setTitle("Minesweeper");
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setSize(800, 800);
        this.window.setLocationRelativeTo(null);
        this.window.setLayout(new GridLayout(board.getFieldHeightOrWidthIdkDepends(), board.getFieldHeightOrWidthIdkDepends()));
        buttonArray = new JButton[board.getFieldHeightOrWidthIdkDepends()][board.getFieldHeightOrWidthIdkDepends()];
        for (int i = 0; i < board.getFieldHeightOrWidthIdkDepends(); i++) {
            for (int j = 0; j < board.getFieldHeightOrWidthIdkDepends(); j++) {
                JButton button = new JButton(String.valueOf(board.getCellValue(i, j)));
                window.add(button);
                button.addMouseListener(this);
                buttonArray[i][j] = button;
            }
        }
    }

    public void show() {
        window.setVisible(true);
    }

    public void setGameboard(Gameboard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("jklsdhf");
        if ("Hallo".equals(e.getActionCommand())) {
            System.out.println("hallo");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 2) {
            System.out.println("middle maustaste");
        }
        if (e.getButton() == 1) {
            System.out.println("linke maustaste");
        }
        if (e.getButton() == 3) {
            System.out.println("rechte maustaste");
        }
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray.length; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    System.out.println("Reihe:" + i + " Spalte:" + j);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
