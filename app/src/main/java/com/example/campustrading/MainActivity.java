package com.example.campustrading;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate ( @Nullable Bundle savedInstanceState , @Nullable PersistableBundle persistentState ) {
        super.onCreate ( savedInstanceState , persistentState );
        setContentView ( R.layout.layout_main );
    }
}
