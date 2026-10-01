package domain.repository;

import domain.entity.Transaction;
import domain.entity.SortOption;
import java.util.List;

public interface ITransactionRepository {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(int id);
    boolean deleteTransaction(int id);
    List<Transaction> searchTransactions(String query);
    List<Transaction> getSortedTransactions(SortOption sortOption);
}