package pl.edu.pw.elka.sokoban.view.event;

/**
 * @author pscorek
 *
 */
public class LoadMapEvent extends Event {

    private String mapName;

    /**
     * @param mapName string equals to name of requested map.
     */
    public LoadMapEvent(String mapName) {
        this.mapName = mapName;
    }

    /**
     * @return string contains map name.
     */
    public String getMapName() {
        return mapName;
    }
    
}
