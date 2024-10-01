
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Gameboard {

    private int bombamount = 0;
    private final int ROWS = 10, COLS = 10;
    private final int[][] feld = new int[ROWS][COLS];

    Random rand = new Random();
    public int wincheckNumber;
    private MainWindow window;

    public void setMainWindow(MainWindow window) {
        this.window = window;
    }

    public int getFieldHeightOrWidthIdkDepends() {
        return ROWS;
    }

    public int getCellValue(int row, int col) {
        return feld[row][col];
    }
    public int getBoardSize(){
        return ROWS;
    }
    public void fillArray(int bombs) {
        int hamburger = 0;
        this.bombamount = bombs;
        for (int[] feld1 : feld) {
            for (int j = 0; j < feld1.length; j++) {
                feld1[j] = 0;
            }
        }
        while (hamburger < bombs) {
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if (feld[row][col] == 0) {
                feld[row][col] = 9;
                hamburger++;
            }
        }
    }
    public int getBombAmount(){
        return bombamount;
    }
    /**
     * Methode sucht bombe und zaehlt bei den umliegenden feldern eins hoch.
     */
    public void checkSurrBombs() {
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                if (feld[i][j] == 9) {
                    // durch die umliegenden felder iterieren
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            // Überprüfen ob index out of bounds
                            if (((i + k) >= 0 && (i + k) < feld.length && (j + l) >= 0 && (j + l) < feld[i].length
                                    && feld[i + k][j + l] != feld[i][j])) {
                                feld[i + k][j + l]++;
                            }
                        }
                    }
                }
            }
        }
    }

    public int checkSurrBombCount(int rows, int cols) {
        int x = 0;
        // durch die umliegenden felder iterieren
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                // Überprüfen ob index out of bounds
                if (((rows + k) >= 0 && (rows + k) < feld.length && (cols + l) >= 0 && (cols + l) < feld[rows].length
                        && feld[rows + k][cols + l] != feld[rows][cols])) {
                    if (feld[rows + k][cols + l] == 9)
                        x++;
                }
            }
        }
        return x;
    }

    public void printArray() {
        for (int[] feld1 : feld) {
            for (int element : feld1) {
                System.out.print("[" + element + "]");
            }
            System.out.println("");
        }
    }

    public void checkLeftClick(int rows, int cols, JButton[][] buttonArray, ImageIcon flag, ImageIcon empty) {
        int bombcount = checkSurrBombCount(rows, cols);
        int bombchecked = 0;
        int flagAmount = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                // Überprüfen ob index out of bounds
                if (((rows + k) >= 0 && (rows + k) < feld.length && (cols + l) >= 0 && (cols + l) < feld[rows].length)
                        && !((rows + k == rows) && (cols + l == cols))) {
                    JButton neighboringButton = buttonArray[rows + k][cols + l];
                    // wenn ich auf eine Zahl clicke und eine falgge existiert
                    if (window.hasIconCheck(neighboringButton) && neighboringButton.getIcon().equals(flag)
                            && feld[rows + k][cols + l] == 9) {
                        System.out.println("has right flag");
                        bombchecked++;
                    } else if (window.hasIconCheck(neighboringButton) && neighboringButton.getIcon().equals(flag)
                            && feld[rows + k][cols + l] != 9) {
                        flagAmount++;
                        System.out.println("has wrong flag");
                    }
                    // Wenn eine falsche flagge gesetzt ist sprich flagAmount ist größer als 0
                    if (flagAmount > 0 && bombcount == (flagAmount + bombchecked)) {
                        window.gameOver();
                    }
                    // die umliegenden felder freilegen
                    if (bombchecked == bombcount) {
                        fieldexpose(rows, cols, buttonArray, bombcount, empty);
                    }
                }
            }
        }
    }

    private void fieldexpose(int rows, int cols, JButton[][] buttonArray, int bombcount, ImageIcon empty) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                // Check if index out of bounds
                if (((rows + i) >= 0 && (rows + i) < feld.length && (cols + j) >= 0 && (cols + j) < feld[rows].length)
                        && !(j == 0 && i == 0)) {
                    JButton closeButton = buttonArray[rows + i][cols + j];
                    // wenn ein Icon existiert und das feld keine Bombe ist
                    if (window.hasIconCheck(closeButton) && bombcount > 0 && feld[rows + i][cols + j] != 9) {
                        // wenn die Zahl im ZahlenArray 0 ist also ein leeres feld dann "leeresfeld"
                        // Icon
                        if (feld[rows + i][cols + j] == 0) {
                            noNearbyBombExpose(rows + i, cols + j, buttonArray, empty);
                            closeButton.setIcon(empty);
                            // wenn eine Zahl vorhanden ist die nicht 9 ist
                        } else if (feld[rows + i][cols + j] > 0 && feld[rows + i][cols + j] < 9) {
                            closeButton.setIcon(window.getNumberIcons(feld[rows + i][cols + j]));
                            // closeButton.setIcon(null);
                            // closeButton.setText(String.valueOf(feld[rows + i][cols + j]));
                        }
                    }
                }
            }
        }
    }

    public void noNearbyBombExpose(int rows, int cols, JButton[][] buttonArray, ImageIcon empty) {
        if (((rows) < 0 || (rows) >= feld.length || (cols) < 0 || (cols) >= feld[rows].length)) {
            return;
        }
        if (buttonArray[rows][cols].getIcon() == empty)
            return;

        JButton closeButton = buttonArray[rows][cols];
        // wenn ein Icon existiert und das feld keine Bombe ist
        if (window.hasIconCheck(closeButton)) {
            // wenn die Zahl im ZahlenArray 0 ist also ein leeres feld dann "leeresfeld"
            // Icon
            if (feld[rows][cols] == 0) {
                closeButton.setIcon(empty);
                // wenn eine Zahl vorhanden ist die nicht 9 ist
            } else if (feld[rows][cols] > 0) {
                closeButton.setIcon(window.getNumberIcons(feld[rows][cols]));
                // closeButton.setIcon(null);
                // closeButton.setText(String.valueOf(feld[rows][cols]));
                return;
            }
        } else
            return;

        noNearbyBombExpose(rows - 1, cols - 1, buttonArray, empty);
        noNearbyBombExpose(rows - 1, cols, buttonArray, empty);
        noNearbyBombExpose(rows - 1, cols + 1, buttonArray, empty);

        noNearbyBombExpose(rows, cols - 1, buttonArray, empty);
        noNearbyBombExpose(rows, cols + 1, buttonArray, empty);

        noNearbyBombExpose(rows + 1, cols - 1, buttonArray, empty);
        noNearbyBombExpose(rows + 1, cols, buttonArray, empty);
        noNearbyBombExpose(rows + 1, cols + 1, buttonArray, empty);
    }

    public void showBombs(ImageIcon bomb, JButton[][] buttonArray) {
        int amount = 0;
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                if (feld[i][j] == 9) {
                    buttonArray[i][j].setIcon(bomb);
                    amount++;
                }
                if (amount == bombamount)
                    return;
            }
        }
    }
}
