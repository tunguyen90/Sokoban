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
