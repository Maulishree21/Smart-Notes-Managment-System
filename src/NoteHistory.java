
import java.time.LocalDateTime;
import java.util.Set;
import java.util.LinkedHashSet;

public class NoteHistory {

    private String title;
    private String content;
    private String category;
    private Set<String> tags;
    private LocalDateTime editedAt;

    public NoteHistory(String title, String content,
                       String category, Set<String> tags) {

        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = new LinkedHashSet<>(tags);
        this.editedAt = LocalDateTime.now();
    }

    public void displayHistory() {
        System.out.println("Edited at: " + editedAt);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Category: " + category);
        System.out.println("Tags: " + tags);
        System.out.println("----------------------------");
    }
}
