package com.vmware.talentboost.networkofgiving.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class User {
    private int id;
    private String name;

    @NotBlank
    @Length(max = 128)
    private String username;
    private int age;
    private String gender;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    @NotBlank
    private String password;

    public User() {
    }

    public User(int id, String name, @NotBlank @Length(max = 128) String username, int age, String gender, String location) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }


}
