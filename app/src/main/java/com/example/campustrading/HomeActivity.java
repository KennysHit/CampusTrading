package com.example.campustrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.campustrading.Fragment.FragmentHome;
import com.example.campustrading.Fragment.FragmentMessage;
import com.example.campustrading.Fragment.FragmentPerson;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.bmob.v3.Bmob;


public class HomeActivity extends AppCompatActivity {

    public static User user = null;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.layout_main );

        Bmob.initialize ( this,"22727d4417ac45765e87663e667d87a4" );


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById ( R.id.bottom_navigation );

        bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected ( @NonNull MenuItem menuItem ) {
                switch ( menuItem.getItemId () ){
                    case R.id.home:
                        Toast.makeText ( HomeActivity.this,"home is clicked!",Toast.LENGTH_LONG ).show ();
                        FragmentHome fragmentHome = new FragmentHome ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentHome ).commit ();
                        break;
                    case R.id.message:
                        Toast.makeText ( HomeActivity.this,"message is clicked!",Toast.LENGTH_LONG ).show ();
                        FragmentMessage fragmentMessage = new FragmentMessage ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentMessage ).commit ();
                        break;
                    case R.id.peroson:
                        Toast.makeText ( HomeActivity.this,"person is clicked!",Toast.LENGTH_LONG ).show ();
                        FragmentPerson fragmentPerson = new FragmentPerson ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
                        break;
                }
                return true;
            }
        } );
    }

}
