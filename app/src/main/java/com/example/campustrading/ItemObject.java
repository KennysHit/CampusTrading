package com.example.campustrading;

public class ItemObject {
    private String name;
    private String imgUrl;
    private String money;

    public ItemObject ( String name , String imgUrl , String money ) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.money = money;
    }

    public String getName () {
        return name;
    }

    public String getImgUrl () {
        return imgUrl;
    }

    public String getMoney () {
        return money;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public void setImgUrl ( String imgUrl ) {
        this.imgUrl = imgUrl;
    }

    public void setMoney ( String money ) {
        this.money = money;
    }
}
