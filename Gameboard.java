public class Gameboard {
    private final int ROWS=10, COLS=10;
    private int[][] feld = new int[ROWS][COLS];
    
    public void fillarray(){
        for(int i=0; i<feld.length; i++){
            for(int j=0; j<feld[i].length; j++){
                feld[i][j] = i;
            }
        }
    }
    
    public void printArray(){
        for(int i=0; i<feld.length; i++){
            for(int j=0; j<feld[i].length; j++){
                System.out.print("["+feld[i][j]+"]");
            }
            System.out.println("");
        }
    }
}
