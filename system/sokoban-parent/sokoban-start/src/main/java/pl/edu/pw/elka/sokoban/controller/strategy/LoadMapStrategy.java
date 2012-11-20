package pl.edu.pw.elka.sokoban.controller.strategy;

import java.io.IOException;

import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;
import pl.edu.pw.elka.sokoban.model.Model;
import pl.edu.pw.elka.sokoban.view.View;
import pl.edu.pw.elka.sokoban.view.event.Event;
import pl.edu.pw.elka.sokoban.view.event.LoadMapEvent;

/**
 * @author pscorek
 *
 */
public class LoadMapStrategy extends Strategy {

    /**
     * @see pl.edu.pw.elka.sokoban.controller.strategy.Strategy#execute(pl.edu.pw.elka.sokoban.view.event.Event)
     */
    @Override
    public void execute(Event event, Model model, View view) {

        LoadMapEvent loadMapEvent = (LoadMapEvent) event;
        
        String mapName = loadMapEvent.getMapName();
        
        try {
            // TODO IO Exception
            Mockup mockup = model.loadMap(mapName);
            
            view.redrawMockup(mockup);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
