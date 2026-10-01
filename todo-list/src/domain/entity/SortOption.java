package domain.entity;

import java.util.Comparator;

public enum SortOption {
    TITLE_ASC(Comparator.comparing(Todo::getTitle)),
    TITLE_DESC(Comparator.comparing(Todo::getTitle).reversed()),
    STATUS_DONE_FIRST(Comparator.comparing(Todo::isDone).reversed()),
    STATUS_UNDONE_FIRST(Comparator.comparing(Todo::isDone));

    private final Comparator<Todo> comparator;

    SortOption(Comparator<Todo> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Todo> comparator() {
        return comparator;
    }
}
