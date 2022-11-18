import service.NotesService;

public class NotesApp {
    public static void main(String[] args) {
        NotesService notesService = new NotesService();
        notesService.run();
    }
}