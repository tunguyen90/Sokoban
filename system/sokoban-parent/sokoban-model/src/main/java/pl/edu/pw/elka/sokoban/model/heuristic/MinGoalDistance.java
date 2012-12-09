package pl.edu.pw.elka.sokoban.model.heuristic;

import pl.edu.pw.elka.sokoban.model.State;

/**
 * A heuristic comparing the states on basis of the minimum real distance
 * for each box to goal squares.
 * XXX
 */
public class MinGoalDistance extends GenericHeuristic {
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final State a, final State b) {
        
        return a.getGoalDistance() - b.getGoalDistance();
        
    }
    
}