package service;

import model.Note;
import repository.NotesRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

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
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void userOperation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n"+
                "- Enter \"1\" for search;\n" +
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
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");

            }
        }
    }

    private void sort() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n"+
                "- Enter \"1\" for sort by date;\n" +
                "- Enter \"2\" for sort by date;\n" +
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

    }

    private void sortByDate() {

    }

    private void search() {

    }
}
