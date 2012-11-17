package pl.edu.pw.elka.sokoban.model.heuristic;

import pl.edu.pw.elka.sokoban.lib.mockup.Point;


/**
 * Class representing manhattan heuristic.
 * 
 * Manhattan uses absolute difference between coordinates.
 */
public class ManhattanHeuristic extends GenericHeuristic {
    
    /**
     * @see pl.edu.pw.elka.sokoban.model.heuristic.GenericHeuristic#getDistance(pl.edu.pw.elka.sokoban.lib.mockup.Point, pl.edu.pw.elka.sokoban.lib.mockup.Point)
     */
    @Override
    public int getDistance(final Point startingPoint, final Point endingPoint) {

        int startingX = startingPoint.getX();
        int startingY = startingPoint.getY();
        
        int endingX = endingPoint.getX();
        int endingY = endingPoint.getY();
        
        return getDistance(startingX, startingY, endingX, endingY);
        
    }
    
    /**
     * @see pl.edu.pw.elka.sokoban.model.heuristic.GenericHeuristic#getDistance(int, int, int, int)
     */
    @Override
    public int getDistance(final int startingX, final int startingY, final int endingX, final int endingY) {
    
        int distanceX = Math.abs(startingX - endingX);
        int distanceY = Math.abs(startingY - endingY);
        
        return distanceX + distanceY;
        
    }

}
