package com.quoc.expensetracker;

import com.quoc.expensetracker.model.Category;
import com.quoc.expensetracker.model.Transaction;
import com.quoc.expensetracker.repository.MemoryRepository;
import com.quoc.expensetracker.service.ExpenseService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Entry point for the Expense Tracker application.
 */
public class App {

    public static void main(String[] args) {
        // 1. Setup Data (Seed data tiếng Việt)
        seedData();

        // 2. Initialize Service
        ExpenseService service = new ExpenseService();

        System.out.println("==========================================");
        System.out.println("   QUẢN LÝ CHI TIÊU - BÁO CÁO TỔNG HỢP    ");
        System.out.println("==========================================");

        // 3. Test: Calculate Grand Total
        BigDecimal total = service.calculateTotalExpense();
        System.out.println(">> TỔNG CHI TIÊU: " + formatCurrency(total));

        // 4. Test: Report by Category (UC-06)
        System.out.println("\n>> BÁO CÁO THEO DANH MỤC:");
        Map<String, BigDecimal> report = service.calculateTotalByCategory();

        report.forEach((categoryName, amount) -> {
            System.out.printf("   - %-20s : %s%n", categoryName, formatCurrency(amount));
        });
    }

    // Helper method to seed initial data for testing
    private static void seedData() {
        MemoryRepository repo = MemoryRepository.getInstance();

        // Tạo danh mục tiếng Việt
        Category food = new Category("Ăn uống");
        Category transport = new Category("Di chuyển");
        Category bills = new Category("Hóa đơn");
        Category entertainment = new Category("Giải trí");

        repo.addCategory(food);
        repo.addCategory(transport);
        repo.addCategory(bills);
        repo.addCategory(entertainment);

        // Tạo giao dịch mẫu (Data tiếng Việt)
        repo.addTransaction(new Transaction(new BigDecimal("55000"), food.getId(), "Phở Bò tái lăn", null));
        repo.addTransaction(new Transaction(new BigDecimal("35000"), food.getId(), "Cà phê sáng", null));
        repo.addTransaction(new Transaction(new BigDecimal("100000"), transport.getId(), "Đổ xăng xe máy", null));
        repo.addTransaction(new Transaction(new BigDecimal("1500000"), bills.getId(), "Tiền điện tháng 6", null));
        repo.addTransaction(new Transaction(new BigDecimal("200000"), entertainment.getId(), "Xem phim", null));
    }

    // Helper for formatting currency to VND
    private static String formatCurrency(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(amount);
    }
}