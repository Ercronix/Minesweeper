
import java.util.Random;

public class Gameboard {

    private final int ROWS = 10, COLS = 10;
    private final int[][] feld = new int[ROWS][COLS];

    Random rand = new Random();

    public int getFieldHeightOrWidthIdkDepends(){
        return ROWS;
    }
    public int getCellValue(int row, int col){
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
}
