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

import com.example.campustrading.ItemObject;
import com.example.campustrading.R;
import com.example.campustrading.User;
import com.google.android.material.textfield.TextInputEditText;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class FragmentAdd extends Fragment {
    private View view;
    private ItemObject itemObject = new ItemObject (  );
    private User user = BmobUser.getCurrentUser(User.class);
    private TextInputEditText textInputEditText_itemname;
    private TextInputEditText textInputEditText_iteminfo;
    private TextInputEditText textInputEditText_itemprice;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_add,container,false);

        Button button_add = (Button)view.findViewById ( R.id.add_button_add );
        Button button_back = (Button )view.findViewById ( R.id.add_button_back);
        textInputEditText_itemname = (TextInputEditText )view.findViewById ( R.id.add_itemname );
        textInputEditText_iteminfo = (TextInputEditText )view.findViewById ( R.id.add_iteminfo );
        textInputEditText_itemprice = (TextInputEditText )view.findViewById ( R.id.add_itemprice );

        button_add.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if(!textInputEditText_itemname.getText ().toString ().isEmpty () &&
                    !textInputEditText_iteminfo.getText ().toString ().isEmpty () &&
                        !textInputEditText_itemprice.getText ().toString ().isEmpty () ){

                    itemObject.setHosttel ( user.getMobilePhoneNumber () );
                    itemObject.setItemname ( textInputEditText_itemname.getText ().toString ().trim () );
                    itemObject.setIteminfo ( textInputEditText_iteminfo.getText ().toString ().trim () );
                    itemObject.setItemprice ( Float.valueOf ( textInputEditText_itemprice.getText ().toString ().trim () ) );
                    itemObject.setHostname ( user.getUsername () );
                    itemObject.setItemplace ( user.getSchoolName () );
                    itemObject.setCurrentbuyer ( " " );

                    itemObject.save ( new SaveListener< String > ( ) {
                        @Override
                        public void done ( String s , BmobException e ) {
                            if(e==null){
                                Toast.makeText ( getActivity (),"添加商品成功！", Toast.LENGTH_SHORT ).show ();
                                FragmentUserWindow fragmentUserWindow = new FragmentUserWindow ();
                                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main, fragmentUserWindow ).commit ();
                            }else {
                                Toast.makeText ( getActivity (),"添加商品失败！", Toast.LENGTH_SHORT ).show ();
                            }
                        }
                    } );

                }else {
                    Toast.makeText ( getActivity (),"请将商品信息填写完整！",Toast.LENGTH_SHORT ).show ();
                }

            }
        } );

        button_back.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentUserWindow fragmentUserWindow = new FragmentUserWindow ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentUserWindow ).commit ();
            }
        } );

        return view;
    }
}
