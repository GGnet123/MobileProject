package com.example.gauharproject.retrofit;

import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private ArrayList favourite;
    private int age;

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

    public ArrayList getFavourite() {
        return favourite;
    }

    public int getAge() {
        return age;
    }
}
