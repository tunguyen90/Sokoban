package pl.edu.pw.elka.sokoban.controller.strategy;

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
     * @see pl.edu.pw.elka.sokoban.controller.strategy.Strategy#execute(pl.edu.pw.elka.sokoban.view.event.Event, pl.edu.pw.elka.sokoban.model.Model, pl.edu.pw.elka.sokoban.view.View)
     */
    @Override
    public void execute(final Event event, final Model model, final View view) {

        LoadMapEvent loadMapEvent = (LoadMapEvent) event;
        
        String mapName = loadMapEvent.getMapName();
        
//        try {
//            // TODO IO Exception
////            Mockup mockup = model.loadMap(mapName);
////            
////            view.redrawMockup(mockup);
//            
//        } catch (IOException e) {
//            
//            e.printStackTrace();
//            
//        }
        
    }

}
