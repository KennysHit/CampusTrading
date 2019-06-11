package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.R;

public class FragmentPerson extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        this.view =  inflater.inflate ( R.layout.fragment_person,container,false );

        Button button_register = (Button)view.findViewById ( R.id.person_button_register );
        Button button_loading = (Button)view.findViewById ( R.id.person_button_loading );


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
