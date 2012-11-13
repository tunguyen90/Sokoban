package pl.edu.pw.elka.sokoban.view;

import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * Main window of application.
 */
class MainWindow extends JFrame {
    
    private static final long serialVersionUID = -1241078495410054915L;
    
    private BlockingQueue<Event> blockingQueue;
    
    /**
     * Constructs the new main window.
     * 
     * @param blockingQueue blocking queue to set.
     */
    public MainWindow(final BlockingQueue<Event> blockingQueue) {

        this.blockingQueue = blockingQueue;
        
        init();
        
    }
    
    /**
     * Inits the window.
     */
    private void init() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        
    }
    
    /**
     * Presents the main window.
     */
    public void present() {
        
        setVisible(true);
        
    }
    
    /**
     * Hides the main window.
     */
    public void unpresent() {
        
        setVisible(false);
        
    }
    
}
