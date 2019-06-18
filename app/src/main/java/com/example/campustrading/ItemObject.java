package com.example.campustrading;

import cn.bmob.v3.BmobObject;

public class ItemObject extends BmobObject {
    private String itemname;
    private String iteminfo;
    private Float itemprice;
    private String hostname;
    private String hosttel;
    private String itemplace;
    private String currentbuyer;

    public String getCurrentbuyer () {
        return currentbuyer;
    }

    public String getItemplace () {
        return itemplace;
    }

    public ItemObject(){
        this.setTableName ( "item" );
    }


    public String getItemname () {
        return itemname;
    }

    public String getIteminfo () {
        return iteminfo;
    }

    public Float getItemprice () {
        return itemprice;
    }

    public String getHostname () {
        return hostname;
    }

    public String getHosttel () {
        return hosttel;
    }

    public void setItemname ( String itemname ) {
        this.itemname = itemname;
    }

    public void setIteminfo ( String iteminfo ) {
        this.iteminfo = iteminfo;
    }

    public void setItemprice ( Float itemprice ) {
        this.itemprice = itemprice;
    }

    public void setHostname ( String hostname ) {
        this.hostname = hostname;
    }

    public void setHosttel ( String hosttel ) {
        this.hosttel = hosttel;
    }

    public void setItemplace ( String itemplace ) {
        this.itemplace = itemplace;
    }

    public void setCurrentbuyer ( String currentbuyer ) {
        this.currentbuyer = currentbuyer;
    }
}

