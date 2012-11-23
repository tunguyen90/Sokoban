package pl.edu.pw.elka.sokoban.lib.mockup;


/**
 * Mockup representing the board.
 */
public class Mockup {
    
    private Point sokobanPoint;

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
     * Returns the point where sokoban currently stands.
     * 
     * @return the point where sokoban currently stands.
     */
    public Point getSokobanPoint() {
        
        return sokobanPoint;
        
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
        
        setIfSokobanOnField(x, y);
        
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
     * @return current field state.
     */
    public FieldState getFieldStateToPoint(final Point point) {
        
        int x = point.getX();
        int y = point.getY();
        
        FieldState fieldStateToGet = getFieldStateOnPosition(x, y);
        
        point.setFieldState(fieldStateToGet);
        
        return fieldStateToGet;
        
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
    
    /**
     * Tells if the field is a brick.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is a brick.
     */
    public boolean isBrick(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.BRICK;
        
    }
    
    /**
     * Tells if the field is a brick.
     * 
     * @param point point to check.
     * @return if the field is a brick.
     */
    public boolean isBrick(final Point point) {
        
        return isBrick(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is a goal.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is a goal.
     */
    public boolean isGoal(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.GOAL;
        
    }
    
    /**
     * Tells if the field is a goal.
     * 
     * @param point point to check.
     * @return if the field is a goal.
     */
    public boolean isGoal(final Point point) {
        
        return isGoal(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is a crate.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is a crate.
     */
    public boolean isCrate(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.CRATE;
        
    }
    
    /**
     * Tells if the field is a crate.
     * 
     * @param point point to check.
     * @return if the field is a crate.
     */
    public boolean isCrate(final Point point) {
        
        return isCrate(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is sokoban.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is sokoban.
     */
    public boolean isSokoban(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.SOKOBAN;
        
    }
    
    /**
     * Tells if the field is sokoban.
     * 
     * @param point point to check.
     * @return if the field is sokoban.
     */
    public boolean isSokoban(final Point point) {
        
        return isSokoban(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is a crate on goal.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is a crate on goal.
     */
    public boolean isCrateOnGoal(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.CRATE_ON_GOAL;
        
    }
    
    /**
     * Tells if the field is a crate on goal.
     * 
     * @param point point to check.
     * @return if the field is a crate on goal.
     */
    public boolean isCrateOnGoal(final Point point) {
        
        return isCrateOnGoal(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is sokoban on goal.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is sokoban on goal.
     */
    public boolean isSokobanOnGoal(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.SOKOBAN_ON_GOAL;
        
    }
    
    /**
     * Tells if the field is sokoban on goal.
     * 
     * @param point point to check.
     * @return if the field is sokoban on goal.
     */
    public boolean isSokobanOnGoal(final Point point) {
        
        return isSokobanOnGoal(point.getX(), point.getY());
        
    }
    
    /**
     * Tells if the field is a free field.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return if the field is a free field.
     */
    public boolean isFree(final int x, final int y) {
        
        return getFieldStateOnPosition(x, y) == FieldState.FREE;
        
    }
    
    /**
     * Tells if the field is a free field.
     * 
     * @param point point to check.
     * @return if the field is a free field.
     */
    public boolean isFree(final Point point) {
        
        return isFree(point.getX(), point.getY());
        
    }
    
    private void setIfSokobanOnField(final int x, final int y) {
        
        if(isSokoban(x, y) || isSokobanOnGoal(x, y))
            sokobanPoint = new Point(x, y);
        
    }
    
}
