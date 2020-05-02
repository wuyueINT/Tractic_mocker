package com.wuyue.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuyue.tractic_mocker.Hero;
import com.wuyue.tractic_mocker.R;

import java.util.List;

import static android.content.ContentValues.TAG;

//recyclerView的适配器
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    BlankFragment.FragmentListener mFragmentListener;
    List<Hero> heroes;
    Context context;

    public RecyclerAdapter(Context context, List<Hero> heroes) {
        this.mFragmentListener = (BlankFragment.FragmentListener) context;
        this.heroes = heroes;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, null);
        return new VH(view);
    }

    //绑定部件的操作
    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.btn.setText(heroes.get(position).getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里向列表内添加英雄，将信息传回mainActivity
                Log.e(TAG, "onClick: 添加英雄");
                mFragmentListener.toActivity(heroes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }


    //recycler的属性
    public static class VH extends RecyclerView.ViewHolder{

        private Button btn;

        public VH(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn01);
        }
    }
}
