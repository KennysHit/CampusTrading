package com.example.campustrading;


import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
    private String userSchool;
    private String schoolId;
    private String userName;
    private String phoneNumber;
    private String password;

    public User ( String userSchool , String schoolId , String userName , String phoneNumber , String password ) {
        this.userSchool = userSchool;
        this.schoolId = schoolId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPassword () {
        return password;
    }

    public String getUserSchool () {
        return userSchool;
    }

    public String getSchoolId () {
        return schoolId;
    }

    public String getUserName () {
        return userName;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }


    public void setUserSchool ( String userSchool ) {
        this.userSchool = userSchool;
    }

    public void setSchoolId ( String schoolId ) {
        this.schoolId = schoolId;
    }

    public void setUserName ( String userName ) {
        this.userName = userName;
    }

    public void setPhoneNumber ( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }
}
