package adapter.repository;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteTransaction(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }

    @Override
    public List<Transaction> searchTransactions(String query) {
        String lowerQuery = query.toLowerCase();
        return transactions.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getSortedTransactions(SortOption sortOption) {
        List<Transaction> sortedList = new ArrayList<>(transactions);
        switch (sortOption) {
            case AMOUNT_ASC:
                sortedList.sort(Comparator.comparingDouble(Transaction::getAmount));
                break;
            case AMOUNT_DESC:
                sortedList.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
                break;
            case INCOME_FIRST:
                sortedList.sort(Comparator.comparing(t -> t.getType() != TransactionType.PEMASUKAN));
                break;
            case EXPENSE_FIRST:
                sortedList.sort(Comparator.comparing(t -> t.getType() != TransactionType.PENGELUARAN));
                break;
        }
        return sortedList;
    }
}