package pl.edu.pw.elka.sokoban.view.event;

import java.util.List;

import pl.edu.pw.elka.sokoban.lib.Point;
import pl.edu.pw.elka.sokoban.lib.mockup.ConstantField;

/**
 *
 */
public class SetNewMapEvent extends Event {

    Point startPoint;
    ConstantField[][] constantFields;
    List<Point> listOfBoxes;
    Point[][] points;
    
    /**
     * @param startPoint
     * @param constantFields
     * @param listOfBoxes
     * @param points
     */
    public SetNewMapEvent(Point startPoint, ConstantField[][] constantFields,
            List<Point> listOfBoxes, Point[][] points) {
        
        this.startPoint = startPoint;
        this.constantFields = constantFields;
        this.listOfBoxes = listOfBoxes;
        this.points = points;
    }

    /**
     * @return object of Point class representing starting point of Sokoban.
     */
    public Point getStartPoint() {
        return startPoint;
    }
    
    /**
     * @return array of objects of ConstantField class. 
     */
    public ConstantField[][] getConstantFields() {
        return constantFields;
    }
    
    /**
     * @return list of Point class objects with crates on them.
     */
    public List<Point> getListOfBoxes() {
        return listOfBoxes;
    }
    
    /**
     * @return array of object of Point class.
     */
    public Point[][] getPoints() {
        return points;
    }
    
}
