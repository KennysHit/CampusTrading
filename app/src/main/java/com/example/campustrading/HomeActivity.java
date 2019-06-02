package com.example.campustrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById ( R.id.bottom_navigation );
        final LinearLayout linearLayout_home = (LinearLayout ) findViewById ( R.id.view_home );
        final LinearLayout linearLayout_message = (LinearLayout ) findViewById ( R.id.view_message );
        final LinearLayout linearLayout_person = (LinearLayout ) findViewById ( R.id.view_person );

        bottomNavigationView.setOnNavigationItemReselectedListener ( new BottomNavigationView.OnNavigationItemReselectedListener ( ) {
            @Override
            public void onNavigationItemReselected ( @NonNull MenuItem menuItem ) {
                switch ( menuItem.getItemId () ){
                    case R.id.home:
                        Toast.makeText ( HomeActivity.this,"home is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_person.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.INVISIBLE );
                        linearLayout_home.setVisibility ( View.VISIBLE );
                        break;
                    case R.id.message:
                        Toast.makeText ( HomeActivity.this,"message is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_person.setVisibility ( View.INVISIBLE );
                        linearLayout_home.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.VISIBLE );
                        break;
                    case R.id.peroson:
                        Toast.makeText ( HomeActivity.this,"person is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_home.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.INVISIBLE );
                        linearLayout_person.setVisibility ( View.VISIBLE );
                        break;
                }
            }
        } );
    }
}
