package com.example.campustrading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHoler> {
    private List<ItemObject> data;
    private Context context;

    public ItemListAdapter( Context context , List<ItemObject> data) {
        this.data = data;
        this.context = context;
    }
    /**
     * 定义点击事件接口
     */
    public interface OnItemClickListener{
        void onClick ( int position );
    }
    public interface OnItemLongClickListener{
        void onLongClick ( int position );
    }
    private  OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public void setOnItemClickListener ( OnItemClickListener listener ) {
        this.listener = listener;
    }
    public void setOnItemLongClickListener ( OnItemLongClickListener longListener ) {
        this.longListener = longListener;
    }
    @NonNull
    @Override
    public ItemViewHoler onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View container = LayoutInflater.from ( parent.getContext () ).inflate (
                R.layout.layout_itemview ,parent,false );
        return new ItemViewHoler (container);
    }

    @Override
    public void onBindViewHolder ( @NonNull ItemViewHoler holder , final int position ) {
        holder.bind ( this.context,data.get ( position ));
        holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( listener != null ){
                    listener.onClick ( position );
                }
            }
        } );
        holder.itemView.setOnLongClickListener ( new View.OnLongClickListener ( ) {
            @Override
            public boolean onLongClick ( View v ) {
                longListener.onLongClick ( position );
                return true;
            }
        } );
    }


    @Override
    public int getItemCount () {
        return data.size ();
    }

    public class ItemViewHoler extends RecyclerView.ViewHolder{
        private TextView textView_title;
        public ItemViewHoler ( @NonNull View itemView ) {
            super ( itemView );
            textView_title = (TextView) itemView.findViewById ( R.id.item_title );
        }
        public void bind( Context context, ItemObject itemObject) {
            textView_title.setText ( itemObject.getItemname () );
        }

    }
}
