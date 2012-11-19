package pl.edu.pw.elka.sokoban.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


/**
 * Test for {@link pl.edu.pw.elka.sokoban.model.SokobanMapLoader}
 */
public class SokobanMapLoaderTest {

    /**
     * Test method for {@link pl.edu.pw.elka.sokoban.model.SokobanMapLoader#loadFromFile(java.lang.String)}.
     */
    @Test
    public final void testLoadFromFileFile() {
        
        File file;
        
        try {
            
            file = new File("src/main/resources/map1.txt");

            SokobanMap map = SokobanMapLoader.loadFromFile(file);
            
            String expectedMap = "XXXXXX\n" +
                                 "X X@ X\n" +
                                 "XXXX X\n" +
                                 "X    X\n" +
                                 "Xo # X\n" +
                                 "XXXXXX\n";
            
            assertEquals(expectedMap, map.toString());
            
        } catch(IOException e) {
            
            e.printStackTrace();
            
            fail(e.getMessage());
            
        }
        
    }

}
