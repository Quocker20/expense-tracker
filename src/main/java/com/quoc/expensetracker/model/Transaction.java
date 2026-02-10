package com.quoc.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a financial transaction (Expense or Income).
 * Uses BigDecimal for monetary precision.
 */
public class Transaction {
    private final String id = UUID.randomUUID().toString();
    private BigDecimal amount;
    private String categoryId;
    private String note;
    private LocalDate date;
    private final LocalDateTime createdAt;


    // --- Constructors ---
    public Transaction() {
        this.createdAt = LocalDateTime.now();
    }

    public Transaction(BigDecimal amount, String categoryId, String note, LocalDate date) {
        //Validation: Fail fast
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Category is Required");
        }

        this.amount = amount;
        this.categoryId = categoryId;
        this.note = note;
        this.createdAt = LocalDateTime.now();

        //Default to today if date is missing
        this.date = (date != null) ? date : LocalDate.now();
    }

    // --- Getters and Setters


    public String getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {return createdAt;}

    @Override
    public String toString() {
        return String.format("Transaction[id=%s, amount=%s, date=%s, created=%s]", id.substring(0, 8), // Chỉ lấy 8 ký tự đầu của UUID cho gọn
                amount, date, createdAt);
    }
}
