package com.quoc.expensetracker.repository;

import com.quoc.expensetracker.model.Category;
import com.quoc.expensetracker.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory data storage implementing Singleton pattern.
 * Acts as a temporary database during the initial phase.
 */
public class MemoryRepository {

    private static MemoryRepository instance;
    private final List<Category> categories = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    private MemoryRepository() {
        // Private constructor to prevent external instantiation
    }

    /**
     * Provides a global access point to the repository instance.
     */
    public static synchronized MemoryRepository getInstance() {
        if (instance == null) {
            instance = new MemoryRepository();
        }
        return instance;
    }

    // --- Category operation ---
    public void addCategory(Category category) {
        boolean exists = categories.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(category.getName()));

        if (exists) {
            throw new IllegalArgumentException("Category name already exists: " + category.getName());
        }

        categories.add(category);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    // --- Transaction operations ---
    public void addTransaction(Transaction transaction) {
        boolean exists = categories.stream()
                .anyMatch(c -> c.getId().equals(transaction.getCategoryId()));

        if (!exists) {
            throw new IllegalArgumentException("Invalid Category ID: " + transaction.getCategoryId());
        }
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }


}
