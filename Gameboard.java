
import java.util.Random;

public class Gameboard {

    private final int ROWS = 10, COLS = 10;
    private final int[][] feld = new int[ROWS][COLS];

    Random rand = new Random();

    public void fillarray(int bombs) {
        int hamburger = 0;
        for (int[] feld1 : feld) {
            for (int j = 0; j < feld1.length; j++) {
                feld1[j] = 0;
            }
        }
        while(hamburger<bombs){
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if(feld[row][col]==0){
                feld[row][col]=1;
                hamburger++;
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
