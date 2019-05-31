package com.example.campustrading;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static com.example.campustrading.MysqlOperation.mysqlOperation;

public class MainActivity extends AppCompatActivity {
    List<User> data = null;
    @Override
    public void onCreate ( @Nullable Bundle savedInstanceState , @Nullable PersistableBundle persistentState ) {
        super.onCreate ( savedInstanceState , persistentState );
        setContentView ( R.layout.layout_main );
        User user1 = new User ( "01","ZhangSan" );
        User user2 = new User ( "02","LiSi" );
        User user3 = new User ( "03","WangWu" );

//        mysqlOperation.insertMysqlData ( user1 );
//        mysqlOperation.insertMysqlData ( user2 );
//        mysqlOperation.insertMysqlData ( user3 );

        data = mysqlOperation.getMysqlOperation ().getMysqlData ();


        Log.d("===================","===========================================");
        Log.d("===================",data.get ( 0 ).getId ()+data.get ( 0 ).getName ());
    }
}
