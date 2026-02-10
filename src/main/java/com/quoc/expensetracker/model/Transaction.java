package com.quoc.expensetracker.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

/**
 * Represents a financial transaction (Expense or Income).
 * Uses BigDecimal for monetary precision.
 */
public class Transaction {
    private String id;
    private BigDecimal amount;
    private String categoryId;
    private String note;
    private LocalDate date;

    // --- Constructors ---
    public Transaction() {
    }

    public Transaction(BigDecimal ammount, String categoryId, String note, LocalDate date) {
        //Validation: Fail fast
        if (ammount == null || ammount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Category is Required");
        }

        this.id = UUID.randomUUID().toString();
        this.amount = ammount;
        this.categoryId = categoryId;
        this.note = note;

        //Default to today if date is missing
        this.date = (date != null) ? date : LocalDate.now();
    }

    // --- Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        //format currency for Viet Nam (e.g. 100,000 ₫)
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        return String.format("[%s] %s | Cat: %s | %s VND",
                date, note, categoryId, formatter.format(amount));
    }
}
