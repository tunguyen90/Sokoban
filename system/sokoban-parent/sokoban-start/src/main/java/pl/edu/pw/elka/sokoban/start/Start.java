package pl.edu.pw.elka.sokoban.start;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.elka.sokoban.controller.Controller;
import pl.edu.pw.elka.sokoban.model.Model;
import pl.edu.pw.elka.sokoban.view.View;
import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Starter class.
 */
public class Start {
    
    /**
     * Runs the application.
     * 
     * @param args unused
     */
    public static void main(final String[] args) {

        BlockingQueue<Event> blockingQueue = new LinkedBlockingQueue<Event>();
        
        Model model = new Model();
        View view = new View(blockingQueue);
        
        Controller controller = new Controller(blockingQueue, view, model);
        controller.go();
        
    }

}
