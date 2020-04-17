package com.example.gauharproject.retrofit;

import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String description;
    private String favourite;
    private int age;

    public User(String name, String surname, int age, String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
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

    public String getFavourite() {
        return favourite;
    }

    public int getAge() {
        return age;
    }
}
