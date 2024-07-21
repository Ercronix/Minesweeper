



public class Controller {
    
    public static int rows=10, cols=10;
    public static int[][] feld = new int[rows][cols];
    public static void main(String[] args) {
        for(int i=0; i<feld.length; i++){
            for(int j=0; j<feld[i].length; j++){
                feld[i][j] = i*j;
            }
        }
        printArray();
    }

    public static void printArray(){
        for(int i=0; i<feld.length; i++){
            for(int j=0; j<feld[i].length; j++){
                System.out.print("["+feld[i][j]+"]");
            }
            System.out.println("");
        }
    }
}
