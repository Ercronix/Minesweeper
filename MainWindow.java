
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class MainWindow implements ActionListener, MouseListener {

    private JFrame window;
    private Gameboard board;
    private JButton[][] buttonArray;
    private final ImageIcon fieldicon = new ImageIcon(getClass().getResource("Images/ping1.png"));
    private final ImageIcon emptyfieldicon = new ImageIcon(getClass().getResource("Images/emptyfield.png"));
    private final ImageIcon flagicon = new ImageIcon(getClass().getResource("Images/t11.png"));
    private final ImageIcon bombicon = new ImageIcon(getClass().getResource("Images/t15.png"));

    private final ImageIcon number1Icon = new ImageIcon(getClass().getResource("Images/t1.png"));
    private final ImageIcon number2Icon = new ImageIcon(getClass().getResource("Images/t2.png"));
    private final ImageIcon number3Icon = new ImageIcon(getClass().getResource("Images/t3.png"));
    private final ImageIcon number4Icon = new ImageIcon(getClass().getResource("Images/t4.png"));
    private final ImageIcon number5Icon = new ImageIcon(getClass().getResource("Images/t5.png"));
    private final ImageIcon number6Icon = new ImageIcon(getClass().getResource("Images/t6.png"));
    private final ImageIcon number7Icon = new ImageIcon(getClass().getResource("Images/t7.png"));
    private final ImageIcon number8Icon = new ImageIcon(getClass().getResource("Images/t8.png"));

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
        this.window.setLayout(
                new GridLayout(board.getFieldHeightOrWidthIdkDepends(), board.getFieldHeightOrWidthIdkDepends()));
        buttonArray = new JButton[board.getFieldHeightOrWidthIdkDepends()][board.getFieldHeightOrWidthIdkDepends()];
        for (int i = 0; i < board.getFieldHeightOrWidthIdkDepends(); i++) {
            for (int j = 0; j < board.getFieldHeightOrWidthIdkDepends(); j++) {
                JButton button = new JButton();
                button.setText(null);
                button.setIcon(fieldicon);
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

    public boolean hasIconCheck(JButton button) {
        if (button.getIcon() != null)
            return true;
        else
            return false;
    }

    public ImageIcon getNumberIcons(int x) {
        switch (x) {
            case 1:
                return number1Icon;
            case 2:
                return number2Icon;
            case 3:
                return number3Icon;
            case 4:
                return number4Icon;
            case 5:
                return number5Icon;
            case 6:
                return number6Icon;
            case 7:
                return number7Icon;
            case 8:
                return number8Icon;
            default:
                return null;
        }
    }

    public void gameOver() {
        System.out.println("eplosion");
        board.showBombs(bombicon, buttonArray);
        JOptionPane.showMessageDialog(window, "Game Over! You clicked on a bomb.");
        System.exit(0);
    }

    private void clickOnNumber(MouseEvent e) {
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray.length; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    board.checkLeftClick(i, j, buttonArray, flagicon, emptyfieldicon);
                }
            }
        }
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
            // System.out.println("linke maustaste");
            if (hasIconCheck(((JButton) e.getSource()))) {
                clickOnNumber(e);
            }
            for (int i = 0; i < buttonArray.length; i++) {
                for (int j = 0; j < buttonArray.length; j++) {
                    if (e.getSource() == buttonArray[i][j] && (((JButton) e.getSource()).getIcon() == fieldicon)) {
                        if ((board.getCellValue(i, j) == 9)) {
                            gameOver();
                        } else {
                            if (board.getCellValue(i, j) == 0) {
                                for (JButton[] buttonArray1 : buttonArray) {
                                    for (int l = 0; l < buttonArray.length; l++) {
                                        if (e.getSource() == buttonArray1[l]) {
                                            board.noNearbyBombExpose(i, j, buttonArray, emptyfieldicon);
                                        }
                                    }
                                }
                                buttonArray[i][j].setIcon(emptyfieldicon);
                                i = buttonArray.length;
                                j = buttonArray.length;
                            } else {
                                buttonArray[i][j].setIcon(getNumberIcons(board.getCellValue(i, j)));
                                //buttonArray[i][j].setText(String.valueOf(board.getCellValue(i, j)));
                                //buttonArray[i][j].setIcon(null);
                                i = buttonArray.length;
                                j = buttonArray.length;
                            }
                        }
                    }
                }
            }
        }
        if (e.getButton() == 3) {
            // System.out.println("rechte maustaste");
            if (((JButton) e.getSource()).getIcon() != flagicon && hasIconCheck(((JButton) e.getSource()))) {
                ((JButton) e.getSource()).setIcon(flagicon);
            } else if (hasIconCheck(((JButton) e.getSource()))) {
                ((JButton) e.getSource()).setIcon(fieldicon);
            }
        }
    }

    /*
     * for (int i = 0; i < buttonArray.length; i++) {
     * for (int j = 0; j < buttonArray.length; j++) {
     * if (e.getSource() == buttonArray[i][j]) {
     * System.out.println("Reihe:" + i + " Spalte:" + j);
     * }
     * }
     * }
     */
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
