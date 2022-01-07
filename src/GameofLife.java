import java.awt.*;    
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
public class GameofLife {    
private int rows=5;
private int cols=5;
private  JButton[][] cells = new JButton[rows][cols];
private GameGrid grid;

GameofLife(GameGrid grid, JFrame fr){
    rows = grid.getRows();
    cols = grid.getCols();
    this.grid = grid;
    cells = new JButton[rows][cols];
    Container container = fr.getContentPane();
	container.setLayout(new GridLayout(rows,cols));
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int ss = grid.getCell(i, j).getState();
            cells[i][j] = new JButton();
            cells[i][j].setPreferredSize(new Dimension(3, 3));
            cells[i][j].setBorder( new LineBorder(Color.BLACK) );
            cells[i][j].setOpaque(true);
            if (ss == 1){
                cells[i][j].setBackground(Color.green);
                cells[i][j].setBorderPainted(false);
            }
            container.add(cells[i][j]);
        }
    }
    // Adding mouse listeners to every cell
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            cells[i][j].addMouseListener(new ActionClick(i, j));
        }
    }

    // setting grid layout
    fr.setLayout(new GridLayout(rows,cols));    
    fr.setVisible(true);    
}   


private class ActionClick extends MouseAdapter {
    private int x;	// abscisse de la case
    private int y;	// ordonnée de la case

    public ActionClick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int n = grid.getCell(x,y).getState();

        if (n==1){ // Old state = Alive
            grid.getCell(x,y).setState(0);
            cells[x][y].setBackground(null);
            cells[x][y].setBorderPainted(true);
        }else{  //Old state = Dead
            grid.getCell(x,y).setState(1);
            cells[x][y].setBackground(Color.GREEN);
            cells[x][y].setBorderPainted(false);
        }
        

    }
}

    public static void main(String[] args) {  
        GameGrid grid = new GameGrid(41, 41);
        JFrame f = new JFrame("Game of Life"); 
        f.setSize(1200, 800);
        new GameofLife(grid,f);
        int rows = grid.getRows();
        int cols = grid.getCols();
        for (int k = 0; k<100; k++){   // 100 iteration
            GameGrid futureGrid= new GameGrid(rows, cols);
            for(int i=0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    Cell c = grid.getCell(i, j);
                    int p = c.getNextState(grid,rows,cols);
                    futureGrid.getCell(i, j).setState(p);
                }
            }
            grid = futureGrid;
            f.setContentPane( new JPanel() );
            new GameofLife(grid,f);
            
        }
    }    
}    
