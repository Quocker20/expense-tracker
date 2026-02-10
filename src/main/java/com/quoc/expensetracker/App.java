package com.quoc.expensetracker;

import com.quoc.expensetracker.model.Category;
import com.quoc.expensetracker.model.Transaction;
import com.quoc.expensetracker.repository.MemoryRepository;

/**
 * Entry point for the Expense Tracker application (CLI version).
 */
public class App {

    public static void main(String[] args) {
        MemoryRepository repo = MemoryRepository.getInstance();

        try {
            // 1. Test Category
            Category food = new Category("Ăn uống");
            repo.addCategory(food);
            repo.addCategory(new Category("Di chuyển"));

            // Thử thêm trùng tên (Sẽ ném lỗi)
//             repo.addCategory(new Category("ăn uống"));

            // 2. Test Transaction
            Transaction t1 = new Transaction(new java.math.BigDecimal("50000"), food.getId(), "Ăn trưa", null);
            repo.addTransaction(t1);

            // 3. Print Results
            System.out.println("--- Categories ---");
            repo.getAllCategories().forEach(System.out::println);

            System.out.println("\n--- Transactions ---");
            repo.getAllTransactions().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}