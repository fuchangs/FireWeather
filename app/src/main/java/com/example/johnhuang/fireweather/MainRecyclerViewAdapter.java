package com.example.johnhuang.fireweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JohnHuang on 2016/9/28.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.FunctionViewHolder> {

    List <String> functionListTitle = new ArrayList<String>();
    List <String> functionListSubTitle = new ArrayList<String>();

    public OnItemClickListener onItemClickListener;

    public MainRecyclerViewAdapter(Context context){
        GetFunctionList(context);
    }

    private void GetFunctionList(Context context) {
        String [] titles = context.getResources().getStringArray(R.array.main_function_list_title);
        String [] subtitles = context.getResources().getStringArray(R.array.main_function_list_subtitle);
        for (int i = 0 ; i < titles.length; ++i){
            functionListTitle.add(titles[i]);
            functionListSubTitle.add(subtitles[i]);
        }
    }

    @Override
    public void onBindViewHolder(FunctionViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position, payloads);
        holder.textViewTitle.setText(functionListTitle.get(position));
        holder.textViewSubTitle.setText(functionListSubTitle.get(position));
    }

    @Override
    public int getItemCount() {
//        return 0;
        return functionListTitle == null ? 0 :functionListTitle.size();
    }

    @Override
    public FunctionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview,parent,false);
        FunctionViewHolder viewHolder = new FunctionViewHolder(view);
        return viewHolder;
    }

    public class FunctionViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewSubTitle;

        public FunctionViewHolder(View view){
            super(view);

            findViews(view);
            setClickListener(view);

        }

        private void findViews(View view) {
            textViewTitle = (TextView) view.findViewById(R.id.main_recyclerview_tvName);
            textViewSubTitle = (TextView) view.findViewById(R.id.main_recyclerview_tvSubName);
        }

        private void setClickListener(View view){
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,getPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
