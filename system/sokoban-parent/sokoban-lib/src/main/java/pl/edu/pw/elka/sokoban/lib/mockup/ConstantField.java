package pl.edu.pw.elka.sokoban.lib.mockup;

/**
 * Constants type of field. May be only free, goal or wall.
 */
public enum ConstantField {
    
    /**
     * Free field. 
     */
    FREE,
    
    /**
     * Destination of the crate.
     */
    GOAL,
    
    /**
     * A field, where sokoban cannot stand.
     */
    WALL
    
}