package pl.edu.pw.elka.sokoban.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import pl.edu.pw.elka.sokoban.lib.Point;
import pl.edu.pw.elka.sokoban.lib.mockup.ConstantField;


/**
 * Represents sokoban map.
 */
public class Map {

    private Point startPoint;

    private List<Point> listOfGoals;
    private List<Point> listOfBoxes;

    private ConstantField[][] constantFields;

    /**
     * A set of points covering the whole map, for saving memory.
     * 
     * XXX
     */
    private Point[][] points;

    private Set<Point>[][] goalsReachable;

    /**
     * A calculated score for each point in the map.
     * XXX
     */
    private float[][] scores;

    /**
     * Constructs the new sokoban map.
     *
     * @param startPoint The start position of the player.
     * @param constantFields The map board.
     * @param points The points covering the map.
     * 
     * XXX private constructor?
     */
    private Map (final Point startPoint, final ConstantField[][] constantFields,
            final List<Point> listOfBoxes, final Point[][] points) {
        
        this.constantFields = constantFields;
        this.listOfBoxes = listOfBoxes;
        this.startPoint = startPoint;
        this.points = points;
        
        this.listOfGoals = new LinkedList<Point>();

        findGoals();

        goalsReachable = findReachableGoals();
        scores = findScores();
    }

    /**
     * Finds all goals on the map.
     */
    private void findGoals() {

        for (int y = 0; y < constantFields.length; y++) {
            
            for (int x = 0; x < constantFields[0].length; x++) {
                
                if (constantFields[y][x] == ConstantField.GOAL)
                    listOfGoals.add(points[y][x]);
                
            }
            
        }
    }

    /**
     * Returns the point on specific position.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return the point on the specific poisition.
     */
    public Point getPoint(final int x, final int y) {
        
        return points[y][x];
        
    }

    /**
     * Returns the score on the specific position.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return the score on the specific position.
     */
    public float getScore(final int x, final int y) {
        
        return scores[y][x];
        
    }

    /**
     * Returns the score on the specific point.
     *
     * @param p the point.
     * @return the score on the specific point.
     */
    public float getScore(final Point p) {
        
        return getScore(p.getX(), p.getY());
        
    }

    /**
     * Returns the list of boxes.
     * 
     * @return the list of boxes.
     */
    public List<Point> getListOfBoxes() {
        
        return listOfBoxes;
        
    }

    /**
     * Returns the list of goals.
     * 
     * @return the list of goals.
     */
    public List<Point> getListOfGoals() {
        
        return listOfGoals;
        
    }

    /**
     * Returns the start point.
     * 
     * @return the start point.
     */
    public Point getStartPoint() {
        
        return startPoint;
        
    }

    /**
     * Returns the map height.
     * 
     * @return the map height.
     */
    public int getHeight() {
        
        return constantFields.length;
        
    }

    /**
     * Returns the map width.
     * 
     * @return the map width.
     */
    public int getWidth() {
        
        return constantFields.length > 0 ? constantFields[0].length : 0;
        
    }

    /**
     * Checks if a given point in the map is forbidden.
     *
     * @param p the point to check.
     * @return true if the given point is a forbidden.
     */
    public boolean isForbidden(final Point p) {
        
        return isForbidden(p.getX(), p.getY());
        
    }
    
    /**
     * Checks if a given point is a wall.
     * 
     * @param p point to check.
     * @return true if the given point is a wall.
     */
    public boolean isWall(final Point p) {
        
        return FieldComparator.isWall(constantFields, p);
        
    }
    
    /**
     * Checks if a position is a wall.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return true if the given point is a wall.
     */
    public boolean isWall(final int x, final int y) {
        
        return FieldComparator.isWall(constantFields, x, y);
        
    }
    
    /**
     * Checks if a given point is a goal.
     * 
     * @param p point to check.
     * @return true if the given point is a goal.
     */
    public boolean isGoal(final Point p) {
        
        return FieldComparator.isGoal(constantFields, p);
        
    }
    
    /**
     * Checks if a position is a goal.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return true if the given point is a goal.
     */
    public boolean isGoal(final int x, final int y) {
        
        return FieldComparator.isGoal(constantFields, x, y);
        
    }
    
    /**
     * Checks if a given point is a free.
     * 
     * @param p point to check.
     * @return true if the given point is a free.
     */
    public boolean isFree(final Point p) {
        
        return FieldComparator.isFree(constantFields, p);
        
    }
    
    /**
     * Checks if a position is a free.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return true if the given point is a free.
     */
    public boolean isFree(final int x, final int y) {
        
        return FieldComparator.isFree(constantFields, x, y);
        
    }
    
    /**
     * Check if a given point in the map is a forbidden.
     *
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @return true if the given point is a forbidden.
     */
    public boolean isForbidden(final int x, final int y) {
        
        return goalsReachable[y][x].size() == 0;
        
    }

    /**
     * Returns the reachable goals from the point.
     * 
     * @param p point.
     * @return reachable goals from the point.
     */
    public Set<Point> getReachableGoals(final Point p) {
        
        return getReachableGoals(p.getX(), p.getY());
        
    }

    /**
     * Returns the reachable goals from the point.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return reachable goals from the point.
     */
    public Set<Point> getReachableGoals(final int x, final int y) {
        
        return goalsReachable[y][x];
        
    }

