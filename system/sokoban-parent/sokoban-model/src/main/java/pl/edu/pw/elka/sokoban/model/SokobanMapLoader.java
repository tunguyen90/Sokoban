package pl.edu.pw.elka.sokoban.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import pl.edu.pw.elka.sokoban.lib.mockup.FieldState;
import pl.edu.pw.elka.sokoban.lib.mockup.Mockup;


/**
 * API which allows to load map from file.
 */
class SokobanMapLoader {
    
    public static SokobanMap loadFromFile(final String path) throws IOException {
        
        File file = new File(path);
        
        return loadFromFile(file);
        
    }
    
    public static SokobanMap loadFromFile(final File file) throws IOException {
        
        List<String> listOfLines = readFileToListOfLines(file);
        
        int height = listOfLines.size();
        int width = listOfLines.get(0).length();
        
        Mockup board = new Mockup(width, height);
        
        int rowNumber = -1; // we start from incrementing
        
        for(String row : listOfLines) {
            
            rowNumber++;

            for(int i = 0; i < row.length(); i++) {
                
                char letterRepresentation = row.charAt(i);
                
                FieldState parsedFieldState = parseField(letterRepresentation);
                
                board.setFieldStateOnPosition(parsedFieldState, i, rowNumber);
                
            }
            
        }
        
        SokobanMap map = new SokobanMap(board);
        
        return map;
        
    }

    /**
     * Reads the file into the list of lines.
     * 
     * @param file file to read.
     * @throws IOException if general IOException occurs.
     */
    private static List<String> readFileToListOfLines(final File file)
            throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(dataInputStream);
        
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
        String line = "";
        List<String> listOfLines = new LinkedList<String>();
        
        while((line = bufferedReader.readLine()) != null) {

            listOfLines.add(line);
            
        }
        
        bufferedReader.close();
        
        return listOfLines;
        
    }
    
    /**
     * Parses letter representation to field state object.
     * 
     * @param letterRepresentation letter representation of the field.
     * @return field state value.
     * @throws IOException if inappropriate letter occurs.
     */
    private static FieldState parseField(final char letterRepresentation) throws IOException {
        
        if(FieldState.BRICK.toString().equals(""+letterRepresentation))
            return FieldState.BRICK;
        
        else if(FieldState.CRATE.toString().equals(""+letterRepresentation))
            return FieldState.CRATE;
        
        else if(FieldState.CRATE_ON_GOAL.toString().equals(""+letterRepresentation))
            return FieldState.CRATE_ON_GOAL;
        
        else if(FieldState.FREE.toString().equals(""+letterRepresentation))
            return FieldState.FREE;
        
        else if(FieldState.GOAL.toString().equals(""+letterRepresentation))
            return FieldState.GOAL;
        
        else if(FieldState.SOKOBAN.toString().equals(""+letterRepresentation))
            return FieldState.SOKOBAN;
        
        else if(FieldState.SOKOBAN_ON_GOAL.equals(""+letterRepresentation))
            return FieldState.SOKOBAN_ON_GOAL;
        
        else
            throw new IOException();
        
    }

}
