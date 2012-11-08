package pl.edu.pw.elka.sokoban.controller;

import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.sokoban.model.Model;
import pl.edu.pw.elka.sokoban.view.View;
import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Controller facade class.
 */
public class Controller {
    
    private BlockingQueue<Event> blockingQueue;
    private View view;
    private Model model;
    
    /**
     * Constructs the new controller.
     * 
     * @param blockingQueue blocking queue to set.
     * @param view view to set.
     * @param model model to set.
     */
    public Controller(final BlockingQueue<Event> blockingQueue, final View view, final Model model) {

        this.blockingQueue = blockingQueue;
        this.view = view;
        this.model = model;
        
    }
    
    /**
     * Runs the main controller loop.
     */
    public void go() {
        
    }
    
}
