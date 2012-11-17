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
    
    /**
     * Returns the field standing on the position.
     * 
     * @param x width coordinate.
     * @param y height coordinate.
     * @return field state.
     */
    public FieldState getFieldStateOnPosition(final int x, final int y) {
        
        // because we have height on first cooridinate
        // and width on second
        return board[y][x];
        
    }
    
    /**
     * Sets the field state on the position. 
     * 
     * @param fieldState field state to set.
     * @param x width coordinate.
     * @param y height coordinate.
     */
    public void setFieldStateOnPosition(final FieldState fieldState, final int x, final int y) {
        
        board[y][x] = fieldState;
        
    }
    
    /**
     * Returns the point on the position.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the point with the field state on the position.
     */
    public Point getPoint(final int x, final int y) {
        
        Point point = new Point(x, y);
        
        FieldState fieldStateOnPosition = getFieldStateOnPosition(x, y);
        
        point.setFieldState(fieldStateOnPosition);
        
        return point;
        
    }
    
    /**
     * Gets field state connected to the point.
     * 
     * @param point interested point.
     */
    public void getFieldStateToPoint(final Point point) {
        
        int x = point.getX();
        int y = point.getY();
        
        FieldState fieldStateToGet = getFieldStateOnPosition(x, y);
        
        point.setFieldState(fieldStateToGet);
        
    }
    
    /**
     * Sets field state on position to value from point.
     * 
     * @param point interested point.
     */
    public void setFieldStateFromPoint(final Point point) {
        
        int x = point.getX();
        int y = point.getY();
        
        FieldState fieldStateToSet = getFieldStateOnPosition(x, y);
        
        setFieldStateOnPosition(fieldStateToSet, x, y);
        
    }
    
}
