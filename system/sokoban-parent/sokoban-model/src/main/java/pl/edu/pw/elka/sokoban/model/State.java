package pl.edu.pw.elka.sokoban.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import pl.edu.pw.elka.sokoban.lib.Point;


/**
 * A representation of a state in a Sokoban puzzle.
 * The state is built up by the formation of boxes and the current position of the player.
 * XXX
 */
public class State {
    
    private Map map;

    private Point[] listOfBoxes;

    private State previousState;

    private Point startPoint;

    /**
     * The minimum point that is reachable in this state.
     * XXX
     */
    private Point min;

    /**
     * Used for remembering old result of getGoalDistance() since that method
     * is quite expensive.
     */
    private int goalDistance;

    /**
     * For holding the result of getScore().
     */
    private float score;

    /**
     * Creates a new state.
     *
     * @param startPoint the start point.
     * @param listOfBoxes the list of boxes
     * @param map the map of this state.
     */
    public State(final Point startPoint, final Point[] listOfBoxes, final Map map) {
        
        this(startPoint, listOfBoxes, map, null, false);
        
    }

    /**
     * Create a new state.
     *
     * @param startPoint the start point.
     * @param listOfBoxes the list of boxes.
     * @param map the map of this state.
     */
    public State(final Point startPoint, final List<Point> listOfBoxes, final Map map) {
        
        this(startPoint, listOfBoxes.toArray(new Point[0]), map);
        
    }

    /**
     * Create a new state.
     *
     * @param startPoint the start point.
     * @param listOfBoxes the array of boxes in the map.
     * @param map the map of this state.
     * @param previousState the previous state.
     * @param isSorted true if the array of boxes already is sorted.
     */
    public State(final Point startPoint, final Point[] listOfBoxes, final Map map,
            final State previousState, final boolean isSorted) {
        
        this.map = map;
        this.min = null;
        this.listOfBoxes = listOfBoxes;
        this.previousState = previousState;
        this.startPoint = startPoint;
        this.goalDistance = -1;
        this.score = 1000;
        

        if (!isSorted)
            Arrays.sort(this.listOfBoxes);
        
    }

    /**
     * Getter for the reachable positions in this state. Lazy evaluation is
     * used.
     *
     * @return the set of reachable positions in this state.
     * XXX
     */
    private Collection<Point> getReachablePositions() {
        
        Queue<Point> queue = new LinkedList<Point>();
        Collection<Point> reachablePositions = new HashSet<Point>();

        queue.add(startPoint);
        reachablePositions.add(startPoint);

        while (!queue.isEmpty()) {
            
            Point current = queue.poll();
            
            for (Direction d : Direction.getArray()) {
                
                Point p = map.getPoint(current.getX() + d.getxValue(), current.getY() + d.getyValue());
                
                if (!map.isWall(p) && !hasBox(p) &&
                        !reachablePositions.contains(p)) {
                    
                    queue.add(p);
                    reachablePositions.add(p);
                    
                }
                
            }
            
        }

        if (min == null)
            min = getMinPosition(reachablePositions);

        return reachablePositions;
    }

    /**
     * Returns the min position.
     *
     * @return the min position.
     */
    private Point getMinPosition() {
        
        if (min == null)
            min = getMinPosition(getReachablePositions());
        
        return min;
        
    }

    /**
     * Find the minimum position in a set of reachable positions.
     *
     * @return the minimum position reachable in the given set.
     */
    private Point getMinPosition(final Collection<Point> reachablePositions) {
        
        Point min = null;
        
        for (Point point : reachablePositions) {
            
            if (min == null || point.compareTo(min) < 0)
                min = point;
            
        }
        
        return min;
        
    }

    /**
     * Returns the number of boxes in goal.
     * 
     * @return the number of boxes in goal.
     */
    public int getNumberOfBoxesInGoal() {
        
        int num = 0;
        
        for (Point box : listOfBoxes) {
            
            if (map.isGoal(box))
                num++;
            
        }
        
        return num;
        
    }

    /**
     * Returns the number of pushes since the map start.
     * 
     * @return the number of pushes since the map start.
     */
    public int getNumberOfPushes() {
        
        return previousState == null ? 0 : previousState.getNumberOfPushes() + 1;
        
    }

