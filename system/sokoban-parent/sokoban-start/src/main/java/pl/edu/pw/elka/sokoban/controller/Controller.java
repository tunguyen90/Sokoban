package pl.edu.pw.elka.sokoban.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import pl.edu.pw.elka.sokoban.controller.strategy.Strategy;
import pl.edu.pw.elka.sokoban.model.Model;
import pl.edu.pw.elka.sokoban.view.View;
import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Controller facade class.
 */
public class Controller {
    
    private Logger logger = Logger.getLogger(Controller.class);
    
    private BlockingQueue<Event> blockingQueue;
    private View view;
    private Model model;
    
    private Map<Class<? extends Event>, Strategy> eventStrategyMap;
    
    private boolean closed = false;
    
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
        
        eventStrategyMap = new HashMap<Class<? extends Event>, Strategy>();
        
        initMap();
        
    }
    
    /**
     * Runs the main controller loop.
     */
    public void go() {
        
        view.present();
        
        while(!closed) {
            
            try {
                
                Event event = blockingQueue.take();
                
                Strategy strategy = eventStrategyMap.get(event.getClass());
                
                strategy.execute(event);
                
            } catch(InterruptedException e) {

                logger.fatal("InterruptedException, application is going done", e);
                
                throw new RuntimeException(e);
                
            }
            
        }
        
    }
    
    private void initMap() {
        
    }
    
}
