package com.example.demo.enums;

public enum UserRole {
    CUSTOMER(0),
    EMPLOYEE(1),
    MANAGER(2),
    ADMIN(3);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
