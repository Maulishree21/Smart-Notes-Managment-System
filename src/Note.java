
import java.util.*;

public class Note {

    private int id;
    private String title;
    private String content;
    private String category;
    private Set<String> tags;
    private List<NoteHistory> history;

    public Note(int id, String title, String content,
                String category, Set<String> tags) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = new LinkedHashSet<>(tags);
        this.history = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public Set<String> getTags() {
        return new LinkedHashSet<>(tags);
    }

    public List<NoteHistory> getHistory() {
        return history;
    }

    public void editNote(String newTitle, String newContent,
                         String newCategory, Set<String> newTags) {

        history.add(new NoteHistory(
                title, content, category, tags
        ));

        this.title = newTitle;
        this.content = newContent;
        this.category = newCategory;
        this.tags = new LinkedHashSet<>(newTags);
    }

    public void displayNote() {
        System.out.println("\nNote ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Category: " + category);
        System.out.println("Tags: " + tags);
        System.out.println("Edit History Versions: " + history.size());
        System.out.println("----------------------------");
    }
}
