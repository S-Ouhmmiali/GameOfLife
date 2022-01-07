public class Cell {
    private int indexI;
    private int indexJ;
    private int state;  //Alive : 1, Dead : 0

    public Cell(int i, int j, int state){
        this.indexI = i;
        this.indexJ = j;
        this.state = state; 
    }

    // getters and setters
    public int getState(){
        return this.state;
    }
    public int getIndexI(){
        return this.indexI;
    }
    public int getIndexJ(){
        return this.indexJ;
    }
    public void setState(int newState){
        this.state = newState;
    }

    // Get if the cell is alive or dead
    public boolean isAlive(){
        return (this.getState() == 1);
    }

    // Count Alive neighbors of the cell
    public int count_alive_neighbors(GameGrid grid,int N, int M){
        int i = this.indexI;
        int j = this.indexJ;
        if (i<N-1 && i>=1 && j>=1 && j<M-1){
            int aliveNeighbours = 0;
                for (int k = -1; k <= 1; k++){ 
                    for (int l = -1; l <= 1; l++){  
                        aliveNeighbours += grid.getCell(i + k,j + l).getState();
                    }
                }
        
            return aliveNeighbours - grid.getCell(i ,j ).getState();
        }else if (i==0){ 
            if(j==0){
                return grid.getCell(0 ,1 ).getState() + grid.getCell(1 ,0 ).getState() + grid.getCell(1 ,1 ).getState() ;
            } else if (j==M-1){
                return grid.getCell(0 ,M-2 ).getState() + grid.getCell(1 ,M-2).getState() + grid.getCell(1 ,M-1).getState() ;
            } else{
                return grid.getCell(0 ,j-1 ).getState() + grid.getCell(0 ,j+1 ).getState() + grid.getCell(1 ,j-1 ).getState() + grid.getCell(1 ,j ).getState() + grid.getCell(1 ,j+1 ).getState() ;
            }
        
        } else if (i==N-1){ 
            if(j==0){
                return grid.getCell(N-1 ,1 ).getState() + grid.getCell(N-2 ,0 ).getState() + grid.getCell(N-2 ,1 ).getState() ;
    
            } else if (j==M-1){
                return grid.getCell(N-2 ,M-2 ).getState() + grid.getCell(N-2 ,M-1 ).getState() + grid.getCell(N-1 ,M-2).getState() ;
            } else{
                return grid.getCell(N-1 ,j-1 ).getState() + grid.getCell(N-1 ,j+1 ).getState() + grid.getCell(N-2 ,j-1 ).getState() + grid.getCell(N-2 ,j ).getState() + grid.getCell(N-2 ,j+1 ).getState() ;
    
            }
        } else if(j==0){
            return grid.getCell(i-1 ,0 ).getState() + grid.getCell(i+1 ,0 ).getState() + grid.getCell(i-1,1 ).getState() + grid.getCell(i ,1 ).getState() + grid.getCell(i+1 ,1 ).getState() ;
    
    
        }else {
            return grid.getCell(i-1 ,M-1 ).getState() + grid.getCell(i+1 ,M-1).getState() + grid.getCell(i-1 ,M-2 ).getState() + grid.getCell(i ,M-2 ).getState() + grid.getCell(i+1 ,M-2).getState() ;
        }
        
    } 
    // Get the next state of the cell
    public int getNextState(GameGrid grid,int N,int M){
        int p = this.count_alive_neighbors(grid, N, M);
        if(p<2 && this.isAlive()) {
            return 0;
        } else if (p>4 && this.isAlive() ){
            return 0;
        }else if( p==3 && !this.isAlive()){
            return 1;
        } else {
            return this.getState();
        }
    }
    

}
