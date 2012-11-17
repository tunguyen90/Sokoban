package pl.edu.pw.elka.sokoban.lib.mockup;


/**
 * Class representing point on the mockup or board.
 */
public class Point {

    private int x;
    private int y;
    
    private FieldState fieldState;
    
    /**
     * Constructs the new point.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(final int x, final int y) {
        
        this.x = x;
        this.y = y;
        
    }
    
    /**
     * Returns the x coordinate.
     * 
     * @return the x coordinate.
     */
    public int getX() {
    
        return x;
    
    }
    
    /**
     * Sets the x coordinate.
     * 
     * @param x the x coordinate to set.
     */
    public void setX(final int x) {
    
        this.x = x;
    
    }
    
    /**
     * Returns the y coordinate.
     * 
     * @return the y coordinate.
     */
    public int getY() {
    
        return y;
    
    }
    
    /**
     * Sets the y coordinate.
     * 
     * @param y the y coordinate to set.
     */
    public void setY(final int y) {
    
        this.y = y;
    
    }
    
    /**
     * Returns the field state.
     * 
     * @return the field state.
     */
    public FieldState getFieldState() {
    
        return fieldState;
    
    }
    
    /**
     * Sets the field state.
     * 
     * @param fieldState the field state to set.
     */
    public void setFieldState(final FieldState fieldState) {
    
        this.fieldState = fieldState;
    
    }
    
}
