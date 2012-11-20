/**
 * 
 */
package pl.edu.pw.elka.sokoban.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.edu.pw.elka.sokoban.lib.mockup.FieldState;
import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;

/**
 * @author pscorek
 *
 */
class MockupPanel extends JPanel {
    
    private static final long serialVersionUID = 9064629195273852003L;
    
    private GridLayout boardLayout;
    private JPanel boardPanel;
    private int boardWidth;
    private int boardHeight;
    private JLabel[][] boardContent;
    
    /**
     * Constructor
     */
    public MockupPanel() {
        
    }

    public void redrawMockup(Mockup mockup) {
        
        initMockup(mockup);
        
        for(int y = 0; y < boardHeight; y++) {
            for(int x = 0; x < boardWidth; x++) {
                
                setFieldText(mockup.getFieldStateOnPosition(x, y), x, y);
                
            }
        }
        
    }

    /**
     * Initializes array of labels for fields states.
     * 
     * @param mockup mockup to redraw.
     */
    private void initMockup(Mockup mockup) {
        
        if(boardHeight != mockup.getHeight() || boardWidth != mockup.getWidth()) {
            
            boardWidth = mockup.getWidth();
            boardHeight = mockup.getHeight();
            
            boardContent = new JLabel[boardHeight][];
            
            for(int y = 0; y < boardHeight; y++) {
                boardContent[y] = new JLabel[boardWidth];
            }
        
            if(boardPanel != null)
                remove(boardPanel);
            boardLayout = new GridLayout(boardHeight, boardWidth, 1, 1);
            boardPanel = new JPanel(boardLayout);
            
            for(int y = 0; y < boardHeight; y++) {
                for(int x = 0; x < boardWidth; x++) {
                    
                    boardContent[y][x] = new JLabel("D");
                    boardPanel.add(boardContent[y][x]);
                    
                }
            }
            
            add(boardPanel);
            
        }
        
    }

    /**
     * Sets label's text that represents field content.
     * 
     * @param state state of field to set.
     * @param y vertical coordinate of set field.
     * @param x horizontal coordinate of set field.
     */
    private void setFieldText(final FieldState state, int x, int y) {
        
        boardContent[y][x].setText(state.toString());
        
    }
    
}
