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
    
    Mockup board;
    
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
    
    private boolean isShiftPossible(final Point cratePoint, final Direction direction) {

        Point sokobanDestinationPoint = findSokobanPointBeforePush(cratePoint, direction);
        
        if(!isReachable(sokobanDestinationPoint))
            return false;
        
        Point crateDestinationPoint = findCratePointAfterPush(cratePoint, direction);
        
        if(!isStayablePoint(crateDestinationPoint))
                return false;
        
    }
    
    private boolean isCorner(final Point point) {
        
        Point topPoint = point.getTopPoint();
        Point bottomPoint = point.getBottomPoint();
        Point leftPoint = point.getLeftPoint();
        Point rightPoint = point.getRightPoint();
        
        boolean isTopStayable = isStayablePoint(topPoint);
        boolean isBottomStayable = isStayablePoint(bottomPoint);
        boolean isLeftStayable = isStayablePoint(leftPoint);
        boolean isRightStayable = isStayablePoint(rightPoint);
        
        if(!isTopStayable && !isLeftStayable)
            return false;
        
        if(!isTopStayable && !isRightStayable)
            return false;
        
        if(!isBottomStayable && !isLeftStayable)
            return false;
        
        if(!isBottomStayable && !isRightStayable)
            return false;
        
        return true;
        
    }
    
    private boolean isReachable(final Point point) {
        
        Boolean[][] reachablePoints = findReachablePoints(point);
        
        return reachablePoints[point.getX()][point.getY()];
        
    }

    /**
     * Returns the position where sokoban must stand to push a crate to a direction.
     * 
     * @param cratePoint point where the crate stands.
     * @param direction direcetion which we use.
     * @return the position where sokoban must stand to push a crate to a direction.
     */
    private Point findSokobanPointBeforePush(final Point cratePoint,
            final Direction direction) {

        Point sokobanDestinationPoint = null;
        
        if(direction == Direction.LEFT)
            
            sokobanDestinationPoint = cratePoint.getRightPoint();
        
        else if(direction == Direction.RIGHT)
            
            sokobanDestinationPoint = cratePoint.getLeftPoint();
        
        else if(direction == Direction.UP)
            
            sokobanDestinationPoint = cratePoint.getBottomPoint();
        
        else if(direction == Direction.DOWN)
            
            sokobanDestinationPoint = cratePoint.getTopPoint();
        
        return sokobanDestinationPoint;
        
    }
    
    /**
     * Returns the position where crate will be standing after push.
     * 
     * @param cratePoint point where the crate stands.
     * @param direction direcetion which we use.
     * @return the position where crate will be standing after push.
     */
    private Point findCratePointAfterPush(final Point cratePoint,
            final Direction direction) {

        Point sokobanDestinationPoint = null;
        
        if(direction == Direction.LEFT)
            
            sokobanDestinationPoint = cratePoint.getLeftPoint();
        
        else if(direction == Direction.RIGHT)
            
            sokobanDestinationPoint = cratePoint.getRightPoint();
        
        else if(direction == Direction.UP)
            
            sokobanDestinationPoint = cratePoint.getTopPoint();
        
        else if(direction == Direction.DOWN)
            
            sokobanDestinationPoint = cratePoint.getBottomPoint();
        
        return sokobanDestinationPoint;
        
    }
    
    /**
     * Returns number of crates on goal.
     * 
     * @param mockup mockup to check.
     * @return number of crates on goal.
     */
    private int getNumberOfCratesOnGoal(final Mockup mockup) {
        
        int numberOfCratesOnGoal = 0;
        
        for(Point cratePoint : listOfCrates) {
            
            if(mockup.isSokobanOnGoal(cratePoint))
                numberOfCratesOnGoal++;
            
        }
        
        return numberOfCratesOnGoal;
        
    }
    
    /**
     * Returns the array representing reachability of the points from start poistion.
     * 
     * @param startPoint point where sokoban stays.
     * @return the reachability array.
     */
    Boolean[][] findReachablePoints(final Point startPoint) {
        
        // null == not visited
        // true == reachable
        // false == not reachable
        Boolean[][] reachablePoints = new Boolean[board.getHeight()][];
        
        for(int i = 0; i < board.getHeight(); i++)
            reachablePoints[i] = new Boolean[board.getWidth()];
        
        isReachableInRecursion(startPoint, reachablePoints);
        
        for(int i = 0; i < board.getHeight(); i++) {
            
            for(int j = 0; j < board.getWidth(); j++) {
                
                if(reachablePoints[i][j] == null)
                    reachablePoints[i][j] = false;
                
            }
            
        }
        
        return reachablePoints;
        
    }
    
    private void isReachableInRecursion(final Point point, final Boolean[][] reachablePoints) {
        
        // visited field, we don't have to check it
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
                isReachableInRecursion(bottomPoint, reachablePoints);
            
            if(isPointOnBoard(leftPoint))
                isReachableInRecursion(leftPoint, reachablePoints);
            
            if(isPointOnBoard(rightPoint))
                isReachableInRecursion(rightPoint, reachablePoints);
            
            if(isPointOnBoard(topPoint))
                isReachableInRecursion(topPoint, reachablePoints);
            
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
    private boolean isStayableField(final FieldState fieldState) {
     
        return fieldState == FieldState.FREE
                || fieldState == FieldState.GOAL
                || fieldState == FieldState.SOKOBAN
                || fieldState == FieldState.SOKOBAN_ON_GOAL;
        
    }
    
    private boolean isStayablePoint(final Point point) {
        
        return isPointOnBoard(point) && isStayableField(point.getFieldState());
        
    }
    
    /**
     * Returns the mockup.
     * 
     * @return the mockup of this sokoban map.
     */
    public Mockup getMockup() {
        
        return board;
        
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

    /**
     * Returns the map height.
     * 
     * @return the map height.
     */
    public int getHeight() {
        
        return board.getHeight();
        
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
     * Returns the start point.
     * 
     * @return the start point.
     */
    public Point getStartPoint() {
        
        return startPoint;
        
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

}
