package com.example.campustrading;


import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String schoolName;
    private String schoolId;



    public String getSchoolName (){
        return schoolName;
    }

    public String getSchoolId () {
        return schoolId;
    }



    public void setSchoolName (String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolId ( String schoolId ) {
        this.schoolId = schoolId;
    }


}
