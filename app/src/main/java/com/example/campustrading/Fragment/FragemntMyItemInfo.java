package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.util.Log;
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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.campustrading.ItemObject;
import com.example.campustrading.R;
import com.example.campustrading.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.campustrading.MainActivity.objid;

public class FragemntMyItemInfo extends Fragment {

    private TextView textView_itemname;
    private TextView textView_iteminfo;
    private TextView textView_itemprice;
    private TextView textView_currentbuyer;
    private View view;
    private User user = BmobUser.getCurrentUser ( User.class );
    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_myiteminfo ,container,false );
        setHasOptionsMenu(true);

        Button button_back = (Button )view.findViewById ( R.id.myiteminfo_button_back );
        Button button_clean = (Button )view.findViewById ( R.id.myiteminfo_clean );
        Button button_sure = (Button )view.findViewById ( R.id.myiteminfo_sure );

        textView_itemname = (TextView )view.findViewById ( R.id.myiteminfo_itemname ) ;
        textView_iteminfo = (TextView )view.findViewById ( R.id.myiteminfo_iteminfo ) ;
        textView_itemprice = (TextView )view.findViewById ( R.id.myiteminfo_itemprice ) ;
        textView_currentbuyer = (TextView )view.findViewById ( R.id.myiteminfo_currentbuyer );

        System.out.println ( objid );
        String bql="select * from item where objectId = ?";
        new BmobQuery<ItemObject>().doSQLQuery(bql,new SQLQueryListener<ItemObject> (){

            @Override
            public void done( BmobQueryResult<ItemObject> result, BmobException e) {
                if(e ==null){
                    List<ItemObject> list = (List<ItemObject>) result.getResults();
                    if(list!=null && list.size()>0){
                        for (ItemObject itemObject : list){
                            System.out.println ( itemObject.getObjectId () );
                            textView_itemname.setText ( "商品名称："+itemObject.getItemname () );
                            textView_iteminfo.setText ( "商品信息："+itemObject.getIteminfo () );
                            textView_itemprice.setText ( itemObject.getItemprice ().toString () +" 元");
                            textView_currentbuyer.setText ( "申请购买者："+itemObject.getCurrentbuyer ().toString () );
                        }
                    }else{
                        Toast.makeText ( getActivity (),"当前产品已不存在，请返回！",Toast.LENGTH_LONG ).show ();
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        },objid);


        button_clean.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String bql="select * from item where objectId = ?";
                new BmobQuery<ItemObject>().doSQLQuery(bql,new SQLQueryListener<ItemObject> (){

                    @Override
                    public void done( BmobQueryResult<ItemObject> result, BmobException e) {
                        if(e ==null){
                            List<ItemObject> list = (List<ItemObject>) result.getResults();
                            if(list!=null && list.size()>0){
                                for (final ItemObject itemObject : list){
                                    itemObject.setCurrentbuyer ( " " );
                                    itemObject.update ( objid , new UpdateListener ( ) {
                                        @Override
                                        public void done ( BmobException e ) {
                                                if(e==null){
                                                    Toast.makeText (getActivity (),"清除成功！:"+itemObject.getUpdatedAt(),Toast.LENGTH_LONG).show ();
                                                }else{
                                                    Toast.makeText (getActivity (),"清除失败：" + e.getMessage(),Toast.LENGTH_LONG).show ();
                                                }
                                            }
                                    } );
                                    textView_currentbuyer.setText ( "申请购买者：" );
                                }
                            }else{
                                Toast.makeText ( getActivity (),"当前产品已不存在，请返回！",Toast.LENGTH_LONG ).show ();
                            }
                        }else{
                            Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                        }
                    }
                },objid);
            }
        } );

        button_sure.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String bql="select * from item where objectId = ?";
                new BmobQuery<ItemObject>().doSQLQuery(bql,new SQLQueryListener<ItemObject> (){

                    @Override
                    public void done( BmobQueryResult<ItemObject> result, BmobException e) {
                        if(e ==null){
                            List<ItemObject> list = (List<ItemObject>) result.getResults();
                            if(list!=null && list.size()>0){
                                for (ItemObject itemObject : list){
                                    if ( !itemObject.getCurrentbuyer ().equals ( " " ) ){
                                        user.setTransaction (user.getTransaction ()+1);
                                        user.update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    Log.i ("info:","更新用户信息成功");
                                                } else {
                                                    Snackbar.make(view, "更新用户信息失败" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                                    Log.e("error", e.getMessage());
                                                }
                                            }
                                        });
                                        Toast.makeText ( getActivity (),"交易成功！",Toast.LENGTH_SHORT).show ();
                                        ItemObject item = new ItemObject ();
                                        item.setObjectId ( objid );
                                        item.delete ( new UpdateListener ( ) {
                                            @Override
                                            public void done ( BmobException e ) {
                                                if(e==null){
                                                    Toast.makeText (getActivity (),"商品已下架",Toast.LENGTH_SHORT).show ();

                                                }else{
                                                    Toast.makeText (getActivity (),"商品下架失败" + e.getMessage(),Toast.LENGTH_LONG).show ();
                                                }
                                            }
                                        } );
                                    }else {
                                        Toast.makeText ( getActivity (),"当前没有人申请购买您的商品！",Toast.LENGTH_SHORT ).show ();
                                    }
                                }
                            }else{
                                Toast.makeText ( getActivity (),"当前产品已不存在，请返回！",Toast.LENGTH_SHORT ).show ();
                            }
                        }else{
                            Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                        }
                    }
                },objid);
            }
        } );

        button_back.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentMyItem fragmentMyItem = new FragmentMyItem ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentMyItem ).commit ();
            }
        } );
        return view;
    }

    /**
     * Fragment中初始化Toolbar
     * @param toolbar
     * @param title 标题
     * @param isDisplayHomeAsUp 是否显示返回箭头
     */
    public void initToolbar( Toolbar toolbar, String title, boolean isDisplayHomeAsUp) {
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp);
        }
    }

    @Override
    public void onCreateOptionsMenu ( @NonNull Menu menu , @NonNull MenuInflater inflater ) {
        inflater.inflate ( R.menu.longclick_delete,menu );
        super.onCreateOptionsMenu ( menu , inflater );
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.item_delete:
                ItemObject itemObject = new ItemObject ();
                itemObject.setObjectId ( objid );
                itemObject.delete ( new UpdateListener ( ) {
                    @Override
                    public void done ( BmobException e ) {
                        if(e==null){
                            Toast.makeText (getActivity (),"删除成功:"+user.getUpdatedAt(),Toast.LENGTH_LONG).show ();

                        }else{
                            Toast.makeText (getActivity (),"删除失败：" + e.getMessage(),Toast.LENGTH_LONG).show ();
                        }
                    }
                } );
                return false;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }
}
