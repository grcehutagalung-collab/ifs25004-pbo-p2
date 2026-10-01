package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

import java.util.List;

public class FinanceUseCase {
    private final ITransactionRepository repository;
    private int nextId = 1;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(String description, double amount, TransactionType type) {
        Transaction transaction = new Transaction(nextId++, description, amount, type);
        repository.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return repository.getAllTransactions();
    }

    public boolean deleteTransaction(int id) {
        return repository.deleteTransaction(id);
    }

    public List<Transaction> searchTransactions(String query) {
        return repository.searchTransactions(query);
    }

    public List<Transaction> getSortedTransactions(SortOption sortOption) {
        return repository.getSortedTransactions(sortOption);
    }

    public double getTotalIncome() {
        return repository.getAllTransactions().stream()
                .filter(t -> t.getType() == TransactionType.PEMASUKAN)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return repository.getAllTransactions().stream()
                .filter(t -> t.getType() == TransactionType.PENGELUARAN)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}