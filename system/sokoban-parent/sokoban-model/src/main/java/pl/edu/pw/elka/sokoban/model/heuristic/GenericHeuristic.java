package pl.edu.pw.elka.sokoban.model.heuristic;

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

}
