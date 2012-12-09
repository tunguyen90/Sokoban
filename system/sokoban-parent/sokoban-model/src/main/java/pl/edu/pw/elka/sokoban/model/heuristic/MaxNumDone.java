package pl.edu.pw.elka.sokoban.model.heuristic;

import pl.edu.pw.elka.sokoban.model.State;

/**
 * A heuristic expanding the states with the most boxes in a goal first.
 * XXX
 */
public class MaxNumDone extends GenericHeuristic {
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final State a, final State b) {
        
        return b.getNumberOfBoxesInGoal() - a.getNumberOfBoxesInGoal();
        
    }
    
}