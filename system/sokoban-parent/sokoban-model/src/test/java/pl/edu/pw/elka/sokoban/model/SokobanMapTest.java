package pl.edu.pw.elka.sokoban.model;

import static org.junit.Assert.assertArrayEquals;
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
        
        Point startPoint = new Point(3, 1);
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
    
    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.SokobanMap#findReachablePoints}
     */
    @Test
    public final void testFindReachablePoints() {
        
        Point startPoint = map.getStartPoint();
        
        Boolean[][] reachablePoints = map.findReachablePoints(startPoint);
        
        Boolean[][] expectedPoints = new Boolean[map.getHeight()][];
        
        for(int i = 0; i < map.getHeight(); i++) {
            
            expectedPoints[i] = new Boolean[map.getWidth()];
            
        }
        
//        XXXXXX
//        X X@ X
//        XXXX X
//        X    X
//        Xo # X
//        XXXXXX

        expectedPoints[0][0] = false;
        expectedPoints[0][1] = false;
        expectedPoints[0][2] = false;
        expectedPoints[0][3] = false;
        expectedPoints[0][4] = false;
        expectedPoints[0][5] = false;
        
        expectedPoints[1][0] = false;
        expectedPoints[1][1] = false;
        expectedPoints[1][2] = false;
        expectedPoints[1][3] = true;
        expectedPoints[1][4] = true;
        expectedPoints[1][5] = false;
        
        expectedPoints[2][0] = false;
        expectedPoints[2][1] = false;
        expectedPoints[2][2] = false;
        expectedPoints[2][3] = false;
        expectedPoints[2][4] = true;
        expectedPoints[2][5] = false;
        
        expectedPoints[3][0] = false;
        expectedPoints[3][1] = true;
        expectedPoints[3][2] = true;
        expectedPoints[3][3] = true;
        expectedPoints[3][4] = true;
        expectedPoints[3][5] = false;
        
        expectedPoints[4][0] = false;
        expectedPoints[4][1] = true;
        expectedPoints[4][2] = true;
        expectedPoints[4][3] = false;
        expectedPoints[4][4] = true;
        expectedPoints[4][5] = false;
        
        expectedPoints[5][0] = false;
        expectedPoints[5][1] = false;
        expectedPoints[5][2] = false;
        expectedPoints[5][3] = false;
        expectedPoints[5][4] = false;
        expectedPoints[5][5] = false;
        
        assertArrayEquals(expectedPoints, reachablePoints);
        
    }

}
