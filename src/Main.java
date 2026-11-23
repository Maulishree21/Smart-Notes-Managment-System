
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static NoteManager manager = new NoteManager();

    // Convert comma-separated tags into a Set
    public static Set<String> getTags() {

        Set<String> tags = new LinkedHashSet<>();

        System.out.print("Enter tags (comma separated): ");

        String input = sc.nextLine();

        for (String tag : input.split(",")) {

            if (!tag.trim().isEmpty()) {
                tags.add(tag.trim().toLowerCase());
            }
        }

        return tags;
    }

    public static void main(String[] args) {

        int choice = 0;

        do {

            System.out.println("\n===== SMART NOTES MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Search Notes");
            System.out.println("4. Find Related Notes");
            System.out.println("5. Edit Note");
            System.out.println("6. View Edit History");
            System.out.println("7. Export Note");
            System.out.println("8. Frequently Used Topics");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter content: ");
                    String content = sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    Set<String> tags = getTags();

                    manager.createNote(
                            title, content, category, tags
                    );

                    break;

                case 2:

                    manager.displayAllNotes();
                    break;

                case 3:

                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();

                    manager.searchNotes(keyword);
                    break;

                case 4:

                    System.out.print("Enter Note ID: ");
                    int relatedId = Integer.parseInt(sc.nextLine());

                    manager.findRelatedNotes(relatedId);
                    break;

                case 5:

                    System.out.print("Enter Note ID to edit: ");
                    int editId = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter new content: ");
                    String newContent = sc.nextLine();

                    System.out.print("Enter new category: ");
                    String newCategory = sc.nextLine();

                    Set<String> newTags = getTags();

                    manager.editNote(
                            editId, newTitle, newContent,
                            newCategory, newTags
                    );

                    break;

                case 6:

                    System.out.print("Enter Note ID: ");
                    int historyId = Integer.parseInt(sc.nextLine());

                    manager.displayHistory(historyId);
                    break;

                case 7:

                    System.out.print("Enter Note ID to export: ");
                    int exportId = Integer.parseInt(sc.nextLine());

                    manager.exportNote(exportId);
                    break;

                case 8:

                    manager.frequentlyUsedTopics();
                    break;

                case 9:

                    System.out.println("Thank you for using Smart Notes!");
                    break;

                default:

                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 9);

        sc.close();
    }
}
