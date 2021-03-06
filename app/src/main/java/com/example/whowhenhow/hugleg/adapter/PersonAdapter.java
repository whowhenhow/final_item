package com.example.whowhenhow.hugleg.adapter;

/**
 * Created by 黄国正 on 2017/12/23.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.bean.Label;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.R;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by 黄国正 on 2017/11/19.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person_info> mPersonList; //存储商品信息
    private OnItemLongClickListener mOnItemLongClickListener; //重写长按监听器
    private OnItemClickListener mOnItemClickListener;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder{ //使用Viewholder为列表快速设置值
        View personView; //子项视图
        ImageView user_avatar;//商品头字母
        TextView user_nickname;//商品名称
        TextView user_address;
        TextView user_introduction;
        public ViewHolder(View view){//ViewHolder初始赋值
            super(view);
            personView = view;
            user_avatar = (ImageView) view.findViewById(R.id.avatar);
            user_nickname = (TextView) view.findViewById(R.id.nickname);
            user_address = (TextView) view.findViewById(R.id.adress);
            user_introduction = (TextView) view.findViewById(R.id.introduction);
        }
    }
    public PersonAdapter(List<Person_info> personList, Context context){
        mPersonList = personList;
        this.context = context;
    }
    public void setonItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener){
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
    public void setonItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }
    /*****绑定数据到正确的子项试图上******/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        Person_info person_info = mPersonList.get(position);
        holder.user_avatar.setImageResource(R.drawable.hugleg);
        holder.user_nickname.setText(person_info.getUser_nickname());
        holder.user_address.setText(person_info.getUser_address());
        holder.user_introduction.setText(person_info.getUser_introduction());
        if (mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }
    @Override
    public int getItemCount(){
        return mPersonList.size();
    }
}

