package pl.edu.pw.elka.sokoban.model;

/**
 * Represents a direction of moves.
 */
public enum Direction {

    /**
     * Up movement representation.
     */
    UP(0, -1, "U"),
    
    /**
     * Right movement representation.
     */
    RIGHT(1, 0, "R"),
    
    /**
     * Down movement representation.
     */
    DOWN(0, 1, "D"),
    
    /**
     * Left movement representation.
     */
    LEFT(-1, 0, "L");

    /**
     * Value of movement to the left or right.
     */
    private int xValue;

    /**
     * The delta movement y-ways.
     */
    private int yValue;

    /**
     * The string representation of this direction.
     */
    private String letterRepresentation;

    /**
     * Holds an array containing all directions.
     */
    private static Direction[] dirs = null;

    /**
     * Create a new Direction.
     *
     * @param xValue The movement in x-ways this direction corresponds to.
     * @param yValue The movement in y-ways this direction corresponds to.
     * @param letterRepresentation The string representation of this direction/movement.
     */
    Direction(final int xValue, final int yValue, final String letterRepresentation) {
        
        this.xValue = xValue;
        this.yValue = yValue;
        this.letterRepresentation = letterRepresentation;
        
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        
        return letterRepresentation;
        
    }

    /**
     * Static method for finding a direction given its delta movements.
     *
     * @param xValue The delta movement in x-ways of the direction to find.
     * @param yValue The delta movement in y-ways of the direction to find.
     * @return the direction found, or null if no such direction was found.
     */
    public static Direction getDirection(final int xValue, final int yValue) {
        
        for (Direction d : getArray())
            if (d.xValue == xValue && d.yValue == yValue)
                return d;
        
        return null;
        
    }

    /**
     * A singleton of dirs.
     *
     * @return an array containing the available directions.
     */
    public synchronized static Direction[] getArray() {
        
        if (dirs == null)
            dirs = values();
        
        return dirs;
        
    }

    /**
     * Calculate the relative direction.
     *
     * @param turns the number of turns in clockwise rotation.
     * @return the relative direction.
     */
    public Direction getRelative(final int turns) {
        
        int relativeTurns = (turns % getArray().length) + getArray().length;
        
        return getArray()[(ordinal() + relativeTurns) % getArray().length];
        
    }

    
    /**
     * Returns the xValue.
     *
     * @return the xValue.
     */
    public int getxValue() {
    
        return xValue;
    
    }

    
    /**
     * Returns the yValue.
     *
     * @return the yValue.
     */
    public int getyValue() {
    
        return yValue;
    
    }

    
    /**
     * Returns the letterRepresentation.
     *
     * @return the letterRepresentation.
     */
    public String getLetterRepresentation() {
    
        return letterRepresentation;
    
    }
    
}
