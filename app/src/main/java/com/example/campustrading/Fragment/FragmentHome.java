package com.example.campustrading.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campustrading.ItemListAdapter;
import com.example.campustrading.ItemObject;
import com.example.campustrading.R;
import com.example.campustrading.TestUser;
import com.example.campustrading.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class FragmentHome extends Fragment {
    private RecyclerView itemList;
    private ItemListAdapter itemListAdapter;
    private User user = new User ();
    private View view;//定义view用来设置fragment的layout

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_home,container,false );

        dataInit ();
        return view;

    }


    public void dataInit(){
        user = BmobUser.getCurrentUser ( user.getClass () );
        String bql="select * from item";
        new BmobQuery<ItemObject> ().doSQLQuery(bql,new SQLQueryListener<ItemObject> (){

            @Override
            public void done( BmobQueryResult<ItemObject> result, BmobException e) {
                if(e ==null){
                    List<ItemObject> list = (List<ItemObject>) result.getResults();
                    if(list!=null && list.size()>0){
                        initRecyclerView ( list );
                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
    }
    private void initRecyclerView(List<ItemObject> data) {
        //获取RecyclerView
        itemList =(RecyclerView)view.findViewById ( R.id.home_item_list );
        //创建adapte
        itemListAdapter = new ItemListAdapter (getActivity(), data);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity ()));
        //给RecyclerView设置adapter
        itemList.setAdapter(itemListAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        itemListAdapter.setOnItemClickListener ( new ItemListAdapter.OnItemClickListener ( ) {
            @Override
            public void onClick ( int position ) {
                Toast.makeText ( getActivity (),"short click!",Toast.LENGTH_LONG ).show ();
            }
        } );
        itemListAdapter.setOnItemLongClickListener ( new ItemListAdapter.OnItemLongClickListener ( ) {
            @Override
            public void onLongClick ( int position ) {
                Toast.makeText ( getActivity (),"long click",Toast.LENGTH_LONG ).show ();
            }
        } );
    }
}
