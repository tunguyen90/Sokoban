package pl.edu.pw.elka.sokoban.model.heuristic;

import java.util.Comparator;

import pl.edu.pw.elka.sokoban.model.State;


/**
 * XXX replace with another heuristics.
 */
public class HeuristicManager implements Comparator<State> {
    
    private GenericHeuristic heuristic;
    
//    /**
//     * A container class for multiple heuristics. The order in which they are
//     * applied is decided by what order they were put in the heuristics list.
//     * XXX very carefully
//     */
//    public static class MultipleHeuristic implements Comparator<State> {
//        
//        /**
//         * For describing when a specific heuristic should be applied.
//         */
//        private class Range {
//            
//            /**
//             * At what move the heuristic should start.
//             */
//            int from;
//
//            /**
//             * At what move the heuristic should end.
//             */
//            int to;
//
//            /**
//             * Create a new Range.
//             *
//             * @param from The start of the range.
//             * @param to The end of the range.
//             */
//            public Range(final int from, final int to) {
//                
//                this.from = from;
//                this.to = to;
//                
//            }
//            
//        }
//
//        /**
//         * The list of heuristics to test.
//         */
//        List<Entry<Range, Comparator<State>>> comparators;
//
//        /**
//         * Create a new MultipleHeuristic.
//         */
//        public MultipleHeuristic() {
//            
//            comparators = new LinkedList<Entry<Range, Comparator<State>>>();
//            
//        }
//
//        /**
//         * Add a heuristic to the list of heuristics to test.
//         *
//         * @param heuristic The heuristic to add.
//         * @param from At what height the heuristic should begin.
//         * @param to At what height the heuristic should end.
//         */
//        public void add(final Comparator<State> heuristic, final int from, final int to) {
//            comparators.add(new SimpleEntry<Range, Comparator<State>>(
//                        new Range(from, to), heuristic));
//        }
//
//        /**
//         * @param heuristic heuristcs
//         * @param from from
//         */
//        public void add(final Comparator<State> heuristic, final int from) {
//            add(heuristic, from, 0);
//        }
//
//        /**
//         * @param heuristic heuristics
//         */
//        public void add(final Comparator<State> heuristic) {
//            add(heuristic, 0);
//        }
    
    
    /**
     * Constructs the heuristic manager.
     */
    public HeuristicManager() {

    }
    
    /**
     * Constructs the heuristic manager with the specific heuristic.
     * 
     * @param heuristic heuristic to set. 
     */
    public HeuristicManager(final GenericHeuristic heuristic) {

        this.heuristic = heuristic;

    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final State a, final State b) {
//        for (Entry<Range, Comparator<State>> entry : comparators) {
//            Range range = entry.getKey();
//            if (range.from > a.getNumberOfPushes() || range.from > b.getNumberOfPushes() ||
//                    (range.to != 0 && (range.to < a.getNumberOfPushes() ||
//                                       range.to < b.getNumberOfPushes())))
//                continue;
//
//            Comparator<State> comparator = entry.getValue();
//            int tmp = comparator.compare(a, b);
//            if (tmp != 0)
//                return tmp;
//        }
        return a.getNumberOfPushes() - b.getNumberOfPushes();
    }
}
