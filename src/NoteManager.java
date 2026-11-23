
import java.util.*;
import java.io.*;

public class NoteManager {

    private List<Note> notes;
    private int nextId;

    public NoteManager() {
        notes = new ArrayList<>();
        nextId = 1;
    }

    // 1. Create Note
    public void createNote(String title, String content,
                           String category, Set<String> tags) {

        Note note = new Note(
                nextId++, title, content, category, tags
        );

        notes.add(note);
        System.out.println("Note created successfully!");
    }

    // 2. Display All Notes
    public void displayAllNotes() {

        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }

        for (Note note : notes) {
            note.displayNote();
        }
    }

    // Find Note by ID
    public Note findById(int id) {

        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        return null;
    }

    // 3. Search Notes by Keywords
    public void searchNotes(String keyword) {

        boolean found = false;
        keyword = keyword.toLowerCase();

        for (Note note : notes) {

            if (note.getTitle().toLowerCase().contains(keyword)
                    || note.getContent().toLowerCase().contains(keyword)
                    || note.getCategory().toLowerCase().contains(keyword)
                    || note.getTags().toString().toLowerCase().contains(keyword)) {

                note.displayNote();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching notes found.");
        }
    }

    // 4. Find Related Notes using Shared Tags
    public void findRelatedNotes(int id) {

        Note selected = findById(id);

        if (selected == null) {
            System.out.println("Note not found.");
            return;
        }

        boolean found = false;

        for (Note note : notes) {

            if (note.getId() == id) {
                continue;
            }

            Set<String> commonTags = new HashSet<>(selected.getTags());

            commonTags.retainAll(note.getTags());

            if (!commonTags.isEmpty()) {

                System.out.println("Related Note:");
                note.displayNote();

                System.out.println("Common Tags: " + commonTags);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No related notes found.");
        }
    }

    // 5. Edit Note
    public void editNote(int id, String title, String content,
                         String category, Set<String> tags) {

        Note note = findById(id);

        if (note == null) {
            System.out.println("Note not found.");
            return;
        }

        note.editNote(title, content, category, tags);

        System.out.println("Note updated successfully!");
    }

    // 6. Display Edit History
    public void displayHistory(int id) {

        Note note = findById(id);

        if (note == null) {
            System.out.println("Note not found.");
            return;
        }

        if (note.getHistory().isEmpty()) {
            System.out.println("No edit history available.");
            return;
        }

        for (NoteHistory h : note.getHistory()) {
            h.displayHistory();
        }
    }

    // 7. Export Note into Text File
    public void exportNote(int id) {

        Note note = findById(id);

        if (note == null) {
            System.out.println("Note not found.");
            return;
        }

        File folder = new File("exports");
        folder.mkdirs();

        String fileName = "exports/note_" + id + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("NOTE ID: " + note.getId() + "\n");
            writer.write("TITLE: " + note.getTitle() + "\n");
            writer.write("CATEGORY: " + note.getCategory() + "\n");
            writer.write("TAGS: " + note.getTags() + "\n");
            writer.write("CONTENT: " + note.getContent() + "\n");

            System.out.println("Note exported to " + fileName);

        } catch (IOException e) {
            System.out.println("Error exporting note: " + e.getMessage());
        }
    }

    // 8. Frequently Used Topics
    public void frequentlyUsedTopics() {

        Map<String, Integer> topicCount = new HashMap<>();

        for (Note note : notes) {

            for (String tag : note.getTags()) {

                topicCount.put(
                        tag,
                        topicCount.getOrDefault(tag, 0) + 1
                );
            }
        }

        if (topicCount.isEmpty()) {
            System.out.println("No topics available.");
            return;
        }

        List<Map.Entry<String, Integer>> topics =
                new ArrayList<>(topicCount.entrySet());

        topics.sort((a, b) ->
                b.getValue().compareTo(a.getValue())
        );

        System.out.println("\nFrequently Used Topics:");

        for (Map.Entry<String, Integer> entry : topics) {

            System.out.println(
                    entry.getKey() + " : " +
                            entry.getValue() + " notes"
            );
        }
    }
}