    /**
     * Returns available moves from this state.
     *
     * @return available moves from this state
     * XXX
     */
    public List<Entry<Direction, Point>> getAvailableMoves() {
        
        List<Entry<Direction, Point>> moves = new LinkedList<Entry<Direction, Point>>();

        Collection<Point> reachable = getReachablePositions();

        for (Point box : listOfBoxes) {
            
            for (Direction d : Direction.getArray()) {
                
                Point p = map.getPoint(box.getX() - d.getxValue(), box.getY() - d.getyValue());
                
                if (reachable.contains(p)) {
                    
                    p = map.getPoint(p.getX() + 2*d.getxValue(), p.getY() + 2*d.getyValue());
                    
                    if (!map.isWall(p) && !map.isForbidden(p) &&
                            !hasBox(p) && !wouldLock(box, d) &&
                            wouldBeConsistent(box, d))
                        
                        moves.add(new SimpleEntry<Direction, Point>(d, box));
                    
                }
                
            }
            
        }
        
        return moves;
        
    }

    /**
     * Returns the list of boxes.
     */
    public Point[] getListOfBoxes() {
        
        return listOfBoxes;
        
    }

    /**
     * Checks if the given position contains a box in this state.
     *
     * @param pos The position to examine.
     * @return true if there is a box on the given Point.
     */
    public boolean hasBox(final Point pos) {
        for (int i = 0; i < listOfBoxes.length; i++)
            if (listOfBoxes[i] == pos)
                return true;
        return false;
    }

    /**
     * Checks if the given position contains a box in this state.
     *
     * @param x The x-coordinate of the point to examine.
     * @param y The y-coordinate of the point to examine.
     * @return true iff there is a box on the given Point.
     */
    public boolean hasBox(final int x, final int y) {
        return hasBox(map.getPoint(x, y));
    }

    /**
     * Calculate the score for this state. The score of a state is the sum of
     * the scores of all boxes.
     *
     * @return the score for this state.
     * XXX
     */
    public float getScore() {
        
        if (score == 1000) {
            
            score = 0;
            
            for (Point box : listOfBoxes)
                score += map.getScore(box);
            
        }
        
        return score;
        
    }

    /**
     *  If we put a box at given point, this method will tell us if that would
     *  generate a blocking cycle. This method don't have to check all possible
     *  positions around the box for cycles, but the brute force method is much
     *  easier to understand and not very expensive.
     *  XXX
     */
    private boolean wouldCreateBlockingCycle(final Point p) {
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                for (Direction d : Direction.getArray())
                    if (wouldCreateBlockingCycle(map.getPoint(p.getX()+dx, p.getY()+dy), d))
                        return true;
        return false;
    }

