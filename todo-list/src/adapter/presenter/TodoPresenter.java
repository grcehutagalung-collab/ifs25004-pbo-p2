package adapter.presenter;

import domain.entity.Todo;
import java.util.List;

public class TodoPresenter {
    private String format(Todo t) {
        String status = t.isDone() ? "[✓]" : "[ ]";
        return String.format("%d | %s %s", t.getId(), status, t.getTitle());
    }

    private void printList(List<Todo> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Todo t : list) {
                System.out.println(format(t));
            }
        }
    }

    public void showTodos(List<Todo> list) {
        printList(list, "Daftar Todo:", "- Belum ada todo!");
    }

    public void showSearchResults(List<Todo> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Todo tidak ditemukan!");
    }

    public void showSortedTodos(List<Todo> list) {
        printList(list, "Daftar Todo (Terurut):", "- Belum ada todo!");
    }

    public void showAddSuccess(Todo t) {
        System.out.printf("Berhasil menambah todo: %s%n", format(t));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus todo.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus todo dengan ID: %d.%n", id);
    }

    public void showMarkDoneSuccess() {
        System.out.println("Berhasil menandai todo sebagai selesai.");
    }

    public void showMarkUndoneSuccess() {
        System.out.println("Berhasil menandai todo sebagai belum selesai.");
    }

    public void showMarkFailed(int id) {
        System.out.printf("[!] Todo dengan ID: %d tidak ditemukan.%n", id);
    }

    public void showEditSuccess() {
        System.out.println("Berhasil mengubah judul todo.");
    }

    public void showEditFailed(int id) {
        System.out.printf("[!] Todo dengan ID: %d tidak ditemukan.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan urutan tidak valid!");
    }
}
