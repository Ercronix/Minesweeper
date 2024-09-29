
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Gameboard {

    private final int ROWS = 10, COLS = 10;
    private final int[][] feld = new int[ROWS][COLS];

    Random rand = new Random();

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

    public void fillarray(int bombs) {
        int hamburger = 0;
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

    /**
     * Methode sucht bombe und zaehlt bei den umliegenden feldern eins hoch.
     */
    public void checkSurrBombs() {
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                if (feld[i][j] == 9) {
                    //durch die umliegenden felder iterieren
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            //Überprüfen ob index out of bounds
                            if (((i + k) >= 0 && (i + k) < feld.length && (j + l) >= 0 && (j + l) < feld[i].length && feld[i + k][j + l] != feld[i][j])) {
                                feld[i + k][j + l]++;
                            }
                        }
                    }
                }
            }
        }
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
        int bombflag = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                //Überprüfen ob index out of bounds
                if (((rows + k) >= 0 && (rows + k) < feld.length && (cols + l) >= 0 && (cols + l) < feld[rows].length) && !((rows + k == rows) && (cols + l == cols))) {
                    JButton neighboringButton = buttonArray[rows + k][cols + l];
                    if (neighboringButton.getIcon() != null && neighboringButton.getIcon().equals(flag)) {
                        System.out.println("has flag");
                        bombflag++;
                        System.out.println(bombflag);
                    }
                    if (feld[rows + k][cols + l] == 9 && !(neighboringButton.getIcon().equals(flag)) && bombflag > 0) {
                        window.gameOver();
                    }
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (((rows + i) >= 0 && (rows + i) < feld.length && (cols + j) >= 0 && (cols + j) < feld[rows].length) && !(j==0&&i==0)) {
                                JButton closeButton = buttonArray[rows + i][cols + j];
                                if (closeButton.getIcon() != null && bombflag > 0 && feld[rows + i][cols + j] != 9) {
                                    if (feld[rows+i][cols+j] == 0) {
                                        closeButton.setIcon(empty);
                                    }else if(feld[rows+i][cols+j] > 0 && feld[rows+i][cols+j] < 9){
                                        closeButton.setIcon(null);
                                        closeButton.setText(String.valueOf(feld[rows+i][cols+j]));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
