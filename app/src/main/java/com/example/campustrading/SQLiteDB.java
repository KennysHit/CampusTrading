package com.example.campustrading;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class SQLiteDB {
    public static String TEST_TABLE="create table Test_table( id integer primary key autoincrement,name text)";
    private SQLiteDatabase db;

    public SQLiteDB (String dir){
        this.db = SQLiteDatabase.openOrCreateDatabase ( "dir",null );
    }

    private void createTable(String table){
        //执行SQL语句
        db.execSQL(table);
    }

    /**
     * 插入单个数据
     * @param db 数据库名
     * @param name 待插入用户名
     */
    public void insert(SQLiteDatabase db,String name){
        Cursor cursor = db.query ( "Test_table",null,null,null,null,null,null );
        //插入数据SQL语句
        ContentValues contentValues = new ContentValues (  );
        cursor.moveToFirst ();
        String sql="insert into stu_table(id,name) values('"+String.valueOf ( cursor.getLong ( 0 ) )+"','"+name+"')";
        //执行SQL语句
        db.execSQL(sql);
    }
    public void deleteById(int id) {
        //删除SQL语句
        String sql = "delete from Test_table where id = "+String.valueOf ( id );
        //执行SQL语句
        db.execSQL(sql);
    }
    public
}
