package pl.edu.pw.elka.sokoban.model.heuristic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Test for {@link pl.edu.pw.elka.sokoban.model.heuristic.ManhattanHeuristic}
 */
public class ManhattanHeuristicTest {

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.heuristic.ManhattanHeuristic#getDistance(int, int, int, int)}.
     */
    @Test
    public final void testGetDistanceIntIntIntInt() {

        ManhattanHeuristic heuristic = new ManhattanHeuristic();
        
        assertEquals(10, heuristic.getDistance(0, 0, 0, 10));
        assertEquals(20, heuristic.getDistance(0, 0, 10, 10));
        
    }

}
