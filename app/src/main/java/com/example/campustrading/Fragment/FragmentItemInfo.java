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

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.I;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.campustrading.MainActivity.objid;


public class FragmentItemInfo extends Fragment {

    private TextView textView_itemname;
    private TextView textView_iteminfo;
    private TextView textView_itemprice;
    private TextView textView_username;
    private TextView textView_issale;
    private View view;
    private User user = BmobUser.getCurrentUser(User.class);
    private static String tel;
    @Nullable
    @Override
    public View onCreateView ( @NonNull final LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_iteminfo ,container,false );
        setHasOptionsMenu ( true );

        Button button_back = (Button )view.findViewById ( R.id.iteminfo_button_back );
        Button button_buy = (Button )view.findViewById ( R.id.iteminfo_buy );

        textView_itemname = (TextView )view.findViewById ( R.id.iteminfo_itemname ) ;
        textView_iteminfo = (TextView )view.findViewById ( R.id.iteminfo_iteminfo ) ;
        textView_itemprice = (TextView )view.findViewById ( R.id.iteminfo_itemprice ) ;
        textView_username = (TextView )view.findViewById ( R.id.iteminfo_user ) ;
        textView_issale = (TextView ) view.findViewById ( R.id.iteminfo_issale);

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
                            textView_itemprice.setText ( itemObject.getItemprice ().toString ()+" 元" );
                            textView_username.setText ( "用户："+itemObject.getHostname () );
                            System.out.println ( "currentbuyer:"+itemObject.getCurrentbuyer () );
                            System.out.println ( "userphone:"+user.getMobilePhoneNumber () );
                            if ( itemObject.getCurrentbuyer ().equals ( " " ) ){
                                textView_issale.setText ( "可以申请购买" );
                            }else if(itemObject.getCurrentbuyer ().equals ( user.getMobilePhoneNumber () )){
                                textView_issale.setText ( "您正在申请购买" );
                            }else {
                                textView_issale.setText ( "已被申请购买" );
                            }
                            System.out.println ( itemObject.getHostname () + ":"+itemObject.getHosttel () );
                            tel = itemObject.getHosttel ();
                        }
                    }else{
                        Toast.makeText ( getActivity (),"当前产品已不存在，请返回！",Toast.LENGTH_LONG ).show ();
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        },objid);
        button_back.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentHome fragmentHome = new FragmentHome ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentHome ).commit ();
            }
        } );

        button_buy.setOnClickListener ( new View.OnClickListener ( ) {
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
                                    if(!itemObject.getHosttel ().equals ( user.getMobilePhoneNumber () )){
                                        if(itemObject.getCurrentbuyer ().equals ( " " )){
                                            itemObject.setCurrentbuyer (user.getMobilePhoneNumber ());
                                            itemObject.update(objid, new UpdateListener () {

                                                @Override
                                                public void done(BmobException e) {
                                                    if(e==null){
                                                        Toast.makeText (getActivity (),"请求成功:"+itemObject.getUpdatedAt(),Toast.LENGTH_SHORT).show ();
                                                        textView_issale.setText ( "您正在申请购买" );
                                                    }else{
                                                        Toast.makeText (getActivity (),"请求失败：" + e.getMessage(),Toast.LENGTH_SHORT).show ();
                                                    }
                                                }

                                            });
                                        }else {
                                            Toast.makeText ( getActivity (),"当前已有人申请购买！",Toast.LENGTH_SHORT ).show ();
                                        }
                                    }else {
                                        Toast.makeText ( getActivity (),"您不能购买您自己的商品！",Toast.LENGTH_SHORT ).show ();
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
                System.out.println ( user.getMobilePhoneNumber ()+":"+tel );
                if(tel.trim ().equals ( user.getMobilePhoneNumber ().trim () ) || user.getTransaction ()>=50){
                    ItemObject item = new ItemObject ();
                    item.delete ( objid , new UpdateListener ( ) {
                        @Override
                        public void done ( BmobException e ) {
                            if ( e==null ) {
                                Toast.makeText ( getActivity ( ) , "商品已下架" , Toast.LENGTH_SHORT ).show ( );
                            }else {
                                Log.e("商品下架失败：",e.getMessage () );
                            }
                        }
                    } );
//                    FragmentHome fragmentHome = new FragmentHome ();
//                    getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentHome ).commit ();
                }else {
                    Toast.makeText ( getContext (),"您还不是管理员，不能删除他人商品！",Toast.LENGTH_SHORT).show ();
                }
                return false;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }
}
