package com.fei.infrareddemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class ContorlAdapter extends RecyclerView.Adapter<ContorlAdapter.MyHolder>{

    private List<String> data ;
    private LayoutInflater mInflater;

    public ContorlAdapter(Context context , List<String> data){
        mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_contorl,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.item_bt.setText(data.get(i));
        myHolder.item_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnItemClick){
                    mOnItemClick.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        Button item_bt;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            if(null == item_bt){
                item_bt = itemView.findViewById(R.id.item_bt);
            }
        }
    }

    public interface OnItemClick{
        void onClick(int position);
    }

    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick){
        mOnItemClick = onItemClick;
    }

}
