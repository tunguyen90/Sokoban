package pl.edu.pw.elka.sokoban.lib.mockup;

/**
 * Enum class representing field state on mockup.
 */
public enum FieldState {
    
    /**
     * Field with brick, where sokoban cannot stands.
     */
    BRICK('X'),
    
    /**
     * Target field, where crate should be pushed.
     */
    GOAL('o'),
    
    /**
     * Field with crate standing on it.
     */
    CRATE('#'),
    
    /**
     * Main character.
     */
    SOKOBAN('@'),
    
    /**
     * Crate standing on the goal.
     */
    CRATE_ON_GOAL('%'),
    
    /**
     * Sokoban standing on the goal.
     */
    SOKOBAN_ON_GOAL('s'),
    
    /**
     * Free place, where sokoban can stands, crate can be pushed.
     */
    FREE(' ');
    
    private char letterRepresentation;
    
    private FieldState(final char letterRepresentation) {
        
        this.letterRepresentation = letterRepresentation;
        
    }
    
    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        
        return "" + letterRepresentation;
        
    }

}
