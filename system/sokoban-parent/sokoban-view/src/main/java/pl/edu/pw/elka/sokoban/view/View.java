package pl.edu.pw.elka.sokoban.view;

import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

import pl.edu.pw.elka.sokoban.view.event.Event;


/**
 * View facade class.
 */
public class View {

    private BlockingQueue<Event> blockingQueue;
    
    private MainWindow mainWindow;
    
    /**
     * Constructs the new view tier.
     * 
     * @param blockingQueue blocking queue to set.
     */
    public View(final BlockingQueue<Event> blockingQueue) {

        this.blockingQueue = blockingQueue;
        
        mainWindow = new MainWindow(blockingQueue);

    }
    
    /**
     * Presents the application.
     */
    public void present() {
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {

                mainWindow.present();
                
            }
            
        });
        
    }
    
    /**
     * Hides the application.
     */
    public void unpresent() {
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {

                mainWindow.unpresent();
                
            }
            
        });
        
    }

}
