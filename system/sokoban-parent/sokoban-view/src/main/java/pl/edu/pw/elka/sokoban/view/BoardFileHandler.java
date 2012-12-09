package pl.edu.pw.elka.sokoban.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import pl.edu.pw.elka.sokoban.lib.Point;
import pl.edu.pw.elka.sokoban.lib.mockup.ConstantField;
import pl.edu.pw.elka.sokoban.lib.mockup.FieldState;
import pl.edu.pw.elka.sokoban.view.event.Event;
import pl.edu.pw.elka.sokoban.view.event.SetNewMapEvent;

/**
 * 
 */
public class BoardFileHandler {

    @SuppressWarnings("unused")
    private BlockingQueue<Event> blockingQueue;
    
    private static JFileChooser fileChooser;
    
    /**
     * Constructor.
     * 
     * @param blockingQueue
     */
    public BoardFileHandler(BlockingQueue<Event> blockingQueue) {
        this.blockingQueue = blockingQueue;
        
        fileChooser = new JFileChooser();
    }

    /**
     * @param blockingQueue 
     * @throws IOException 
     */
    public void loadBoardFromFile(BlockingQueue<Event> blockingQueue) throws IOException {
        
        fileChooser.setCurrentDirectory(new File("../sokoban-model/src/main/resources"));
        
        if(JFileChooser.APPROVE_OPTION  != fileChooser.showOpenDialog(null))
            return;
        
        File selectedFile = fileChooser.getSelectedFile();
        blockingQueue.offer(loadFromFile(selectedFile));
        
    }
    
    /**
     * @param boardContent 
     */
    public void saveBoardToFile(JLabel[][] boardContent) {
        
        fileChooser.setCurrentDirectory(new File("../sokoban-model/src/main/resources"));
        
        if(JFileChooser.APPROVE_OPTION  != fileChooser.showSaveDialog(null))
            return;
        
        File selectedFile = fileChooser.getSelectedFile();
        try {
            saveToFile(selectedFile, boardContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * @param file
     * @return object of class SetNewMapEvent.
     * @throws IOException
     */
    public SetNewMapEvent loadFromFile(final File file) throws IOException {
        
        List<String> listOfLines = readFileToListOfLines(file);
        
        int height = listOfLines.size();
        int width = listOfLines.get(0).length();
        
        Point startPoint = new Point(0, 0);
        ConstantField[][] constantFields = new ConstantField[height][width];
        List<Point> listOfBoxes = new LinkedList<Point>();
        Point[][] points = new Point[height][width];
        
        
        int rowNumber = -1; // we start from incrementing
        
        for(String row : listOfLines) {
            
            rowNumber++;

            for(int i = 0; i < row.length(); i++) {
                
                points[rowNumber][i] = new Point(rowNumber, i);
                
                char letterRepresentation = row.charAt(i);
                
                FieldState parsedFieldState = parseField(letterRepresentation);
                
                switch(parsedFieldState) {
                    case BRICK: {
                        constantFields[rowNumber][i] = ConstantField.WALL;
                        break;
                    }
                    case GOAL: {
                        constantFields[rowNumber][i] = ConstantField.GOAL;
                        break;
                    }
                    case SOKOBAN_ON_GOAL: {
                        constantFields[rowNumber][i] = ConstantField.GOAL;
                        startPoint = new Point(rowNumber, i);
                        break;
                    }
                    case SOKOBAN: {
                        constantFields[rowNumber][i] = ConstantField.FREE;
                        startPoint = new Point(rowNumber, i);
                        break;
                    }
                    case CRATE: {
                        constantFields[rowNumber][i] = ConstantField.FREE;
                        listOfBoxes.add(new Point(rowNumber, i));
                        break;
                    }
                    case CRATE_ON_GOAL: {
                        constantFields[rowNumber][i] = ConstantField.GOAL;
                        listOfBoxes.add(new Point(rowNumber, i));
                        break;
                    }
                    case FREE: {
                        constantFields[rowNumber][i] = ConstantField.FREE;
                        break;
                    }
                }
            }
        }
        
        
        return new SetNewMapEvent(startPoint, constantFields, listOfBoxes, points);
        
    }

    /**
     * Reads the file into the list of lines.
     * 
     * @param file file to read.
     * @throws IOException if general IOException occurs.
     */
    private List<String> readFileToListOfLines(final File file)
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
        inputStreamReader.close();
        dataInputStream.close();
        fileInputStream.close();
        
        return listOfLines;
        
    }
    
    /**
     * @param boardContent 
     * 
     */
    private void saveToFile(File selectedFile, JLabel[][] boardContent) throws IOException {
        
        String boardString = new String();
        
        int height = boardContent.length;
        int width = boardContent[0].length;
        
        for(int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                boardString += boardContent[y][x].toString();
            }
            boardString += "\n";
        }
        
        saveBoardStringToFile(selectedFile, boardString);
    }
    
    private void saveBoardStringToFile(File selectedFile,
            String boardString) throws IOException {
        
        FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(dataOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        
        bufferedWriter.write(boardString);
        
        bufferedWriter.close();
        outputStreamWriter.close();
        dataOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Parses letter representation to field state object.
     * 
     * @param letterRepresentation letter representation of the field.
     * @return field state value.
     * @throws IOException if inappropriate letter occurs.
     */
    private FieldState parseField(final char letterRepresentation) throws IOException {
        
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
