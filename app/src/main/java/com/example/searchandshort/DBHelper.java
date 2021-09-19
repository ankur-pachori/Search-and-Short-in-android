package com.example.searchandshort;

public class DBHelper {
    private int number;
    private String name;

    public DBHelper(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
