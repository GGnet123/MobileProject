package com.example.gauharproject.retrofit;

import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private ArrayList<String> favourite;
    private int age;

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<String> getFavourite() {
        return favourite;
    }

    public int getAge() {
        return age;
    }
}
