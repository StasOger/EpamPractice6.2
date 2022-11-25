package service;

import model.Note;
import repository.NotesRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.LocalDate;

public class NotesService {

    private NotesRepository notesRepository = new NotesRepository();

    public void run() {
        try {
            List<Note> noteList = notesRepository.selectAllNotes();
            for (Note n: noteList){
                if (n.getId()>0 && n.getId()<10){
                    System.out.println(n);
                }
            }
            userOperation();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    private void userOperation() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n"+
                "- Enter \"1\" for search;\n" +
                "- Enter \"2\" for sort;\n" +
                "- Enter \"3\" for add note;\n" +
                "- Enter \"4\" for delete note;\n" +
                "for end enter \"exit\"\n";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.print("Enter command: ");
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    search();
                    break;
                case "2":
                    sort();
                    break;
                case "3":
                    add();
                    break;
                case "4":
                    delete();
                    break;
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");

            }
        }
    }

    private void delete() {
        List<Note> noteList = notesRepository.selectAllNotes();
        List<Note> newNoteList = new ArrayList<>();
        for (Note n: noteList){
            System.out.println(n);
        }

        System.out.println("Enter id of book which you want to delete: ");
        Scanner in1 = new Scanner(System.in);
        int idOfDeletedNote = in1.nextInt();
        for (Note n: noteList){
            if (n.getId() != idOfDeletedNote){
                newNoteList.add(n);
            }
        }
        notesRepository.deleteNote(newNoteList);
    }

    private void add() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter topic of new note: ");
        Scanner in1 = new Scanner(System.in);
        String notesTopic = in1.nextLine();

        LocalDate date = LocalDate.now(); // получаем текущую дату

        System.out.println("Enter email: ");
        String email = in1.nextLine();

        System.out.println("Enter message: ");
        String message = in1.nextLine();

        List<Note> noteList = notesRepository.selectAllNotes();

        notesRepository.addNote(new Note(noteList.size()+1, notesTopic, date, email, message));

//        String message = ("N added!"+bookNewName+" "+bookNewAuthor+" "+ yearOfNewBook);
//        for (User user: userList){
//            String email = user.getEmail();
////            emailService.sendEmail(email, message);
//        }

        System.out.println("Note added!");
    }

    private void sort() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n"+
                "- Enter \"1\" for sort by date;\n" +
                "- Enter \"2\" for sort by alfphabet;\n" +
                "for end enter \"exit\"\n";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.print("Enter command: ");
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    sortByDate();
                    break;
                case "2":
                    sortByAlfphabet();
                    break;
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");

            }
        }
    }

    private void sortByAlfphabet() {
        List<Note> noteList = notesRepository.selectAllNotes();
        noteList.sort(COMPARE_BY_ALFPHABET);

        for(Note n: noteList){
            System.out.println(n);
        }
    }
    private static final Comparator<Note> COMPARE_BY_ALFPHABET = Comparator.comparing(Note::getTopic);

    private void sortByDate() {
        List<Note> noteList = notesRepository.selectAllNotes();
        noteList.sort(COMPARE_BY_DATE);

        for(Note n: noteList){
            System.out.println(n);
        }
    }

    private static final Comparator<Note> COMPARE_BY_DATE = Comparator.comparing(Note::getDateOfCreate);


    private void search() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "Hi, User! \n"+
                "- Enter \"1\" for find note by id;\n" +
                "- Enter \"2\" for find note by date;\n" +
                "- Enter \"3\" for find note by topic;\n" +
                "- Enter \"4\" for find by occurrence of words; \n" +
                "for end enter \"exit\"\n";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.print("Enter command: ");
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    findNoteById();
                    break;
                case "2":
                    findNoteByDate();
                    break;
                case "3":
                    findBookByTopic();
                    break;
                case "4":
                    findByOccurrenceOfWords();
                    break;
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");

            }
        }
    }

    private void findBookByTopic() {
        System.out.println("Enter topic: ");
        Scanner in1 = new Scanner(System.in);
        String noteTopic = in1.nextLine();
        List<Note> noteList = notesRepository.selectAllNotes();
        for (Note n: noteList){
            if (noteTopic.equals(n.getTopic())){
                System.out.println(n);
            }
        }
    }

    private void findNoteByDate() throws ParseException {
        System.out.println("Enter date (year-mounth-day): ");
        Scanner in1 = new Scanner(System.in);
        String noteDate = in1.nextLine();

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        LocalDate docDate= LocalDate.parse(noteDate);

        List<Note> noteList = notesRepository.selectAllNotes();
        for (Note n: noteList){
            int result = docDate.compareTo(n.getDateOfCreate());
            if (result == 0){
                System.out.println(n);
            }
        }
    }

    private void findNoteById() {
        System.out.println("Enter id: ");
        Scanner in1 = new Scanner(System.in);
        int noteId = in1.nextInt();
        List<Note> noteList = notesRepository.selectAllNotes();
        for (Note n: noteList){
            if (noteId == n.getId()){
                System.out.println(n);
            }
        }
    }

    private void findByOccurrenceOfWords() {
        System.out.println("Enter word: ");
        Scanner in1 = new Scanner(System.in);
        String str = in1.nextLine();

        List<Note> noteList = notesRepository.selectAllNotes();
        for (Note n: noteList){
            String topic = n.getTopic();
            String message = n.getMessage();
            if (topic.contains(str) || message.contains(str)){
                System.out.println(n);
            }
        }
    }
}
