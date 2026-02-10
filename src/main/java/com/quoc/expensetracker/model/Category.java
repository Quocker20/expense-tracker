package com.quoc.expensetracker.model;

import java.util.UUID;

/**
 * Represents a spending category (e.g., Food, Transport).
 */
public class Category {
    private String id;
    private String name;

    // --- Constructors ---
    public Category() {
    }

    public Category(String name) {
        this.id = UUID.randomUUID().toString();
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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", id, name);
    }
}
