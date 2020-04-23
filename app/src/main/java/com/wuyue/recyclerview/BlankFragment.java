package com.wuyue.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wuyue.tractic_mocker.Hero;
import com.wuyue.tractic_mocker.R;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    //这里使用bundle来初始化fragment
    public static BlankFragment newInstance(ArrayList<Hero> heroes){
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("heros", heroes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement01, null);
        initView(view);

        List<Hero> heros = new ArrayList<>();
        //将英雄数据输入
        if (getArguments()!=null){
            heros = getArguments().getParcelableArrayList("heros");
        }

        adapter = new RecyclerAdapter(getActivity(), heros);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler01);
    }

    public interface FragmentListener{
        void toActivity(Hero hero);
    }

}