    /**
     * Check if theres a blocking, clockwise cycle starting in the given point
     * directed in the given direction. A blocking cycle is a cycle in which
     * the surrounding "walls" contains at least one box that cannot be moved
     * to a goal square.
     *
     * There are all in all four interesting points:
     *
     * 123
     * ↑ 4
     *
     * The arrow indicates the current position and direction. Either the cycle
     * continues straight ahead, or it turns right (and right again). A turn is
     * indicated by one of the following patterns:
     *
     * Pattern 1: 124
     * Pattern 2: 234
     *
     * @param Position The position from where to start looking for a cycle.
     * @param direction The direction in which to look for a cycle.
     * @return true if there is a blocking cycle starting in the given
     * position, heading in the given direction.
     * XXX
     */
    private boolean wouldCreateBlockingCycle(final Point position, final Direction direction) {
        
        Point currentPosition = position;
        Direction currentDirection = direction;
        
        if (!(map.isWall(currentPosition) || hasBox(currentPosition)))
            return false;

        Point source = currentPosition;
        boolean turned = false;
        int turns = 0;
        int notInGoal = 0;
        int goalsFound = 0;

        while (turns < 3) {
            
            Direction right = currentDirection.getRelative(1);

            /* check so that we haven't reached the end of the map. */
            int xlimit = currentPosition.getX() + currentDirection.getxValue() + 2*right.getxValue();
            int ylimit = currentPosition.getY() + currentDirection.getyValue() + 2*right.getyValue();
            if (xlimit < 0 || ylimit < 0 || xlimit > map.getWidth()-1 ||
                    ylimit > map.getHeight()-1)
                return false;

            Point p1 = map.getPoint(currentPosition.getX() + currentDirection.getxValue(), currentPosition.getY() + currentDirection.getyValue());
            Point p2 = map.getPoint(currentPosition.getX() + currentDirection.getxValue() + right.getxValue(),
                    currentPosition.getY() + currentDirection.getyValue() + right.getyValue());
            Point p3 = map.getPoint(currentPosition.getX() + currentDirection.getxValue() + 2*right.getxValue(),
                    currentPosition.getY() + currentDirection.getyValue() + 2*right.getyValue());
            Point p4 = map.getPoint(currentPosition.getX() + 2*right.getxValue(), currentPosition.getY() + 2*right.getyValue());
            Point p5 = map.getPoint(currentPosition.getX() + right.getxValue(), currentPosition.getY() + right.getyValue());

            if (startPoint == p5 || !(map.isWall(p4) || hasBox(p4)))
                return false;

            if (!hasBox(p5) && map.isGoal(p5))
                ++goalsFound;

            if (!turned &&
                    ((map.isWall(p1) || hasBox(p1)) ||
                        (map.isWall(p3) || hasBox(p3))) &&
                     (map.isWall(p2) || hasBox(p2)) &&
                     (map.isWall(p4) || hasBox(p4))) {

                notInGoal += (hasBox(p1) && !map.isGoal(p1) ? 1 : 0) +
                              (hasBox(p2) && !map.isGoal(p2) ? 1 : 0) +
                              (hasBox(p3) && !map.isGoal(p3) ? 1 : 0) +
                              (hasBox(p4) && !map.isGoal(p4) ? 1 : 0);

                if (p1 == source || p2 == source || p3 == source ||
                        p4 == source)
                    break;

                currentPosition = p4;
                currentDirection = currentDirection.getRelative(2);
                turned = true;
                ++turns;
            } else if (map.isWall(p1) || hasBox(p1)) {
                
                if (p1 == source)
                    break;
                currentPosition = p1;
                turned = false;
                notInGoal += hasBox(p1) && !map.isGoal(p1) ? 1 : 0;
                
            } else {
                return false;
            }
        }

        return (notInGoal - goalsFound/2) > 0;
    }


    /**
     * Check if the movement of the given box in the given direction results in
     * a lock, i.e.\ unsolvable game.
     *
     * @param box The box to check.
     * @param direction The direction of movement to move the given box.
     * @return true iff the movement of the given box in the given direction
     * would result in a locked state.
     * XXX
     */
    private boolean wouldLock(final Point box, final Direction direction) {
        
        Point currentBox = box;
        
        int index = 0;
        for (int i = 0; i < listOfBoxes.length; i++)
            if (listOfBoxes[i] == currentBox)
                index = i;

        Point backup = currentBox;
        currentBox = map.getPoint(currentBox.getX() + direction.getxValue(), currentBox.getY() + direction.getyValue());
        listOfBoxes[index] = currentBox;
        boolean locked = false;

        for (int dx = -1; !locked && dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++) {
                Point p = map.getPoint(currentBox.getX() + dx, currentBox.getY() + dy);
                if ((dx == 0 && dy == 0) || startPoint == p || map.isWall(p) ||
                        map.isGoal(p))
                    continue;

                /* safe to assume that p is not a map edge. */
                Point up = map.getPoint(p.getX(), p.getY()-1);
                Point down = map.getPoint(p.getX(), p.getY()+1);
                Point left = map.getPoint(p.getX()-1, p.getY());
                Point right = map.getPoint(p.getX()+1, p.getY());

                if ((!map.isWall(up) && !hasBox(up)) ||
                        (!map.isWall(down) && !hasBox(down)) ||
                        (!map.isWall(left) && !hasBox(left)) ||
                        (!map.isWall(right) && !hasBox(right)))
                    continue;

                /* up, down, left, right is blocked. */
                int notInGoal = 0;
                for (int ddx = -1; notInGoal == 0 && ddx <= 1; ddx++)
                    for (int ddy = -1; ddy <= 1; ddy++) {
                        Point pp = map.getPoint(p.getX() + ddx, p.getY() + ddy);
                        if (!map.isGoal(pp) && hasBox(pp)) {
                            notInGoal++;
                            break;
                        }
                    }

                if (notInGoal > 0 &&
                        (((map.isWall(p.getX()-1, p.getY()-1) || hasBox(p.getX()-1, p.getY()-1)) &&
                          (map.isWall(p.getX()+1, p.getY()+1) || hasBox(p.getX()+1, p.getY()+1))) ||
                         ((map.isWall(p.getX()-1, p.getY()+1) || hasBox(p.getX()-1, p.getY()+1)) &&
                          (map.isWall(p.getX()+1, p.getY()-1) || hasBox(p.getX()+1, p.getY()-1))))) {
                    locked = true;
                    break;
                }
            }

        if (!locked)
            for (Point neighbor : listOfBoxes)
                if (Math.abs(currentBox.getX() - neighbor.getX()) <= 1 &&
                        Math.abs(currentBox.getY() - neighbor.getY()) <= 1 &&
                        !map.isGoal(neighbor) && boxIsLocked(neighbor)) {
                    locked = true;
                    break;
                }

        if (!locked && wouldCreateBlockingCycle(currentBox))
            locked = true;

        listOfBoxes[index] = backup;

        return locked;
    }

