package com.example.campustrading;


import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
    private String schoolName;
    private String schoolId;
    private String userName;
    private String phoneNumber;
    private String password;

    public User () {
        this.setTableName ( "User" );
    }

    public String getPassword () {
        return password;
    }

    public String getSchoolName (){
        return schoolName;
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


    public void setSchoolName (String schoolName) {
        this.schoolName = schoolName;
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
