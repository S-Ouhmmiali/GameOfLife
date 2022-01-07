public class GameGrid {
    private int rows;
    private int cols;
    private Cell[][] grid ;

    public GameGrid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        for(int i=0; i<rows ;i ++){
            for(int j=0; j<cols; j++){
                this.grid[i][j] = new Cell(i,j,0);
            }
        }
        // Define the initial pattern of the grid
        int c = (this.rows - (this.rows%2))/2 +1;
        for(int m =0;m<this.cols;m++){
            this.grid[c][m] = new Cell(c,m,1);
            
        }
    }

    // Getters and setters
    public Cell getCell(int i, int j){
        return this.grid[i][j];
    }
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;

    }

    



}
