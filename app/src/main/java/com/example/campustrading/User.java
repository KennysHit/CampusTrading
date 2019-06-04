package com.example.campustrading;

public class User {
    private int id;
    private String name;

    public User(){

    }
<<<<<<< HEAD
    public User ( int id , String name ) {
=======
    public User(String id , String name ) {
>>>>>>> 2e8baa9afeeec2fc39b8660db738b93fc969cb36
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public void setName ( String name ) {
        this.name = name;
    }
}
