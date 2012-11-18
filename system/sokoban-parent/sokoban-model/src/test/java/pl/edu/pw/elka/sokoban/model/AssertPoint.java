package pl.edu.pw.elka.sokoban.model;

import static org.junit.Assert.assertEquals;
import pl.edu.pw.elka.sokoban.lib.mockup.Point;

/**
 * Asserts the two points.
 */
public class AssertPoint {
    
    /**
     * Asserts the two points.
     * 
     * @param expected excpected point.
     * @param actual actual point.
     */
    public static void assertPoints(final Point expected, final Point actual) {
        
        assertEquals("X", expected.getX(), actual.getX());
        assertEquals("Y", expected.getY(), actual.getY());
        assertEquals("FieldState", expected.getFieldState(), actual.getFieldState());
        
    }

}
