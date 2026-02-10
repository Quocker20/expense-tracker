package com.quoc.expensetracker.model;

import java.util.UUID;

/**
 * Represents a spending category (e.g., Food, Transport).
 */
public class Category {
    private final String id = UUID.randomUUID().toString();
    private String name;

    // --- Constructors ---
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // --- Getters and Setters ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%s, name=%s]", id.substring(0, 8), name);
    }
}
