package com.example.whowhenhow.hugleg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Project;

import java.util.List;

/**
 * Created by 黄国正 on 2018/1/5.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> mProjectList; //存储商品信息
    private OnItemLongClickListener mOnItemLongClickListener; //重写长按监听器
    private OnItemClickListener mOnItemClickListener;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder{ //使用Viewholder为列表快速设置值
        View personView; //子项视图
        TextView project_name;//商品名称
        TextView project_manager_account;
        TextView need_person_number;
        TextView project_introduction;
        TextView project_main_address;
        public ViewHolder(View view){//ViewHolder初始赋值
            super(view);
            personView = view;
            project_name = (TextView) view.findViewById(R.id.projectname);
            project_manager_account = (TextView) view.findViewById(R.id.manager);
            need_person_number = (TextView) view.findViewById(R.id.need_person_number);
            project_introduction = (TextView) view.findViewById(R.id.introduction);
            project_main_address = (TextView) view.findViewById(R.id.adress);
        }
    }
    public ProjectAdapter(List<Project> personList, Context context){
        mProjectList = personList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }
    /*****绑定数据到正确的子项试图上******/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        Project project = mProjectList.get(position);
        holder.project_name.setText(project.getProject_name());
        holder.project_manager_account.setText(project.getProject_manager_account());
        holder.need_person_number.setText(project.getNeed_person_number() + "");
        holder.project_main_address.setText(project.getProject_main_address());
        holder.project_introduction.setText(project.getProject_introduction());
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
        return mProjectList.size();
    }
}
