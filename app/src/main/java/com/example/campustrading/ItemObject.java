package com.example.campustrading;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.http.I;

public class ItemObject extends BmobObject {
    private String itemname;
    private String iteminfo;
    private Float itemprice;
    private String itemhost;

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

    public String getItemhost () {
        return itemhost;
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

    public void setItemhost ( String itemhost ) {
        this.itemhost = itemhost;
    }
}