    /**
     * Finds the goals that are reachable in each point in the map.
     * 
     * @return set of reachable goals.
     * XXX
     */
    private Set<Point>[][] findReachableGoals() {
        
        @SuppressWarnings("unchecked")
        Set<Point>[][] goalSets = new HashSet[getHeight()][getWidth()];
        
        Queue<Point> queue = new LinkedList<Point>();
        
        Set<Point> visited = new HashSet<Point>();

        for (int y = 0; y < getHeight(); y++) {
            
            for (int x = 0; x < getWidth(); x++) {
                
                goalSets[y][x] = new HashSet<Point>();
                
                visited.clear();
                
                queue.add(getPoint(x, y));
                
                visited.add(getPoint(x, y));

                while (!queue.isEmpty()) {
                    
                    Point p = queue.poll();
                    
                    if (FieldComparator.isGoal(constantFields, p))
                        goalSets[y][x].add(p);

                    for (Direction d : Direction.getArray()) {
                        Direction opp = d.getRelative(2);
                        if (p.getX() + d.getxValue() >= 0 && p.getX() + d.getxValue() < getWidth() &&
                                p.getY() + d.getyValue() >= 0 && p.getY() + d.getyValue() < getHeight() &&
                                p.getX() + opp.getxValue() >= 0 && p.getX() + opp.getxValue() < getWidth() &&
                                p.getY() + opp.getyValue() >= 0 && p.getY() + opp.getyValue() < getHeight()) {
                            Point dst = getPoint(p.getX() + d.getxValue(), p.getY() + d.getyValue());

                            if (!FieldComparator.isWall(constantFields, p.getX() + opp.getxValue(), 
                                        p.getY() + opp.getyValue()) &&
                                    !FieldComparator.isWall(constantFields, dst) && !visited.contains(dst)) {
                                queue.add(dst);
                                visited.add(dst);
                            }
                        }
                    }
                }
            }
            
        }

        return goalSets;
    }

    /**
     * Calculate the score of each point in the map. The score is 0 if the
     * point is not a goal. Otherwise, it's a function of how easy it is to
     * push a box into the position. A goal square with walls along each side
     * gives the highest score.
     *
     * @return a two-dimensional array where the indexes are the x and y
     * coordinate of the points and its value is the calculated score.
     */
    private float[][] findScores() {
        float[][] scores = new float[getHeight()][getWidth()];

        for (int y = 1; y < getHeight()-1; y++)
            for (int x = 1; x < getWidth()-1; x++) {
                if (FieldComparator.isGoal(constantFields, x, y)) {
                    scores[y][x] = 1;
                    for (Direction d : Direction.getArray()) {
                        if (FieldComparator.isWall(constantFields, x + d.getxValue(), y + d.getyValue()))
                            scores[y][x] += 2;
                        else if (FieldComparator.isWall(constantFields, x + 2*d.getxValue(), y + 2*d.getyValue()))
                            scores[y][x] += 1;
                    }
                }
            }

        return scores;
    }

    /**
     * @see java.lang.Object#toString()
     * XXX
     */
    @Override
    public String toString() {
        
        StringBuffer buffer = new StringBuffer();

        for (int y = 0; y < getHeight(); y++) {
            
            for (int x = 0; x < getWidth(); x++) {
                
                Point p = getPoint(x, y);
                
                if (FieldComparator.isWall(constantFields, p))
                    buffer.append('#');
                
                else if (FieldComparator.isGoal(constantFields, p))
                    buffer.append('.');
                
                else if (isForbidden(p))
                    buffer.append('x');
                
                else
                    buffer.append(' ');
                
            }
            
            buffer.append('\n');
            
        }
        
        return buffer.toString();
        
    }

    /**
     * Parse a string containing a map to a Map object.
     *
     * The boardString could for example have the following appearance:
     *
     * ################
     * #@ #...#       #
     * #  #   # $ $ $ #
     * #              #
     * ################
     *
     * @param boardString a string representation of a map.
     * @return map
     * 
     *  XXX replace with class
     */
    public static Map parse(final String boardString) {
        int col = 0;
        int row = 0;
        int maxRow = 0;
        int maxCol = 0;

        for (byte current : boardString.getBytes()) {
            if (current == '\n') {
                if (col != 0)
                    row++;
                if (col > maxCol)
                    maxCol = col;
                col = 0;
            } else {
                col++;
            }
        }

        maxRow = row;

        ConstantField[][] matrix = new ConstantField[maxRow][maxCol];
        Point[][] points = new Point[maxRow][maxCol];
        List<Point> boxes = new LinkedList<Point>();

        for (int y = 0; y < maxRow; y++)
            for (int x = 0; x < maxCol; x++)
                points[y][x] = new Point(x, y);

        Point start = null;
        col = 0;
        row = 0;

        for (byte current : boardString.getBytes()) {
            switch(current) {
                case '*':
                    boxes.add(points[row][col]);
                case '.':
                    matrix[row][col] = ConstantField.GOAL;
                    break;
                case '#':
                    matrix[row][col] = ConstantField.WALL;
                    break;
                case '$':
                    boxes.add(points[row][col]);
                    matrix[row][col] = ConstantField.FREE;
                    break;
                case '+':
                    matrix[row][col] = ConstantField.GOAL;
                case '@':
                    start = points[row][col];
                    break;
                case ' ':
                    matrix[row][col] = ConstantField.FREE;
                    break;
                case '\n':
                    row++;
                    col = 0;
                    break;
            }

            if (current != '\n')
                col++;
        }

        /* Ensure that the map is bounded. */
        for (int x = 0; x < maxCol; x++)
            matrix[0][x] = matrix[maxRow-1][x] = ConstantField.WALL;
        for (int y = 0; y < maxRow; y++)
            matrix[y][0] = matrix[y][maxCol-1] = ConstantField.WALL;

        return new Map(start, matrix, boxes, points);
    }
}
