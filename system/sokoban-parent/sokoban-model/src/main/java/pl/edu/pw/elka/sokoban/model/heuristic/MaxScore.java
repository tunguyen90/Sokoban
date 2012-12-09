package pl.edu.pw.elka.sokoban.model.heuristic;

import pl.edu.pw.elka.sokoban.model.State;

/**
 * A heuristic for comparing state scores.
 * XXX
 */
public class MaxScore extends GenericHeuristic {
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final State a, final State b) {
        
        float cmp = b.getScore() - a.getScore();
        
        if (cmp < 0)
            return -1;
        
        else if (cmp > 0)
            return 1;
        
        return 0;
        
    }
    
}