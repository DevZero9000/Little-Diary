package com.lab.littlediary;

public class EntryHelper {
    String title;
    private int date;
    private int month;
    private int year;

    public EntryHelper(String title, int date, int month, int year) {
        this.title = title;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }
    public int getDate() {
        return date;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
}
