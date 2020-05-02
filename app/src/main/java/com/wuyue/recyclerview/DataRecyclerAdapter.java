package com.wuyue.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuyue.tractic_mocker.FetterActivity;
import com.wuyue.tractic_mocker.R;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.VH> {

    private Context context;
    private List<Map.Entry<String, Integer>> tmpList;

    public DataRecyclerAdapter(Context context, List<Map.Entry<String, Integer>> tmpList) {
        this.context = context;
        this.tmpList = tmpList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_data, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        StringBuilder sb = new StringBuilder();
        Map.Entry<String, Integer> entry = tmpList.get(position);
        sb.append(entry.getKey()).append(":").append(entry.getValue());
        holder.tv.setText(sb.toString());
        //之后添加一个点击文本就在新页面显示相关信息的功能
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("wuyue", tmpList.get(position).getKey());
                intent.setClass(context, FetterActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tmpList.size();
    }

    public class VH extends RecyclerView.ViewHolder{

        private TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_data);
        }
    }

}
