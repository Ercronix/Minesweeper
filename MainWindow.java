
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class MainWindow implements ActionListener, MouseListener {

    private JPanel boardPanel;
    private JFrame window;
    private Gameboard board;
    private JButton[][] buttonArray;
    private JMenuBar menuBar;
    private JTextField bombsTextArea;
    private JTextField sizeTextArea;
    private int flagsUsed = 0;
    private JButton resetButton;
    private JButton newBoard;
    private JPanel buttonBar;
    private final ImageIcon fieldicon = new ImageIcon(getClass().getResource("Images/ping1.png"));
    private final ImageIcon emptyfieldicon = new ImageIcon(getClass().getResource("Images/emptyfield.png"));
    private final ImageIcon emptyfieldicon2 = new ImageIcon(getClass().getResource("Images/t9.png"));
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
        this.menuBar = new JMenuBar();
        this.boardPanel = new JPanel();
        this.buttonBar = new JPanel();
        this.bombsTextArea = new JTextField(String.valueOf(board.getBombAmount()));
        this.sizeTextArea = new JTextField(String.valueOf(board.getBoardSize()));
        this.window.setTitle("Minesweeper");
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setSize(800, 800);
        this.window.setLocationRelativeTo(null);
        this.resetButton = new JButton();
        this.newBoard = new JButton();
        window.setLayout(new GridBagLayout());
        window.setJMenuBar(menuBar);
        window.add(boardPanel);
        menuBar.add(bombsTextArea);
        menuBar.add(sizeTextArea);
        menuBar.add(buttonBar);
        buttonBar.add(resetButton);
        buttonBar.add(newBoard);
        addButtons();

        bombsTextArea.addActionListener((ActionEvent e) -> {
            updateBombs();
        });
        sizeTextArea.addActionListener((ActionEvent e) -> {
            updateAreaSize();
        });
        resetButton.addActionListener((ActionEvent e) -> {
            refreshBoard();
        });
        newBoard.addActionListener((ActionEvent e) -> {
            board.setBoardSize(board.getBoardSize());
            refreshBoard();
            board.printArray();
        });
    }

    public void addButtons() {
        int boardSize = board.boardSize();
        this.boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        buttonArray = new JButton[boardSize][boardSize];
        resetButton.setText("Reset");
        newBoard.setText("New Board");
        for (int i = 0; i < board.boardSize(); i++) {
            for (int j = 0; j < board.boardSize(); j++) {
                JButton button = new JButton();
                button.setText(null);
                button.setIcon(fieldicon);
                button.setPreferredSize(new Dimension(16, 16));
                boardPanel.add(button);
                button.addMouseListener(this);
                button.setBackground(Color.LIGHT_GRAY); // Change background color
                button.setBorderPainted(false);
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
        return button.getIcon() != null;
    }

    public boolean hasNumberIconCheck(JButton button) {
        if (button.getIcon() == emptyfieldicon || button.getIcon() == fieldicon || button.getIcon() == flagicon)
            return false;
        return true;
    }

    public ImageIcon getNumberIcons(int x) {
        return switch (x) {
            case 1 ->
                number1Icon;
            case 2 ->
                number2Icon;
            case 3 ->
                number3Icon;
            case 4 ->
                number4Icon;
            case 5 ->
                number5Icon;
            case 6 ->
                number6Icon;
            case 7 ->
                number7Icon;
            case 8 ->
                number8Icon;
            default ->
                null;
        };
    }

    public void gameOver() {
        System.out.println("eplosion");
        board.showBombs(bombicon, buttonArray);
        JOptionPane.showMessageDialog(window, "Game Over! You clicked on a bomb.");
        board.setBoardSize(board.getBoardSize());
        refreshBoard();
        board.printArray();
    }

    private void clickOnNumber(MouseEvent e) {
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray.length; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    board.checkLeftClick(i, j, buttonArray, flagicon, emptyfieldicon);
                    return;
                }
            }
        }
    }

    private void updateBombs() {
        try {
            int newBombs = Integer.parseInt(bombsTextArea.getText());
            int boardSize = board.boardSize();
            if (newBombs >= 0 && newBombs < (boardSize * boardSize)) { // Ensure valid number of
                board.fillArray(newBombs); // Update the bomb amount in Gameboard
                board.checkSurrBombs(); // Recalculate surrounding bombs
                JOptionPane.showMessageDialog(window, "Bombs set to " + newBombs);
                resetButtonIcons();
            } else {
                JOptionPane.showMessageDialog(window, "Please enter a valid number of bombs.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window, "Invalid input. Please enter a number.");
        }
    }

    private void updateAreaSize() {
        try {
            int newSize = Integer.parseInt(sizeTextArea.getText());
            if (newSize >= 5 && newSize <= 30) { // Ensure valid range
                JOptionPane.showMessageDialog(window, "Size set to " + newSize);
                board.setBoardSize(newSize);
                refreshBoard(); // Update the UI
            } else {
                JOptionPane.showMessageDialog(window, "Please enter a size between 5 and 30.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window, "Invalid input. Please enter a number.");
        }
    }

    private void refreshBoard() {
        boardPanel.removeAll(); // Clear existing buttons
        addButtons(); // Re-add buttons for new size
        boardPanel.revalidate(); // Refresh the layout
        boardPanel.repaint(); // Repaint the panel to update the UI
    }

    private void resetButtonIcons() {
        for (JButton[] buttonArray1 : buttonArray) {
            for (JButton button : buttonArray1) {
                button.setIcon(fieldicon); // Reset icons to field icon
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
                                return;
                            } else {
                                buttonArray[i][j].setIcon(getNumberIcons(board.getCellValue(i, j)));
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (e.getButton() == 3) {
            // System.out.println("rechte maustaste");
            if (((JButton) e.getSource()).getIcon() != flagicon && hasIconCheck(((JButton) e.getSource()))
                    && ((JButton) e.getSource()).getIcon() == fieldicon) {
                ((JButton) e.getSource()).setIcon(flagicon);
                flagsUsed++; // Increment flagsUsed when a flag is placed
                checkWinCondition(); // Check win condition after placing a flag
            } else if (hasIconCheck(((JButton) e.getSource())) && ((JButton) e.getSource()).getIcon() == flagicon) {
                ((JButton) e.getSource()).setIcon(fieldicon);
                flagsUsed--; // Decrement flagsUsed when a flag is removed
            }
        }
    }

    /*
     * Ausgabe welches Feld geclickt wurde
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
        if (e.getButton() == 1)
            for (int i = 0; i < buttonArray.length; i++) {
                for (int j = 0; j < buttonArray.length; j++) {
                    if (e.getSource() == buttonArray[i][j] && buttonArray[i][j].getIcon() != flagicon
                            && buttonArray[i][j].getIcon() != fieldicon
                            && buttonArray[i][j].getIcon() != emptyfieldicon) {
                        int[][] offsets = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                                { 1, 1 } };
                        for (int[] offset : offsets) {
                            int k = offset[0];
                            int l = offset[1];
                            // Check if index is out of bounds
                            if (((i + k) >= 0 && (i + k) < buttonArray.length && (j + l) >= 0
                                    && (j + l) < buttonArray[i].length
                                    && buttonArray[i + k][j + l] != buttonArray[i][j])) {
                                if (buttonArray[i + k][j + l].getIcon() == fieldicon)
                                    buttonArray[i + k][j + l].setIcon(emptyfieldicon2);
                            }
                        }
                        return;
                    }
                }
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1)
            for (int i = 0; i < buttonArray.length; i++) {
                for (int j = 0; j < buttonArray.length; j++) {
                    if (e.getSource() == buttonArray[i][j]) {
                        int[][] offsets = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                                { 1, 1 } };
                        for (int[] offset : offsets) {
                            int k = offset[0];
                            int l = offset[1];
                            // Check if index is out of bounds
                            if (((i + k) >= 0 && (i + k) < buttonArray.length && (j + l) >= 0
                                    && (j + l) < buttonArray[i].length
                                    && buttonArray[i + k][j + l] != buttonArray[i][j])) {
                                if (buttonArray[i + k][j + l].getIcon() == emptyfieldicon2) {
                                    buttonArray[i + k][j + l].setIcon(fieldicon);
                                }
                            }
                        }
                        return;
                    }
                }
            }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void checkWinCondition() {
        int bombsCount = board.getBombAmount();
        int correctFlagsCount = 0; 
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if (buttonArray[i][j].getIcon() == flagicon) { 
                    if (board.getCellValue(i, j) == 9) { 
                        correctFlagsCount++;
                    }
                }
            }
        }

        if (flagsUsed == bombsCount && correctFlagsCount == bombsCount) {
            JOptionPane.showMessageDialog(window, "Congratulations! You've correctly marked all bombs!");
        }
    }
}
