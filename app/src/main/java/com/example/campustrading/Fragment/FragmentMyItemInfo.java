package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.campustrading.ItemObject;
import com.example.campustrading.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

import static com.example.campustrading.MainActivity.objid;


public class FragmentMyItemInfo extends Fragment {

    TextView textView_itemname;
    TextView textView_iteminfo;
    TextView textView_itemprice;
    private View view;
    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_myiteminfo,container,false );

        Button button_back = (Button )view.findViewById ( R.id.myiteminfo_button_back );

        textView_itemname = (TextView )view.findViewById ( R.id.myiteminfo_itemname ) ;
        textView_iteminfo = (TextView )view.findViewById ( R.id.myiteminfo_iteminfo ) ;
        textView_itemprice = (TextView )view.findViewById ( R.id.myiteminfo_itemprice ) ;

        System.out.println ( objid );
        BmobQuery< ItemObject > query = new BmobQuery<ItemObject> ();
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
                            textView_itemprice.setText ( "商品价格："+itemObject.getItemprice ().toString () );
                        }
                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        },objid);

        button_back.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FragmentMyItem fragmentMyItem = new FragmentMyItem ();
                getActivity ().getSupportFragmentManager ().beginTransaction ().replace ( R.id.view_main,fragmentMyItem ).commit ();
            }
        } );
        return view;
    }
}
