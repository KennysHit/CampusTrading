package com.example.campustrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private List<ItemObject> data;
    private RecyclerView itemList;
    private ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById ( R.id.bottom_navigation );
        final LinearLayout linearLayout_home = (LinearLayout ) findViewById ( R.id.view_home );
        final LinearLayout linearLayout_message = (LinearLayout ) findViewById ( R.id.view_message );
        final LinearLayout linearLayout_person = (LinearLayout ) findViewById ( R.id.view_person );
        dataInit ();

        itemList = (RecyclerView )findViewById ( R.id.list_item ) ;
        itemListAdapter = new ItemListAdapter ( this,data);
        itemList.setLayoutManager ( new LinearLayoutManager ( this ) );//设置样式列表
        itemList.setAdapter ( itemListAdapter );
        itemListAdapter.setOnItemClickListener ( new ItemListAdapter.OnItemClickListener ( ) {
            @Override
            public void onClick ( int position ) {
                Toast.makeText ( HomeActivity.this,"short click!",Toast.LENGTH_LONG ).show ();
            }
        } );
        itemListAdapter.setOnItemLongClickListener ( new ItemListAdapter.OnItemLongClickListener ( ) {
            @Override
            public void onLongClick ( int position ) {
                Toast.makeText ( HomeActivity.this,"long click",Toast.LENGTH_LONG ).show ();
            }
        } );
        bottomNavigationView.setOnNavigationItemReselectedListener ( new BottomNavigationView.OnNavigationItemReselectedListener ( ) {
            @Override
            public void onNavigationItemReselected ( @NonNull MenuItem menuItem ) {
                switch ( menuItem.getItemId () ){
                    case R.id.home:
                        Toast.makeText ( HomeActivity.this,"home is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_person.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.INVISIBLE );
                        linearLayout_home.setVisibility ( View.VISIBLE );
                        break;
                    case R.id.message:
                        Toast.makeText ( HomeActivity.this,"message is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_person.setVisibility ( View.INVISIBLE );
                        linearLayout_home.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.VISIBLE );
                        break;
                    case R.id.peroson:
                        Toast.makeText ( HomeActivity.this,"person is clicked!",Toast.LENGTH_LONG ).show ();
                        linearLayout_home.setVisibility ( View.INVISIBLE );
                        linearLayout_message.setVisibility ( View.INVISIBLE );
                        linearLayout_person.setVisibility ( View.VISIBLE );
                        break;
                }
            }
        } );
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
}
