package com.example.campustrading.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.R;
import com.google.android.material.textfield.TextInputEditText;

public class FragmentPerson extends Fragment {
    private View view;
    private TextInputEditText textInputEditText_username;
    private TextInputEditText textInputEditText_userpassword;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        this.view =  inflater.inflate ( R.layout.fragment_person,container,false );

        textInputEditText_username = (TextInputEditText )view.findViewById ( R.id.loading_username );
        textInputEditText_userpassword = (TextInputEditText )view.findViewById ( R.id.loading_password );

        Button button_register = (Button)view.findViewById ( R.id.person_button_register );
        Button button_loading = (Button)view.findViewById ( R.id.person_button_loading );

        Intent intent = getActivity ().getIntent ();
        String message = intent.getStringExtra ( "userInfo" );
        if(message!=null){
            String[] userInfo  = message.split ( "@@@" );
                textInputEditText_username.setText ( userInfo[0] );
                textInputEditText_userpassword.setText ( userInfo[1] );
        }


        button_register.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentRegister fragmentRegister = new FragmentRegister ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentRegister ).commit ();
            }
        } );
        return view;
    }
}
