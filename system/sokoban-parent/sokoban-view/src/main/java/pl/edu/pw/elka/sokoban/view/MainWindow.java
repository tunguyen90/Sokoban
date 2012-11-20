package pl.edu.pw.elka.sokoban.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;
import pl.edu.pw.elka.sokoban.view.event.Event;
import pl.edu.pw.elka.sokoban.view.event.LoadMapEvent;
import pl.edu.pw.elka.sokoban.view.event.NextStepEvent;
import pl.edu.pw.elka.sokoban.view.event.PreviousStepEvent;


/**
 * Main window of application.
 */
class MainWindow extends JFrame {
    
    private static final long serialVersionUID = -1241078495410054915L;
    
    private final BlockingQueue<Event> blockingQueue;
    
    private BorderLayout borderLayout;
    
    private MockupPanel mockupPanel;
    
    private JTextField mapNameField;
    
    private JButton loadButton;
    private JButton nextButton;
    private JButton prevButton;
    
    
    
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
        setTitle("Sokoban");
        
        initComponents();
        initLayout();
        initListeners();
        
        pack();
        
    }
    
    /**
     * Initializes window components.
     */
    private void initComponents() {
    	
    	borderLayout = new BorderLayout();
        
        mockupPanel = new MockupPanel();
        
        mapNameField = new JTextField("map1.txt");
        
        loadButton = new JButton("Load");
        nextButton = new JButton("Next");
        prevButton = new JButton("Prev");
        
    }

    /**
     * Initializes and adds listeners to window components. 
     */
    private void initListeners() {
        
        addKeyListener(new KeyAdapter() {
            
            //TODO fix me
            @Override
            public void keyReleased(KeyEvent e) {
                
                System.out.println("KeyListener "+e.getKeyCode());
                
                //TODO consider using switch() instead of if-else
                if(e.getKeyCode() == KeyEvent.VK_M) {
                    generateNextStepEvent();
                }
                else if(e.getKeyCode() == KeyEvent.VK_N) {
                    generatePreviousStepEvent();
                }
            }
            
            
            
        });
        
        loadButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                blockingQueue.offer(new LoadMapEvent(mapNameField.getText()));
                
            }
            
        });
        
        nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				generateNextStepEvent();
				
			}

		});
    	
        prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			    generatePreviousStepEvent();
				
			}
			
		});
        
    }
    
    /**
     * Composes windows appearance.
     */
    private void initLayout() {
        
    	setLayout(borderLayout);
    	
    	add(mapNameField, BorderLayout.NORTH);
    	add(loadButton, BorderLayout.SOUTH);
    	
    	add(prevButton, BorderLayout.WEST);
    	add(mockupPanel, BorderLayout.CENTER);
    	add(nextButton, BorderLayout.EAST);
    	
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
    
    /**
     * Passes the mockup to redraw.
     * 
     * @param mockup mockup to redraw.
     */
    public void redrawMockup(final Mockup mockup) {
    	
    	mockupPanel.redrawMockup(mockup);
    	pack();
    	
    }
    
    /**
     * Adds next step event to blocking queue. 
     */
    private void generateNextStepEvent() {
        blockingQueue.offer(new NextStepEvent());
    }
    
    /**
     * Adds next step event to blocking queue. 
     */
    private void generatePreviousStepEvent() {
        blockingQueue.offer(new PreviousStepEvent());
    }
    
}
