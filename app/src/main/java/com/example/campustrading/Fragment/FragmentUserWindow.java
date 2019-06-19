package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import cn.bmob.v3.BmobUser;

public class FragmentUserWindow extends Fragment {
    private View view;
    private User user = BmobUser.getCurrentUser(User.class);
    private TextView textView_schoolid;
    private TextView textView_username;
    private TextView textView_school;
    private TextView textView_phone;
    private TextView textView_transaction;
    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_userwindow ,container,false);
        setHasOptionsMenu ( true );

        Button button_additem = ( Button )view.findViewById ( R.id.userwindow_additem );
        Button button_myitems = (Button )view.findViewById ( R.id.userwindow_myitems );

        textView_username = (TextView )view.findViewById ( R.id.userwindow_username );
        textView_school = (TextView )view.findViewById ( R.id.userwindow_userschool );
        textView_phone = (TextView )view.findViewById ( R.id.userwindow_usertelephone );
        textView_transaction = (TextView )view.findViewById ( R.id.userwindow_transaction );
        textView_schoolid = (TextView )view.findViewById ( R.id.userwindow_schoolid );


        textView_username.setText ( user.getUsername () );
        textView_school.setText ( "学校名称："+user.getSchoolName () );
        textView_phone.setText ( "电话："+user.getMobilePhoneNumber () );
        textView_transaction.setText ( "交易数量："+user.getTransaction () );
        textView_schoolid.setText ( "学号："+user.getSchoolId () );

        button_additem.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentAdd fragmentAdd = new FragmentAdd ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentAdd ).commit ();
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
    @Override
    public void onCreateOptionsMenu ( @NonNull Menu menu , @NonNull MenuInflater inflater ) {
        inflater.inflate ( R.menu.lougout,menu );
        super.onCreateOptionsMenu ( menu , inflater );
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.logout:
                user.logOut();   //清除缓存用户对象
                user = BmobUser.getCurrentUser(user.getClass ()); // 现在的currentUser是null了
                Toast.makeText ( getActivity (),"退出成功！",Toast.LENGTH_SHORT ).show ();
                FragmentPerson fragmentPerson = new FragmentPerson ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentPerson ).commit ();
                return false;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }
}
