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

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.layout_main );

        Bmob.initialize ( this,"f1b72261dade04a8cf80f8addd9abeb2" );


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById ( R.id.bottom_navigation );

        bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected ( @NonNull MenuItem menuItem ) {
                switch ( menuItem.getItemId () ){
                    case R.id.home:
                        FragmentHome fragmentHome = new FragmentHome ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentHome ).commit ();
                        break;
                    case R.id.message:
                        FragmentMessage fragmentMessage = new FragmentMessage ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentMessage ).commit ();
                        break;
                    case R.id.peroson:
                        FragmentPerson fragmentPerson = new FragmentPerson ();
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
                        break;
                }
                return true;
            }
        } );
    }

}
