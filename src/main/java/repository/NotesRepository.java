package repository;

import model.Note;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesRepository {
    private static final String ADDRESS_FILE = "src/main/resources/Notes.csv";
    private static SimpleDateFormat startTime = new SimpleDateFormat("MM/dd/yyyy");

    public List<Note> selectAllNotes () {
        List<Note> notes = readNotesFromCSV(ADDRESS_FILE);
//        let's print all the person read from CSV file
        return notes;
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
        Date dateOfCreate = startTime.parse(metadata[2]);
        String email = metadata[3];
        String message = metadata[4];

        // create and return book of this metadata
        return new Note(id, topic, dateOfCreate, email, message);
    }
}
