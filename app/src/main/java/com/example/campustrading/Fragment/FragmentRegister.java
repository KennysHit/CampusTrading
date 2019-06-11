package com.example.campustrading.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.R;
import com.example.campustrading.User;
import com.google.android.material.textfield.TextInputEditText;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class FragmentRegister extends Fragment {
    private View view;
    private TextInputEditText textInputEditText_username;
    private TextInputEditText textInputEditText_password;
    private TextInputEditText textInputEditText_schoolname;
    private TextInputEditText textInputEditText_schoolid;
    private TextInputEditText textInputEditText_phonenumber;
    private TextInputEditText textInputEditText_password2;
    User user = new User ();

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view =  inflater.inflate ( R.layout.fragment_register,container,false );
        Button button_register = (Button)view.findViewById ( R.id.register_button_register );
        Button button_back = (Button )view.findViewById ( R.id.register_button_back );

        textInputEditText_username = (TextInputEditText)view.findViewById ( R.id.register_username );
        textInputEditText_password = (TextInputEditText)view.findViewById ( R.id.register_password );
        textInputEditText_schoolname = (TextInputEditText)view.findViewById ( R.id.register_schoolname );
        textInputEditText_schoolid = (TextInputEditText)view.findViewById ( R.id.register_schoolid );
        textInputEditText_phonenumber = (TextInputEditText)view.findViewById ( R.id.register_phonenumber );
        textInputEditText_password2 = (TextInputEditText)view.findViewById ( R.id.register_password2 );




        button_register.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                user.setUserName ( textInputEditText_username.getText ().toString () );
                user.setPassword ( textInputEditText_password.getText ().toString () );
                user.setSchoolName ( textInputEditText_schoolname.getText ().toString () );
                user.setSchoolId ( textInputEditText_schoolid.getText ().toString () );
                user.setPhoneNumber ( textInputEditText_phonenumber.getText ().toString () );
                if ( !user.getUserName ().isEmpty ( ) &&
                        !user.getPassword ().isEmpty ( ) &&
                        !user.getSchoolName ().isEmpty ( ) &&
                        !user.getSchoolId ().isEmpty ( ) &&
                        !user.getPhoneNumber ().isEmpty ( ) &&
                        !textInputEditText_password2.getText ( ).toString ( ).isEmpty ( ) ) {
                    if ( user.getPassword().equals ( textInputEditText_password2.getText ( ).toString ( ) ) ) {
                        user.save ( new SaveListener< String > ( ) {
                            @Override
                            public void done ( String s , BmobException e ) {
                                if(e==null){
                                    Toast.makeText ( getActivity ( ) , "注册成功！" , Toast.LENGTH_LONG ).show ( );
                                    Intent intent = new Intent ( getContext (),FragmentPerson.class );
                                    intent.putExtra ( "userInfo",user.getUserName ()+"@@@"+user.getPassword () );
                                    startActivity ( intent );
//                                    FragmentPerson fragmentPerson = new FragmentPerson ();
//                                    getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
                                }else{
                                    Toast.makeText ( getActivity ( ) , "注册失败，错误："+e.getMessage () , Toast.LENGTH_LONG ).show ( );
                                    Log.e ( "error:",e.getMessage () );
                                }
                            }
                        } );
                    }else{
                        Toast.makeText ( getActivity ( ) , "请确认两次密码输入相同！" , Toast.LENGTH_LONG ).show ( );
                        textInputEditText_password.setText ( "" );
                        textInputEditText_password2.setText ( "" );
                    }
                }else {
                    Toast.makeText ( getActivity ( ) , "请将注册信息填写完整！" , Toast.LENGTH_LONG ).show ( );
                }
            }
        } );
        button_back.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentPerson fragmentPerson = new FragmentPerson ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
            }
        } );
        return view;
    }
}
