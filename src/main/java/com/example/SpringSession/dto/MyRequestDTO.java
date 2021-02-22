package com.example.SpringSession.dto;

public class MyRequestDTO {
    private String firstName;
    private String lastName;
    private int age;

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
