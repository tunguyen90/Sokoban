package pl.edu.pw.elka.sokoban.lib;

/**
 * Represents the point on the map.
 */
public class Point implements Comparable<Point> {
    
    private int x;
    private int y;

    /**
     * Create a new point.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(final int x, final int y) {
        
        this.x = x;
        this.y = y;
        
    }

    /**
     * Copy constructor.
     *
     * @param p The point to copy.
     * XXX
     */
    public Point(final Point p) {
        
        this(p.x, p.y);
        
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Point p) {
        
        if (x != p.x)
            return x - p.x;
        
        return y - p.y;
        
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        
        if (!(o instanceof Point))
            return false;
        
        Point p = (Point)o;
        
        return x == p.x && y == p.y;
        
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "[" + x + ", " + y + "]";
        
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
     * Returns the y coordinate.
     *
     * @return the y coordinate.
     */
    public int getY() {
    
        return y;
    
    }
    
}
