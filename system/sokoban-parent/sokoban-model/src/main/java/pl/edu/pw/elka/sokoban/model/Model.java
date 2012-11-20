package pl.edu.pw.elka.sokoban.model;

import java.io.IOException;

import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;


/**
 * Model facade class.
 */
public class Model {

    private SokobanMap sokobanMap;
    
    /**
     * Constructs the model.
     */
    public Model() {
        
    }
    
    /**
     * Loads map and returns mockup.
     * 
     * @param mapName map name to load.
     * @return mockup.
     * @throws IOException if input/output error occured. 
     */
    public Mockup loadMap(String mapName) throws IOException {
        //TODO filepath
        
        sokobanMap = SokobanMapLoader.loadFromFile("../sokoban-model/src/main/resources/" + mapName);
        
        return sokobanMap.getMockup();
        
    }
    
}
