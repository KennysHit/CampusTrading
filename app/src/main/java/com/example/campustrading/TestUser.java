package com.example.campustrading;

import cn.bmob.v3.BmobObject;

public class TestUser extends BmobObject {
    private int id;
    private String name;

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
