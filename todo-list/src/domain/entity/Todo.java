package domain.entity;

public class Todo {
    private final int id;
    private String title;
    private boolean done;

    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }
}
