package pl.edu.pw.elka.sokoban.model.heuristic;

import pl.edu.pw.elka.sokoban.model.State;

/**
 * A heuristic comparing the number of steps from the goal the player is in
 * each state.
 */
public class MinStepsFromGoal extends GenericHeuristic {
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final State a, final State b) {
        
        return a.getStepsFromGoal() - b.getStepsFromGoal();
        
    }
    
}