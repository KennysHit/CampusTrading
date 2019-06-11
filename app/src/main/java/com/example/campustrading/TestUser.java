package com.example.campustrading;

import cn.bmob.v3.BmobObject;

public class TestUser extends BmobObject {

    private String name;

    public TestUser (){
        this.setTableName ( "Test" );
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }
}
