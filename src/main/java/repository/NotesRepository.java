package repository;

import model.Note;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NotesRepository {
    private static final String ADDRESS_FILE = "src/main/resources/Notes.csv";
    private static SimpleDateFormat startTime = new SimpleDateFormat("MM/dd/yyyy");

    public List<Note> selectAllNotes () {
        List<Note> notes = readNotesFromCSV(ADDRESS_FILE);
//        let's print all the person read from CSV file
        return notes;
    }

    public void addNote (Note note) throws FileNotFoundException {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, true))
        {
            writer.append(String.valueOf(note.getId()));
            writer.append(",");
            writer.append(note.getTopic());
            writer.append(",");
            writer.append(String.valueOf(note.getDateOfCreate()));
            writer.append(",");
            writer.append(note.getEmail());
            writer.append(",");
            writer.append(note.getMessage());
            writer.append("\n");

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void deleteNote(List<Note> noteList) {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, false))
        {
            for (int i = 0; i < noteList.size(); i++) {
                if (i!=0) {
                    writer.append("\n");
                }
                Note note = noteList.get(i);
                writer.append(String.valueOf(note.getId()));
                writer.append(",");
                writer.append(note.getTopic());
                writer.append(",");
                writer.append(String.valueOf(note.getDateOfCreate()));
                writer.append(",");
                writer.append(note.getEmail());
                writer.append(",");
                writer.append(note.getMessage());

            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static List<Note> readNotesFromCSV(String fileName) {
        List<Note> notes = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Note note = createNotes(attributes);
                notes.add(note);
                line = br.readLine();
            }
        }
        catch (IOException | ParseException ioe) {
            ioe.printStackTrace();
        }
        return notes;
    }

    private static Note createNotes(String[] metadata) throws ParseException {
        int id = Integer.parseInt(metadata[0]);
        String topic = metadata[1];
        LocalDate dateOfCreate = LocalDate.parse(metadata[2]);
        String email = metadata[3];
        String message = metadata[4];

        // create and return book of this metadata
        return new Note(id, topic, dateOfCreate, email, message);
    }
}
