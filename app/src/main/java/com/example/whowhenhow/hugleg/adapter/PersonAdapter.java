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
        ImageView personImage;//商品头字母
        TextView personName;//商品名称
        TextView personCountry;
        TextView personGender;
        TextView personlife;
        public ViewHolder(View view){//ViewHolder初始赋值
            super(view);
            personView = view;
            //personImage = (ImageView) view.findViewById(R.id.person_image);
            //personName = (TextView) view.findViewById(R.id.person_name);
            //personCountry = (TextView) view.findViewById(R.id.person_country);
            //personGender = (TextView) view.findViewById(R.id.person_gender);
           // personlife = (TextView) view.findViewById(R.id.person_life);
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

    }
    @Override
    public int getItemCount(){
        return mPersonList.size();
    }
}

