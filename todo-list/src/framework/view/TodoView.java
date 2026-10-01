package framework.view;

import adapter.presenter.TodoPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.TodoUseCase;

public class TodoView {
    private final TodoUseCase useCase;
    private final TodoPresenter presenter;

    public TodoView(TodoUseCase useCase, TodoPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        boolean running = true;
        while (running) {
            presenter.showTodos(useCase.getAllTodos());
            printMenu();
            String input = InputUtil.input("Pilih");
            switch (input) {
                case "1" -> addTodo();
                case "2" -> markDone();
                case "3" -> markUndone();
                case "4" -> editTodo();
                case "5" -> searchTodo();
                case "6" -> sortTodo();
                case "7" -> removeTodo();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running)
                System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah Todo");
        System.out.println("2. Tandai Selesai");
        System.out.println("3. Tandai Belum Selesai");
        System.out.println("4. Ubah Judul");
        System.out.println("5. Cari");
        System.out.println("6. Urutkan");
        System.out.println("7. Hapus");
        System.out.println("x. Keluar");
    }

    private void addTodo() {
        System.out.println("[Tambah Todo]");
        String title = InputUtil.input("Judul (x Jika Batal)");
        if (title.equals("x"))
            return;
        if (title.isBlank()) {
            System.out.println("[!] Judul tidak boleh kosong!");
            return;
        }
        presenter.showAddSuccess(useCase.addTodo(title));
    }

    private void markDone() {
        System.out.println("[Tandai Selesai]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        if (useCase.markDone(id)) {
            presenter.showMarkDoneSuccess();
        } else {
            presenter.showMarkFailed(id);
        }
    }

    private void markUndone() {
        System.out.println("[Tandai Belum Selesai]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        if (useCase.markUndone(id)) {
            presenter.showMarkUndoneSuccess();
        } else {
            presenter.showMarkFailed(id);
        }
    }

    private void editTodo() {
        System.out.println("[Ubah Judul]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        String newTitle = InputUtil.input("Judul Baru (x Jika Batal)");
        if (newTitle.equals("x"))
            return;
        if (newTitle.isBlank()) {
            System.out.println("[!] Judul tidak boleh kosong!");
            return;
        }
        if (useCase.editTitle(id, newTitle)) {
            presenter.showEditSuccess();
        } else {
            presenter.showEditFailed(id);
        }
    }

    private void searchTodo() {
        System.out.println("[Cari Todo]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equals("x")) {
            presenter.showSearchResults(useCase.searchTodos(keyword), keyword);
        }
    }

    private void sortTodo() {
        System.out.println("[Urutkan Todo]");
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Judul (A-Z)");
        System.out.println("2. Judul (Z-A)");
        System.out.println("3. Selesai Dulu");
        System.out.println("4. Belum Selesai Dulu");
        System.out.println("x. Batal");
        String input = InputUtil.input("Pilih");
        if (input.equals("x"))
            return;
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }
        presenter.showSortedTodos(useCase.sortTodos(option));
    }

    private void removeTodo() {
        System.out.println("[Hapus Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        if (useCase.removeTodo(id)) {
            presenter.showRemoveSuccess();
        } else {
            presenter.showRemoveFailed(id);
        }
    }

    private Integer parseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            presenter.showInvalidId();
            return null;
        }
    }

    private SortOption mapSortOption(String input) {
        return switch (input) {
            case "1" -> SortOption.TITLE_ASC;
            case "2" -> SortOption.TITLE_DESC;
            case "3" -> SortOption.STATUS_DONE_FIRST;
            case "4" -> SortOption.STATUS_UNDONE_FIRST;
            default -> null;
        };
    }
}
