package pl.edu.pw.elka.sokoban.view;

import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Main window of application.
 */
class MainWindow {
    
    private BlockingQueue<Event> blockingQueue;

    /**
     * Constructs the new main window.
     * 
     * @param blockingQueue blocking queue to set.
     */
    public MainWindow(final BlockingQueue<Event> blockingQueue) {

        this.blockingQueue = blockingQueue;
        
    }
    
}
