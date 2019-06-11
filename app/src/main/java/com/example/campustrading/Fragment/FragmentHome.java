package com.example.campustrading.Fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class FragmentHome extends Fragment {
    private List< ItemObject > data;
    private RecyclerView itemList;
    private ItemListAdapter itemListAdapter;
    private View view;//定义view用来设置fragment的layout

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate ( R.layout.fragment_home,container,false );

        dataInit ();
        initRecyclerView ();
        return view;

    }


    public void dataInit(){
        data = new ArrayList<ItemObject> (  );
        ItemObject itemObject1 = new ItemObject ( "商品一","","100" );
        ItemObject itemObject2 = new ItemObject ( "商品二","","200" );
        ItemObject itemObject3 = new ItemObject ( "商品三","","300" );
        data.add ( itemObject1 );
        data.add ( itemObject2 );
        data.add ( itemObject3 );
    }
    private void initRecyclerView() {
        //获取RecyclerView
        itemList =(RecyclerView)view.findViewById ( R.id.item_list );
        //创建adapter
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
