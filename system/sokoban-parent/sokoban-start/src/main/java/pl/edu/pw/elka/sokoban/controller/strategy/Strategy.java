package pl.edu.pw.elka.sokoban.controller.strategy;

import pl.edu.pw.elka.sokoban.model.Model;
import pl.edu.pw.elka.sokoban.view.View;
import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Generic strategy to handle events.
 */
public abstract class Strategy {
    
    /**
     * Executes the strategy parsing given event.
     * 
     * @param event event to parse.
     * @param model 
     * @param view 
     * TODO
     */
    public abstract void execute(final Event event, Model model, View view);

}
