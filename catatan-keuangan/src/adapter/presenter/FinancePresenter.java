package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class FinancePresenter {

    public void showTransactions(List<Transaction> transactions) {
        showTransactionList(transactions, "Daftar Transaksi:", "- Belum ada transaksi!");
    }

    public void showSearchResults(String query, List<Transaction> transactions) {
        showTransactionList(transactions, "Hasil Pencarian: \"" + query + "\"", "- Transaksi tidak ditemukan!");
    }

    public void showSortedTransactions(List<Transaction> transactions) {
        showTransactionList(transactions, "Daftar Transaksi (Terurut):", "- Belum ada transaksi!");
    }

    private void showTransactionList(List<Transaction> transactions, String header, String emptyMessage) {
        System.out.println(header);
        if (transactions.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Transaction transaction : transactions) {
            showSingleTransaction(transaction);
        }
    }

    public void showSingleTransaction(Transaction t) {
        String typeStr = (t.getType() == TransactionType.PEMASUKAN) ? "Pemasukan" : "Pengeluaran";
        System.out.println(t.getId() + " | " + t.getDescription() + " | Rp " + (long)t.getAmount() + " | " + typeStr);
    }

    public void showBalance(double balance) {
        System.out.println("Saldo: Rp " + (long)balance);
    }

    public void showBalance(long balance) {
        System.out.println("Saldo: Rp " + balance);
    }

    public void showCurrentBalance(double balance) {
        System.out.println("Saldo saat ini: Rp " + (long) balance);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println(error);
    }
}