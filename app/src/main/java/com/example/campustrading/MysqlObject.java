package com.example.campustrading;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlObject {
    private String driver = "com.mysql.jdbc.Driver";//Mysql驱动
    private String myurl = "jdbc:mysql://100.70.73.46/test_database";//Mysql数据库连接Url
    private String myuser = "kennys";//Mysql用户名
    private String mypassword = "dsy199866";//Mysql密码
    private Connection connection = null;

    public static MysqlObject mysqlObject = null;//MysqlObjec单例对象



    /**
     * 单例模式，构造方法私有化
     */
    private MysqlObject (){

    }
    /**
     * 链接数据库
     * @return 数据库对象
     */
    public static Connection mysqlConnect(){
        try {
            if(mysqlObject == null) {
                mysqlObject = new MysqlObject ();
                Class.forName ( mysqlObject.driver );//获取驱动
                mysqlObject.connection = ( Connection ) DriverManager.getConnection (
                        mysqlObject.myurl ,
                        mysqlObject.myuser ,
                        mysqlObject.mypassword
                );//获取链接
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace ( );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return mysqlObject.connection;
    }

    /**
     * 关闭数据库
     */
    public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
