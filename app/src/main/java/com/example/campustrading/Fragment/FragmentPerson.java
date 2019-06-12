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
import com.example.campustrading.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class FragmentPerson extends Fragment {
    private View view;
    private TextInputEditText textInputEditText_username;
    private TextInputEditText textInputEditText_userpassword;
    public User user = new User ();

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        this.view =  inflater.inflate ( R.layout.fragment_person,container,false );

        textInputEditText_username = (TextInputEditText )view.findViewById ( R.id.loading_username );
        textInputEditText_userpassword = (TextInputEditText )view.findViewById ( R.id.loading_password );

        Button button_register = (Button)view.findViewById ( R.id.person_button_register );
        Button button_loading = (Button)view.findViewById ( R.id.person_button_loading );

        user = BmobUser.getCurrentUser ( user.getClass () );
        if(user != null){
            // 允许用户使用应用
            FragmentUserWindow fragmentUserWindow = new FragmentUserWindow ();
            getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentUserWindow ).commit ();
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }

        button_loading.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( !textInputEditText_username.getText ( ).toString ( ).isEmpty () && !textInputEditText_userpassword.getText ( ).toString ( ).isEmpty () ) {
                    user = new User ();
                    user.setUsername ( textInputEditText_username.getText ( ).toString ( ).trim ( ) );
                    user.setPassword ( textInputEditText_userpassword.getText ( ).toString ( ).trim ( ) );
                    user.login ( new SaveListener< User > ( ) {

                        @Override
                        public void done ( User user , BmobException e ) {
                            if ( e == null ) {
                                Toast.makeText ( getActivity ( ) , "登录成功!" , Toast.LENGTH_LONG ).show ( );
                                FragmentUserWindow fragmentUserWindow = new FragmentUserWindow ();
                                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentUserWindow ).commit ();
                                //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                                //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                            } else {
                                Toast.makeText ( getActivity ( ) , e.getMessage ( ) , Toast.LENGTH_LONG ).show ( );
                            }
                        }
                    } );
                }else {
                    Toast.makeText ( getActivity ( ) ,"用户名或密码为空！" , Toast.LENGTH_LONG ).show ( );
                }
            }
        } );


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
