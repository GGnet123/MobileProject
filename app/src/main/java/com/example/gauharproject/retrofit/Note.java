package com.example.gauharproject.retrofit;

public class Note {
    private int id;
    private String note;
    private int done;

    public Note(int id, String note, int done) {
        this.id = id;
        this.note = note;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public int isDone() {
        return done;
    }
}
