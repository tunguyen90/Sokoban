package pl.edu.pw.elka.sokoban.model;

import java.util.LinkedList;
import java.util.List;

import pl.edu.pw.elka.sokoban.lib.mockup.FieldState;
import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;
import pl.edu.pw.elka.sokoban.lib.mockup.Point;


/**
 * Class representing sokoban map.
 */
class SokobanMap {
    
    private Point startPoint;
    
    private List<Point> listOfCrates;
    private List<Point> listOfGoals;
    
    private Mockup board;
    
    /**
     * Constrcuts the new sokoban map.
     * 
     * @param board mockup of the board.
     */
    public SokobanMap(final Mockup board) {
        
        this.board = board;
        
        listOfCrates = new LinkedList<Point>();
        listOfGoals = new LinkedList<Point>();
        
        init();
        
    }
    
    /**
     * Returns the point.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the point.
     */
    public Point getPoint(final int x, final int y) {
        
        return board.getPoint(x, y);
        
    }
    
    /**
     * Returns the start point.
     * 
     * @return the start point.
     */
    public Point getStartPoint() {
        
        return startPoint;
        
    }
    
    /**
     * Returns the list of the crates.
     * 
     * @return the list of the crates.
     */
    public List<Point> getListOfCrates() {
        
        return listOfCrates;
        
    }
    
    /**
     * Returns the list of the goals.
     * 
     * @return the list of the goals.
     */
    public List<Point> getListOfGoals() {

        return listOfGoals;
        
    }
    
    /**
     * Returns the map width.
     * 
     * @return the map width.
     */
    public int getWidth() {
        
        return board.getWidth();
        
    }
    
    /**
     * Returns the map height.
     * 
     * @return the map height.
     */
    public int getHeight() {
        
        return board.getHeight();
        
    }
    
    /**
     * Initializes the map with start point, list of boxes and list of goals.
     */
    private void init() {
        
        for(int x = 0; x < board.getWidth(); x++) {
            
            for(int y = 0; y < board.getHeight(); y++) {
                
                Point point = board.getPoint(x, y);
                
                FieldState fieldStateOnPoint = point.getFieldState();
                
                if(fieldStateOnPoint == FieldState.SOKOBAN)
                    startPoint = point;
                
                else if(fieldStateOnPoint == FieldState.CRATE)
                    listOfCrates.add(point);
                
                else if (fieldStateOnPoint == FieldState.GOAL)
                    listOfGoals.add(point);
                
            }
            
        }
        
    }
    
    /**
     * Returns the array representing reachability of the points from start poistion.
     * 
     * @param startPoint point where sokoban stays.
     * @return the reachability array.
     */
    public Boolean[][] findReachablePoints(final Point startPoint) {
        
        // null == not visited
        // true == reachable
        // false == not reachable
        Boolean[][] reachablePoints = new Boolean[board.getHeight()][];
        
        for(int i = 0; i < board.getHeight(); i++)
            reachablePoints[i] = new Boolean[board.getWidth()];
        
        isReachable(startPoint, reachablePoints);
        
        for(int i = 0; i < board.getHeight(); i++) {
            
            for(int j = 0; j < board.getWidth(); j++) {
                
                if(reachablePoints[i][j] == null)
                    reachablePoints[i][j] = false;
                
            }
            
        }
        
        return reachablePoints;
        
    }
    
    private void isReachable(final Point point, final Boolean[][] reachablePoints) {
        
        // visited field, we don't have to check its
        if(reachablePoints[point.getY()][point.getX()] != null)
            return;
        
        FieldState currentFieldState = board.getFieldStateToPoint(point);

        if(isStayableField(currentFieldState)) {
        
            reachablePoints[point.getY()][point.getX()] = true;
            
            Point bottomPoint = point.getBottomPoint();
            Point leftPoint = point.getLeftPoint();
            Point rightPoint = point.getRightPoint();
            Point topPoint = point.getTopPoint();
            
            if(isPointOnBoard(bottomPoint))
                isReachable(bottomPoint, reachablePoints);
            
            if(isPointOnBoard(leftPoint))
                isReachable(leftPoint, reachablePoints);
            
            if(isPointOnBoard(rightPoint))
                isReachable(rightPoint, reachablePoints);
            
            if(isPointOnBoard(topPoint))
                isReachable(topPoint, reachablePoints);
            
        }
        
    }
    
    private boolean isPointOnBoard(final Point point) {
        
        if(point.getX() < 0)
            return false;
        
        if(point.getY() < 0)
            return false;
        
        if(point.getX() > getWidth() - 1) // starting number 0
            return false;
        
        if(point.getY() > getHeight() - 1) // starting number 0
            return false;
        
        return true;
        
    }
    
    /**
     * Returns true if sokoban can stay on this field state.
     * Reutrns true if field is free, goal, sokoban or sokoban on goal.
     * 
     * @param fieldState field state to check.
     * @return true if sokoban can stay on this field state.
     */
    public boolean isStayableField(final FieldState fieldState) {
     
        return fieldState == FieldState.FREE
                || fieldState == FieldState.GOAL
                || fieldState == FieldState.SOKOBAN
                || fieldState == FieldState.SOKOBAN_ON_GOAL;
        
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int y = 0; y < board.getHeight(); y++) {
            
            for(int x = 0; x < board.getWidth(); x++) {
                
                FieldState fieldState = board.getFieldStateOnPosition(x, y);
                
                stringBuilder.append(fieldState);
                
            }
            
            stringBuilder.append("\n");
            
        }
        
        return stringBuilder.toString();
        
    }

}
