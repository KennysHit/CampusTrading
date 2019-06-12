package com.example.campustrading;

public class ItemObject {
    private String name;
    private String img;
    private String money;
    private String info;
    public ItemObject ( String name , String img , String money ) {
        this.name = name;
        this.img = img;
        this.money = money;
    }

    public String getInfo () {
        return info;
    }

    public String getName () {
        return name;
    }

    public String getImg () {
        return img;
    }

    public String getMoney () {
        return money;
    }

    public void setInfo ( String info ) {
        this.info = info;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public void setImg ( String img ) {
        this.img = img;
    }

    public void setMoney ( String money ) {
        this.money = money;
    }
}
