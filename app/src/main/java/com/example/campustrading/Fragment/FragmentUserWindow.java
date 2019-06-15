package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.R;
import com.example.campustrading.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;

public class FragmentUserWindow extends Fragment {
    private View view;
    private User user = BmobUser.getCurrentUser(User.class);
    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_userwindow ,container,false);
        Button button_logout = ( Button )view.findViewById ( R.id.userwindow_logout );
        Button button_additem = ( Button )view.findViewById ( R.id.userwindow_additem );
        Button button_myitems = (Button )view.findViewById ( R.id.userwindow_myitems );

        TextView textView_username = (TextView )view.findViewById ( R.id.userwindow_username );
        TextView textView_school = (TextView )view.findViewById ( R.id.userwindow_userschool );
        TextView textView_phone = (TextView )view.findViewById ( R.id.userwindow_usertelephone );
        TextView textView_transaction = (TextView )view.findViewById ( R.id.userwindow_transaction );

        textView_username.setText ( "用户名："+user.getUsername () );
        textView_school.setText ( "学校名称："+user.getSchoolName () );
        textView_phone.setText ( "电话："+user.getMobilePhoneNumber () );
        textView_transaction.setText ( "交易数量："+user.getTransaction () );

        if ( BmobUser.isLogin () ){
            Toast.makeText ( getActivity (),"已登录！",Toast.LENGTH_SHORT ).show ();
        }else{
            Toast.makeText ( getActivity (),"请重新登录！",Toast.LENGTH_SHORT ).show ();
            FragmentPerson fragmentPerson = new FragmentPerson ();
            getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
        }

        button_additem.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentAdd fragmentAdd = new FragmentAdd ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentAdd ).commit ();
            }
        } );

        button_logout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                user.logOut();   //清除缓存用户对象
                user = BmobUser.getCurrentUser(user.getClass ()); // 现在的currentUser是null了
                FragmentPerson fragmentPerson = new FragmentPerson ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
            }
        } );

        button_myitems.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentMyItem fragmentMyItem = new FragmentMyItem ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentMyItem ).commit ();
            }
        } );

        return view;
    }
}
