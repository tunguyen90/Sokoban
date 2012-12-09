package pl.edu.pw.elka.sokoban.model;


import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import pl.edu.pw.elka.sokoban.lib.Point;
import pl.edu.pw.elka.sokoban.model.heuristic.HeuristicManager;
import pl.edu.pw.elka.sokoban.model.heuristic.MinStepsFromGoal;


/**
 * A class used for solving Sokoban puzzles.
 * This is the main class of the project.
 * 
 * XXX REPLACE ME
 */
public class Model {
    
    /**
     * Tells whether the state path should be written to stdout when a path is
     * found.
     */
    static boolean printStatePath = true;

    /**
     * Tells whether a path of directions should be written to stdout when
     * a path is found.
     */
    static boolean printDirectionPath = true;

    /**
     * Tells whether the puzzle should be written to stdout upon start.
     */
    static boolean printPuzzle = true;

    /**
     * Tells whether a progress meter should be displayed.
     */
    static boolean printProgress = true;

    /**
     * The time limit for a search.
     */
    static int searchLimit = 60000;

    /**
     * The interval to wait between check of time and prints.
     */
    static int interval = 200;

    /**
     * The map to solve.
     */
    Map map;

    /**
     * The start state of the map.
     */
    State startState;

    /**
     * The end state of the map. If no solution has been found, it is null.
     */
    State endState;

    /**
     * Create a new Solver.
     *
     * @param mapString A string representation of a map.
     */
    public Model() {
        
        String stringOfMap = "########\n" +
                                "#   # .#\n" +
                                "#   $$.#\n" +
                                "####   #\n" +
                                "####@ ##\n" +
                                "########\n";
        
        map = Map.parse(stringOfMap);
        startState = new State(map.getStartPoint(), map.getListOfBoxes(), map);
        endState = null;
        
    }

    /**
     * @return the map to solve.
     */
    public Map getMap() {
        
        return map;
        
    }

    /**
     * @return the start state of the map.
     */
    public State getStartState() {
        
        return startState;
        
    }

    /**
     * @return the end state of the map.
     */
    public State getEndState() {
        
        return endState;
        
    }

    private void printInfo(final int expanded, final int inspected, final int visited,
            final int filled, final long start) {
        
        System.out.printf("expanded: %6d, inspected: %6d, queue: %6d, " +
                "filled: %2d, time: %2.2f s\r", expanded, inspected, visited,
                filled, (float)(System.currentTimeMillis()-start)/1000);
        
    }

    /**
     * Do an A* search for a solution.
     *
     * @param heuristic The heuristic to use.
     * @param limit The time limit.
     * @return the number of expanded nodes.
     * XXX key of everything.
     */
    public int search(final Comparator<State> heuristic, final int limit) {
        
        int numExpanded = 0;
        int numInspected = 0;

        Queue<State> queue = new PriorityQueue<State>(1000, heuristic);
        Set<State> visited = new HashSet<State>();

        queue.add(startState);
        visited.add(startState);

        int i = 0;
        long start = System.currentTimeMillis();

        while (!queue.isEmpty()) {
            State curState = queue.poll();

            if (numExpanded == 1 || i == interval) {
                if (printProgress) {
                    printInfo(numExpanded, numInspected, queue.size(),
                            curState.getNumberOfBoxesInGoal(), start);
                }

                if (System.currentTimeMillis()-start >= limit)
                    break;
                i = 0;
            }
            i++;
            numExpanded++;

            for (Entry<Direction, Point> move : curState.getAvailableMoves()) {
                State nextState = State.getStateAfterMove(curState, move);
                numInspected++;

                if (!visited.contains(nextState)) {
                    if (nextState.isGoalReached()) {
                        endState = nextState;
                        if (printProgress) {
                            printInfo(numExpanded, numInspected, queue.size(),
                                    endState.getNumberOfBoxesInGoal(), start);
                            System.out.println();
                        }
                        return numExpanded;
                    }
                    queue.add(nextState);
                    visited.add(nextState);
                }
            }
        }

        if (printProgress) {
            printInfo(numExpanded, numInspected, queue.size(), 0, start);
            System.out.println();
        }
        return numExpanded;
        
    }

    /**
     * Run the solver on the puzzle given in args[0].
     * @param args args
     * @throws IOException ioe 
     */
    public static void main(final String[] args) throws IOException {
        
        Model solver = new Model();

        if (printPuzzle)
            System.out.println(solver.getStartState());

//        HeuristicManager.MultipleHeuristic heuristic =
//            new HeuristicManager.MultipleHeuristic();
        
//        HeuristicManager heuristicManager = new HeuristicManager(new MinGoalDistance());
//        HeuristicManager heuristicManager = new HeuristicManager(new MaxNumDone());
//        HeuristicManager heuristicManager = new HeuristicManager(new MaxScore());
        HeuristicManager heuristicManager = new HeuristicManager(new MinStepsFromGoal());

//        heuristic.add(new MinGoalDistance(), 3);

        long time = System.currentTimeMillis();
        long num = solver.search(heuristicManager, (int)(3.0/4 * searchLimit));
        time = System.currentTimeMillis() - time;

        if (solver.getEndState() == null) {
//            heuristic = new HeuristicManager.MultipleHeuristic();
//            heuristic.add(new MaxScore(), 3);
//            time = System.currentTimeMillis();
//            num += solver.search(heuristic, (int)(1.0/4 * searchLimit));
//            time = System.currentTimeMillis() - time;
        }

        boolean success = false;

        if (solver.getEndState() != null) {
            
            success = true;
            
        }

        if (success) {
            if (printStatePath)
                System.out.println(solver.getEndState().statePath());

            if (printDirectionPath) {
                List<Direction> path = solver.getEndState().directionPath();
                System.out.println("path length: " + path.size());
                for (Direction d : path)
                    System.out.print(d);
                System.out.println();
            }
        } else if (printPuzzle)
            System.out.println("No solution found in time.");

        if (printPuzzle) {
            if (solver.getEndState() != null)
                System.out.printf("num states: %d\n",
                        solver.getEndState().getNumberOfPushes());
            System.out.printf("num expanded: %d\n", num);
            System.out.printf("time: %d\n", time);
        }

        System.exit(solver.getEndState() == null ? 1 : 0);
    }
}
