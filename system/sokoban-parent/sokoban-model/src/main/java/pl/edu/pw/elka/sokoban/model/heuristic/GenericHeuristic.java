package pl.edu.pw.elka.sokoban.model.heuristic;

import java.util.List;

import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;
import pl.edu.pw.elka.sokoban.lib.mockup.Point;

/**
 * Generic heuristic.
 */
public abstract class GenericHeuristic {

    /**
     * Returns the distance between points using Pitagoras equation.
     * 
     * @param startingPoint starting point.
     * @param endingPoint ending point.
     * @return the distance.
     */
    public abstract int getDistance(Point startingPoint, Point endingPoint);

    /**
     * Returns the distance between points using Pitagoras equation.
     * 
     * @param startingX starting x coordinate.
     * @param startingY starting y coordinate.
     * @param endingX ending x coordinate.
     * @param endingY ending y coordinate.
     * @return the distance.
     */
    public abstract int getDistance(int startingX, int startingY, int endingX, int endingY);
    
    /**
     * Returns the nearest crate to sokoban using concrete heuristic.
     * 
     * @param mockup mockup.
     * @param listOfCrates list of our crates.
     * @return point of the nearest crate.
     */
    public Point getNearestCrate(final Mockup mockup, final List<Point> listOfCrates) {
        
        Point sokobanPoint = mockup.getSokobanPoint();
        
        Point nearestCrate = listOfCrates.get(0);
        
        int distanceToNearestCrate = getDistance(sokobanPoint, nearestCrate);
        
        for(Point cratePoint : listOfCrates) {

            int currentDistance = getDistance(sokobanPoint, cratePoint);
            
            if(currentDistance < distanceToNearestCrate) {
                
                nearestCrate = cratePoint;
                distanceToNearestCrate = currentDistance;
                
            }
            
        }
        
        return nearestCrate;
        
    }
    
}
