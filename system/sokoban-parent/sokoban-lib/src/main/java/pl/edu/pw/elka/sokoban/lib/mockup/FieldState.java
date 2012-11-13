package pl.edu.pw.elka.sokoban.lib.mockup;

/**
 * Enum class representing field state on mockup.
 */
public enum FieldState {
    
    /**
     * Field with brick, where sokoban cannot stands.
     */
    BRICK,
    
    /**
     * Target field, where crate should be pushed.
     */
    TARGET,
    
    /**
     * Field with crate standing on it.
     */
    CRATE,
    
    /**
     * Main character.
     */
    SOKOBAN,
    
    /**
     * Free place, where sokoban can stands, crate can be pushed.
     */
    FREE

}
