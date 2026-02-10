package com.quoc.expensetracker.service;


import com.quoc.expensetracker.model.Category;
import com.quoc.expensetracker.model.Transaction;
import com.quoc.expensetracker.repository.MemoryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handle business for expense tracking
 * Acts as a bridge between the controller/view and the repository
 */
public class ExpenseService {

    private final MemoryRepository repository;

    //Constructor Injection (Simulated)
    public ExpenseService() {
        this.repository = MemoryRepository.getInstance();
    }

    /**
     * Calculates the grand total of all transactions
     * @return Total amount as BigDecimal
     */
    public BigDecimal calculateTotalExpense() {
        return repository.getAllTransactions().stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    /**
     * Groups transactions by Category Name and calculates total amount for each.
     *
     * @return A Map where Key is Category Name and Value is Total Amount.
     */
    public Map<String, BigDecimal> calculateTotalByCategory() {
        //Create a map CategoryId -> CategoryName for quick lookup
        Map<String, String> categoryNameMap = repository.getAllCategories().stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));

        //Stream Transactions, map Id -> name, group and sum
        return repository.getAllTransactions().stream()
                .collect(Collectors.groupingBy(
                        t -> categoryNameMap.getOrDefault(t.getCategoryId(),"Unknown"),
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
                ));
    }


    /**
     * Filters transactions within a specific date range.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Transaction> filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
        return repository.getAllTransactions().stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

}
