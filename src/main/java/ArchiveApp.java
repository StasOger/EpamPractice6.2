import service.archiveService;

public class ArchiveApp {
    public static void main(String[] args) {
        archiveService notesService = new archiveService();
        notesService.run();
    }
}