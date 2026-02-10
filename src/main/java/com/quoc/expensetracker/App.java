package com.quoc.expensetracker;

/**
 * Entry point for the Expense Tracker application (CLI version).
 */
public class App {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   EXPENSE TRACKER - EVOLUTION EDITION    ");
        System.out.println("   Dev: Quoc");
        System.out.println("   Environment: Java " + System.getProperty("java.version"));
        System.out.println("==========================================");

        // runtime.maxMemory() returns bytes, converting to MB for readability
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Max Memory (Heap): " + (maxMemory / 1024 / 1024) + " MB");

        System.out.println(">> System initialized successfully.");
    }
}