package pl.edu.pw.elka.sokoban.model.heuristic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Test for {@link pl.edu.pw.elka.sokoban.model.heuristic.PitagorasHeuristic}
 */
public class PitagorasHeuristicTest {

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.heuristic.PitagorasHeuristic#getDistance(int, int, int, int)}.
     */
    @Test
    public final void testGetDistanceIntIntIntInt() {
        
        PitagorasHeuristic heuristic = new PitagorasHeuristic();
        
        assertEquals(10, heuristic.getDistance(0, 0, 0, 10));
        assertEquals(14, heuristic.getDistance(0, 0, 10, 10));
        
    }

}
