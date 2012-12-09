package pl.edu.pw.elka.sokoban.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;
import pl.edu.pw.elka.sokoban.view.event.Event;
import pl.edu.pw.elka.sokoban.view.event.NextStepEvent;
import pl.edu.pw.elka.sokoban.view.event.PreviousStepEvent;


/**
 * Main window of application.
 */
class MainWindow extends JFrame {
    
    private static final long serialVersionUID = -1241078495410054915L;
    
    private final BlockingQueue<Event> blockingQueue;
    private BoardFileHandler boardFileHandler;
    
    private BorderLayout borderLayout;
    
    private JMenuBar menuBar;
    
    private JMenu menuFile;
    private JMenuItem menuSaveBoard;
    private JMenuItem menuLoadBoard;
    
    private MockupPanel mockupPanel;
    
    private JButton nextButton;
    private JButton prevButton;
    private JButton mapEditorButton;
    
    //TODO create object of map editor window's class and correct showMapEditorWindow()
    //private correctMapEditorClass mapEditorWindow;  
    
    
    /**
     * Constructs the new main window.
     * 
     * @param blockingQueue blocking queue to set.
     */
    public MainWindow(final BlockingQueue<Event> blockingQueue) {

        this.blockingQueue = blockingQueue;
        this.boardFileHandler = new BoardFileHandler(blockingQueue);
        
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
    	
    	menuBar = new JMenuBar();
        
        menuFile = new JMenu("File");
        menuSaveBoard = new JMenuItem("Save board");
        menuLoadBoard = new JMenuItem("Load board");
        
        mockupPanel = new MockupPanel();
        
        nextButton = new JButton("Next");
        prevButton = new JButton("Prev");
        mapEditorButton = new JButton("Map editor");
        
    }

    /**
     * Initializes and adds listeners to window components. 
     */
    private void initListeners() {
        
        addKeyListener(new KeyAdapter() {
            
            //TODO fix me
            @Override
            public void keyReleased(KeyEvent e) {
                
                //System.out.println("KeyListener "+e.getKeyCode());
                
                //TODO consider using switch() instead of if-else
                if(e.getKeyCode() == KeyEvent.VK_M) {
                    generateNextStepEvent();
                }
                else if(e.getKeyCode() == KeyEvent.VK_N) {
                    generatePreviousStepEvent();
                }
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
        
        mapEditorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                showMapEditorWindow();
                
            }
        });
        
        menuLoadBoard.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                loadBoardFile();
                
            }
        });
        
        menuSaveBoard.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                saveBoardFile();
                
            }
        });
        
    }
    
    /**
     * Composes windows appearance.
     */
    private void initLayout() {
        
    	setLayout(borderLayout);
    	
    	menuFile.add(menuSaveBoard);
        menuFile.add(menuLoadBoard);
        menuBar.add(menuFile);
        add(menuBar, BorderLayout.NORTH);
    	
    	add(prevButton, BorderLayout.WEST);
    	add(mockupPanel, BorderLayout.CENTER);
    	add(nextButton, BorderLayout.EAST);
    	add(mapEditorButton, BorderLayout.SOUTH);
    	
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
    
    /**
     * Shows map editor window. 
     */
    private void showMapEditorWindow() {
        // TODO Map Editor Window showing - correct and uncomment
        
        //mapEditorWindow.show();
    }
    
    private void loadBoardFile() {
        
        try {
            boardFileHandler.loadBoardFromFile(blockingQueue);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private void saveBoardFile() {
        
        boardFileHandler.saveBoardToFile(mockupPanel.getBoardContent());
        
    }
    
}
