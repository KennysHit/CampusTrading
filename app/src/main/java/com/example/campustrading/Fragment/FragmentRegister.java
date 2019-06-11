package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.R;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.campustrading.HomeActivity.user;

public class FragmentRegister extends Fragment {
    private View view;
    private TextInputEditText textInputEditText_username;
    private TextInputEditText textInputEditText_password;
    private TextInputEditText textInputEditText_schoolname;
    private TextInputEditText textInputEditText_schoolid;
    private TextInputEditText textInputEditText_phonenumber;
    private TextInputEditText textInputEditText_password2;
    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view =  inflater.inflate ( R.layout.fragment_register,container,false );
        Button button = (Button)view.findViewById ( R.id.register_button_register );

        textInputEditText_username = (TextInputEditText)view.findViewById ( R.id.register_username );
        textInputEditText_password = (TextInputEditText)view.findViewById ( R.id.register_password );
        textInputEditText_schoolname = (TextInputEditText)view.findViewById ( R.id.register_schoolname );
        textInputEditText_schoolid = (TextInputEditText)view.findViewById ( R.id.register_schoolid );
        textInputEditText_phonenumber = (TextInputEditText)view.findViewById ( R.id.register_phonenumber );
        textInputEditText_password2 = (TextInputEditText)view.findViewById ( R.id.register_phonenumber );

        return view;
    }
}