    /**
     * Checks if the given box is locked.
     *
     * @param box The box to check.
     * @return true if the given box is locked.
     * XXX
     */
    private boolean boxIsLocked(final Point box) {
        
        return boxIsLocked(box, new LinkedList<Point>());
        
    }

    /**
     * Checks if the given box is locked. The set of boxes given are assumed to
     * be locked.
     *
     * @param box The box to check.
     * @param checkedBoxes Boxes priorly checked and assumed to be locked.
     * @return true iff the box is locked.
     */
    private boolean boxIsLocked(final Point box, final Collection<Point> checkedBoxes) {
        checkedBoxes.add(box);

        for (Direction d1 : Direction.getArray()) {
            Collection<Point> children = new LinkedList<Point>(checkedBoxes);
            Direction d2 = d1.getRelative(1);

            Point p1 = map.getPoint(box.getX() + d1.getxValue(), box.getY() + d1.getyValue());
            Point p2 = map.getPoint(box.getX() + d2.getxValue(), box.getY() + d2.getyValue());
            if (isBlocked(p1, children) && isBlocked(p2, children)) {
                checkedBoxes.addAll(children);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given Point p is blocked.
     *
     * @param p The point to check if blocked.
     * @param checkedBoxes Boxes already checked and assumed to be locked.
     * @return true iff the point in question is a wall, or iff it's a box and
     * the box in question is locked or is assumed to be locked.
     */
    private boolean isBlocked(final Point p, final Collection<Point> checkedBoxes) {
        return map.isWall(p) || (hasBox(p) &&
                (checkedBoxes.contains(p) || boxIsLocked(p, checkedBoxes)));
    }

    /**
     * A greedy method for finding the sum of distances between goal squares
     * and boxes. The boxes and goal squares are paired in a greedy way so that
     * each goal in order is paired with its nearest unpaired box.
     *
     * @return the sum of distances between each box in the state and a goal
     * square in such a way so that each goal square is paired with one box.
     */
    public int getGoalDistance() {
        if (goalDistance == -1) {
            List<Point> goalsLeft = new LinkedList<Point>();
            Collection<Point> boxesLeft = new LinkedList<Point>();
            goalsLeft.addAll(map.getListOfGoals());

            for (Point box : listOfBoxes)
                if (map.isGoal(box))
                    goalsLeft.remove(box);
                else
                    boxesLeft.add(box);

            goalDistance = 0;

            for (Point goal : goalsLeft) {
                Entry<Point, Integer> answer = findMinPathLength(goal, boxesLeft);
                boxesLeft.remove(answer.getKey());
                goalDistance += answer.getValue();
            }
        }
        return goalDistance;
    }

    /**
     * Finds the minimum path from the point given to any of the points in the
     * given destination set.
     *
     * @param from The source in the path.
     * @param dst The set of possible destinations.
     * @return the minimum path from the point from to one of the points in the
     * destination set.
     */
    private Entry<Point, Integer> findMinPathLength(final Point from,
            final Collection<Point> dst) {
        Queue<Point> queue = new LinkedList<Point>();
        int[][] visited = new int[map.getHeight()][map.getWidth()];

        if (dst.contains(from))
            return new SimpleEntry<Point, Integer>(from, 0);

        queue.add(from);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (Direction d : Direction.getArray()) {
                int x = current.getX() + d.getxValue();
                int y = current.getY() + d.getyValue();
                if (visited[y][x] == 0 && !map.isWall(x, y) && !map.isForbidden(x, y)) {
                    Point to = map.getPoint(x, y);
                    if (dst.contains(to))
                        return new SimpleEntry<Point, Integer>(
                                to, visited[current.getY()][current.getX()] + 1);

                    queue.add(to);
                    visited[y][x] = visited[current.getY()][current.getX()] + 1;
                }
            }
        }
        return null;
    }

    /**
     * Calculate and return the players distance from the goal in number of
     * steps. Boxes are not treated as obstacles here.
     *
     * @return the minimum number of steps needed to reach a goal.
     */
    public int getStepsFromGoal() {
        Queue<Point> queue = new LinkedList<Point>();
        int[][] visited = new int[map.getHeight()][map.getWidth()];

        int stepsFromGoal = 0;
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (map.isGoal(current)) {
                stepsFromGoal = visited[current.getY()][current.getX()];
                break;
            }

            for (Direction d : Direction.getArray()) {
                int x = current.getX() + d.getxValue();
                int y = current.getY() + d.getyValue();
                if (visited[y][x] == 0 && !map.isWall(x, y)) {
                    queue.add(map.getPoint(x, y));
                    visited[y][x] = visited[current.getY()][current.getX()] + 1;
                }
            }
        }
        return stepsFromGoal;
    }

    /**
     * Goal test. Check if all the boxes are on goal squares.
     */
    public boolean isGoalReached() {
        return getNumberOfBoxesInGoal() == listOfBoxes.length;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(map.getHeight()*map.getWidth());
        Collection<Point> reachable = getReachablePositions();

        buffer.append(String.format("score: %d\n", getNumberOfBoxesInGoal()));
        buffer.append(String.format("distance: %d\n", getGoalDistance()));

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Point p = map.getPoint(x, y);
                if (map.isWall(p))
                    buffer.append('#');
                else if (hasBox(p))
                    buffer.append((map.isGoal(p) ? '*' : '$'));
                else if (map.isGoal(p))
                    buffer.append((reachable.contains(p) ? '+' : '.'));
                else if (map.isForbidden(p))
                    buffer.append('x');
                else
                    buffer.append((reachable.contains(p) ? ' ' : '-'));
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }

    /**
     * @return a string containing the string representation of all states from
     * the start state to the current.
     */
    public String statePath() {
        return (previousState != null ? previousState.statePath() + "\n" : "") + toString();
    }

    /**
     * @return a list of directions describing the player movements needed to
     * reach this state from the start state.
     */
    public List<Direction> directionPath() {
        return previousState.directionPath(this);
    }

    /**
     * Calculate the movements needed to go from this state to the next state.
     * Append these movements to the movements needed to go from the start
     * state to this state.
     *
     * @param next The state to reach.
     * @return the list of directions needed for the player to go from the
     * start state to this state.
     */
    private List<Direction> directionPath(final State next) {
        List<Direction> directions;
        Point from = null;
        Point to = null;

        if (previousState != null)
            directions = previousState.directionPath(this);
        else
            directions = new LinkedList<Direction>();

        for (Point box : listOfBoxes)
            if (!next.hasBox(box)) {
                from = box;
                break;
            }

        for (Point box : next.listOfBoxes)
            if (!hasBox(box)) {
                to = box;
                break;
            }

        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();
        directions.addAll(pathSearch(startPoint, map.getPoint(from.getX() - dx, from.getY() - dy)));

        for (Direction d : Direction.getArray())
            if (d.getxValue() == dx && d.getyValue() == dy) {
                directions.add(d);
                break;
            }

        return directions;
    }

    /**
     * Search for a path from the Point from to the Point to.
     *
     * @param from The start point.
     * @param to The point to reach.
     * @return a list of directions for the player to go from the start to the
     * end of the path.
     */
    private List<Direction> pathSearch(final Point from, final Point to) {
        Queue<Point> queue = new LinkedList<Point>();
        Set<Point> visited = new HashSet<Point>();
        HashMap<Point, Entry<Point, Direction>> traceback =
            new HashMap<Point, Entry<Point, Direction>>();

        queue.add(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current == to) {
                LinkedList<Direction> directions = new LinkedList<Direction>();
                while (current != from) {
                    Entry<Point, Direction> previous = traceback.get(current);
                    current = previous.getKey();
                    directions.addFirst(previous.getValue());
                }
                return directions;
            }

            for (Direction d : Direction.getArray()) {
                Point p = map.getPoint(current.getX() + d.getxValue(), current.getY() + d.getyValue());
                if (!map.isWall(p) && !hasBox(p) && !visited.contains(p)) {
                    queue.add(p);
                    visited.add(p);
                    traceback.put(p, new SimpleEntry<Point, Direction>(current, d));
                }
            }
        }
        return null;
    }

