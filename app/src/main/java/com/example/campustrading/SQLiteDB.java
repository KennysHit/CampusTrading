package com.example.campustrading;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class SQLiteDB {
    public static String TEST_TABLE= "Test_table";
    private SQLiteDatabase db;

    public SQLiteDB (String dir){
        this.db = SQLiteDatabase.openOrCreateDatabase ( "dir",null );
    }

    private void createTable(String table){
        //执行SQL语句
        db.execSQL("create table "+table+"( id integer primary key autoincrement,name text)");
    }

    /**
     * 插入单个数据
     * @param db 数据库名
     * @param name 待插入用户名
     */
    public void insert(SQLiteDatabase db,String name){
        Cursor cursor = db.query ( TEST_TABLE,null,null,null,null,null,null );
        //插入数据SQL语句
        String sql="insert into stu_table(id,name) values('"+String.valueOf ( cursor.getCount ()+1)+"','"+name+"')";
        //执行SQL语句
        db.execSQL(sql);
    }
    public void deleteById(int id) {
        //删除SQL语句
        String sql = "delete from " + TEST_TABLE + " where id = " + String.valueOf ( id );
        //执行SQL语句
        db.execSQL(sql);
    }
    private User queryById(int id) {
        User user = null;
        //查询获得游标
        Cursor cursor = db.query (TEST_TABLE,null,null,null,null,null,null);
            //判断游标是否为空
        if(cursor.moveToFirst()){
            //遍历游标
            for(int i=0;i<cursor.getCount();i++){
                cursor.move(i);
                //获得ID
                user.setId ( cursor.getInt(0) );
                //获得用户名
                user.setName ( cursor.getString(1) );
                System.out.println(user.getId ()+":"+user.getName ());
            }
        }
        return user;
    }
}
