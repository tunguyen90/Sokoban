package pl.edu.pw.elka.sokoban.model;

import pl.edu.pw.elka.sokoban.lib.Point;
import pl.edu.pw.elka.sokoban.lib.mockup.ConstantField;


/**
 * Provides many methods which can say is field is...
 */
public class FieldComparator {
    
    /**
     * Checks if a point in the map is a wall.
     * 
     * @param constantFields constant fields to check. 
     * @param p the point to check.
     * @return true if the given point is a wall, false otherwise.
     */
    public static boolean isWall(final ConstantField[][] constantFields, final Point p) {
        
        return isWall(constantFields, p.getX(), p.getY());
        
    }

    /**
     * Checks if a point in the map is a wall.
     *
     * @param constantFields constant fields to check.
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point.
     * @return true if the given point is a wall.
     */
    public static boolean isWall(final ConstantField[][] constantFields, final int x, final int y) {
        
        return constantFields[y][x] == ConstantField.WALL;
        
    }

    /**
     * Checks if a given point in the map is a goal.
     *
     * @param constantFields constant fields to check.
     * @param p the point to check.
     * @return true if the given point is a goal.
     */
    public static boolean isGoal(final ConstantField[][] constantFields, final Point p) {
        
        return isGoal(constantFields, p.getX(), p.getY());
        
    }

    /**
     * Checks if a given point in the map is a goal.
     *
     * @param constantFields constant fields to check.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @return true if the given point is a goal.
     */
    public static boolean isGoal(final ConstantField[][] constantFields, final int x, final int y) {
        
        return constantFields[y][x] == ConstantField.GOAL;
        
    }

    /**
     * Checks if a given point in the map is free.
     *
     * @param constantFields constant fields to check.
     * @param p The point to check.
     * @return true if the given point is free.
     */
    public static boolean isFree(final ConstantField[][] constantFields, final Point p) {
        
        return isFree(constantFields, p.getX(), p.getY());
        
    }
    
    /**
     * Checks if a given point in the map is free.
     *
     * @param constantFields constant fields to check.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @return true if the given point is free.
     */
    public static boolean isFree(final ConstantField[][] constantFields, final int x, final int y) {
        
        return constantFields[y][x] == ConstantField.FREE;
        
    }

}