    /**
     * Create a new state from the given state and the movement described.
     *
     * @param from The source state.
     * @param move The movement to apply on the source state to reach a new
     * state.
     * @return the new state resulting from applying move on from.
     */
    public static State getStateAfterMove(final State from,
            final Entry<Direction, Point> move) {
        Direction direction = move.getKey();
        Point moveBox = move.getValue();
        Point[] boxes = new Point[from.listOfBoxes.length];

        int index = 0;
        Point newBox = from.map.getPoint(moveBox.getX() + direction.getxValue(),
                moveBox.getY()+direction.getyValue());

        /* Insert boxes in sorted order. */
        for (int i = 0; i < boxes.length; i++) {
            Point box = from.listOfBoxes[i];

            if (box == moveBox)
                continue;

            if (newBox != null && box.compareTo(newBox) > 0) {
                boxes[index++] = newBox;
                newBox = null;
            }

            boxes[index++] = box;
        }

        if (newBox != null)
            boxes[index] = newBox;
        return new State(moveBox, boxes, from.map, from, true);
    }

    private boolean wouldBeConsistent(final Point box, final Direction direction) {
        int index = 0;
        for (int i = 0; i < listOfBoxes.length; i++)
            if (listOfBoxes[i] == box)
                index = i;

        listOfBoxes[index] = map.getPoint(box.getX() + direction.getxValue(), box.getY() + direction.getyValue());
        boolean consistent = isConsistent();
        listOfBoxes[index] = box;
        return consistent;
    }

    /**
     * A three level check of consistency. Checks that given any three boxes in
     * the state, they can each be placed on a different goal.
     *
     * @return true iff the state is consistent.
     */
    private boolean isConsistent() {
        for (int i = 0; i < listOfBoxes.length; i++) {
            Point box1 = listOfBoxes[i];
            Set<Point> r1 = map.getReachableGoals(box1);
            if (r1.size() > 2)
                continue;

            for (int j = i+1; j < listOfBoxes.length; j++) {
                Point box2 = listOfBoxes[j];
                Set<Point> r2 = map.getReachableGoals(box2);
                if (r2.size() > 2)
                    continue;

                for (int k = j+1; k < listOfBoxes.length; k++) {
                    Point box3 = listOfBoxes[k];
                    Set<Point> r3 = map.getReachableGoals(box3);
                    if (r3.size() > 2)
                        continue;

                    boolean found = false;
                    for (Point p1 : r1)
                        for (Point p2 : r2)
                            for (Point p3 : r3)
                                if (p1 != p2 && p1 != p3 && p2 != p3) {
                                    found = true;
                                    break;
                                }
                    if (!found)
                        return false;
                }
            }
        }
        return true;
    }
}
