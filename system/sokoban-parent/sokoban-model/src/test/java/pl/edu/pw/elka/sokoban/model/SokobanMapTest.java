package pl.edu.pw.elka.sokoban.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static pl.edu.pw.elka.sokoban.model.AssertPoint.assertPoints;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.elka.sokoban.lib.mockup.FieldState;
import pl.edu.pw.elka.sokoban.lib.mockup.Point;


/**
 * Test for {@link pl.edu.pw.elka.sokoban.model.SokobanMap}.
 */
public class SokobanMapTest {
    
    private SokobanMap map;
    
    /**
     * Inits the map.
     */
    @Before
    public void beforeClass() {
        
        try {
            
            map = SokobanMapLoader.loadFromFile("src/main/resources/map1.txt");
            
        } catch(IOException e) {
            
            e.printStackTrace();

            fail(e.getMessage());
            
        }
        
    }

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.SokobanMap#getStartPoint()}.
     */
    @Test
    public final void testGetStartPoint() {
        
        Point startPoint = new Point(2, 1);
        startPoint.setFieldState(FieldState.SOKOBAN);
        
        assertPoints(map.getStartPoint(), startPoint);
        
    }

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.SokobanMap#getListOfCrates()}.
     */
    @Test
    public final void testGetListOfCrates() {
        
        Point cratePoint = new Point(3, 4);
        cratePoint.setFieldState(FieldState.CRATE);
        
        assertPoints(map.getListOfCrates().get(0), cratePoint);
        assertEquals(1, map.getListOfCrates().size());

    }

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.SokobanMap#getListOfGoals()}.
     */
    @Test
    public final void testGetListOfGoals() {
        
        Point goalPoint = new Point(1, 4);
        goalPoint.setFieldState(FieldState.GOAL);
        
        assertPoints(map.getListOfGoals().get(0), goalPoint);
        assertEquals(1, map.getListOfCrates().size());

    }

}
