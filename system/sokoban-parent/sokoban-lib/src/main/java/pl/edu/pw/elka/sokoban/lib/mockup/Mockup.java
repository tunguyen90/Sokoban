package pl.edu.pw.elka.sokoban.lib.mockup;


/**
 * Mockup representing the board.
 */
public class Mockup {

    private FieldState[][] board;
    
    /**
     * Constructs empty board mockup.
     * 
     * @param width width of the board. 
     * @param height height of the board.
     */
    public Mockup(final int width, final int height) {
        
        board = new FieldState[height][];

        for(int i = 0; i < height; i++) {
            
            board[i] = new FieldState[width];
            
        }

    }

    /**
     * Returns the width of the board.
     * 
     * @return the width of the board.
     */
    public int getWidth() {
        
        return board[0].length;
        
    }
    
    /**
     * Returns the height of the board.
     * 
     * @return the height of the board.
     */
    public int getHeight() {
        
        return board.length;
        
    }
    
}
