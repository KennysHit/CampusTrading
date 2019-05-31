package com.example.campustrading;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.campustrading.MysqlObject.mysqlObject;

public class MysqlOperation {
    private Connection connection = mysqlObject.mysqlConnect ();//打开数据库对象
    private PreparedStatement preparedStatement = null;//操作整合sql语句的对象
    private ResultSet resultSet = null;//查询结果的集合

    public static MysqlOperation mysqlOperation = null;//MysqlObjec单例对象

    /**
     * 单例模式，构造方法私有化
     */
    private MysqlOperation(){

    }
    /**
     * 获取Mysql单例对象
     */
    public static MysqlOperation getMysqlOperation (){
        if ( mysqlOperation == null ){
            mysqlOperation = new MysqlOperation ();
        }
        return mysqlOperation;
    }

    /**
     * 查询Mysql数据库，将查询到的内容放入List容器中
     * @return List查询集合
     */
    public List<User> getMysqlData(){
        //结果存放集合
        List<User> list=new ArrayList<User> ();
        //MySQL 语句
        String sql=" select * from test_table";
        try {
            if(mysqlOperation.connection != null && (!mysqlOperation.connection.isClosed())){
                mysqlOperation.preparedStatement = (PreparedStatement) mysqlOperation.connection.prepareStatement(sql);
                if(mysqlOperation.connection != null){
                    mysqlOperation.resultSet = mysqlOperation.preparedStatement.executeQuery(sql);
                    if(mysqlOperation.resultSet != null){
                        while(mysqlOperation.resultSet.next()){
                            User u = new User();
                            u.setId(mysqlOperation.resultSet.getString("id"));
                            u.setName(mysqlOperation.resultSet.getString("name"));
                            Log.d ( "==========","=================" );
                            Log.d ( "==========",u.getId () );
                            Log.d ( "==========",u.getName () );
                            list.add(u);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysqlObject.closeAll(mysqlOperation.connection,mysqlOperation.preparedStatement,mysqlOperation.resultSet);//关闭相关操作
        return list;
    }

    public void insertMysqlData(User user){
        if(user != null){

            //MySQL 语句
            String sql=" insert into test_table(id,name) values( ?,? ) ";
            try {
                boolean closed = mysqlOperation.connection.isClosed();
                if((mysqlOperation.connection != null) && (!closed)){
                    mysqlOperation.preparedStatement = (PreparedStatement) mysqlOperation.connection.prepareStatement(sql);
                    String name = user.getName ();
                    String id = user.getId ();
                    mysqlOperation.preparedStatement.setString(1, id );//第一个参数 id 规则同上
                    mysqlOperation.preparedStatement.setString(2, name);//第二个参数 name 规则同上
                }
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
        }
        mysqlObject.closeAll(mysqlOperation.connection,mysqlOperation.preparedStatement,null);//关闭相关操作
    }
}
