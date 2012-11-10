package pl.edu.pw.elka.sokoban.controller.strategy;

import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Generic strategy to handle events.
 */
public abstract class Strategy {
    
    /**
     * Executes the strategy parsing given event.
     * 
     * @param event event to parse.
     */
    public abstract void execute(final Event event);

}
