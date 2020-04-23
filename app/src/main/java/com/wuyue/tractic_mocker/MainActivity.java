package com.wuyue.tractic_mocker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyue.recyclerview.Fragment02;
import com.wuyue.recyclerview.Fragment03;
import com.wuyue.recyclerview.Fragment04;
import com.wuyue.recyclerview.Fragment05;
import com.wuyue.recyclerview.BlankFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements BlankFragment.FragmentListener {


    //这部分声明用于布局
    private ViewPager vp;
    private List<Fragment> fragments;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv_main;
    private int tabLineLength;// 1/3屏幕宽
    private int currentPage = 0;// 初始化当前页为0（第一页）
    private Button tabline;
    private Button clear;

    //这部分变量用于内容
    Map<Hero, Integer> chess;
    Map<String, Integer> raceMap;
    Map<String, Integer> proMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chess = new HashMap<>();
        raceMap = new HashMap<>();
        proMap = new HashMap<>();

        initTabLine();
        initView();
    }

    //初始化滑动条
    private void initTabLine() {
        // 获取显示屏信息
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // 1/3屏幕宽度
        tabLineLength = metrics.widthPixels / 5;
        // 获取控件实例
        tabline = (Button) findViewById(R.id.tabline);
        // 控件参数
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width = tabLineLength;
        tabline.setLayoutParams(lp);
    }

    //初始化视图
    private void initView() {

        tv_main = findViewById(R.id.main_tv1);
        clear = findViewById(R.id.clear);

        vp = findViewById(R.id.viewPager);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);
        fragments = new ArrayList<>();

        //模拟heros数据
        ArrayList<Hero> heros = new ArrayList<>();
        heros.add(new Hero("卡牌", Race.WEI_LAI, Profession.FASHI, 1));
        heros.add(new Hero("霞", Race.XING_SHEN, Profession.JIANSHI, 1));
        heros.add(new Hero("石头人", Race.AO_DE_SAI, Profession.DOUSHI, 1));


        BlankFragment fragment = BlankFragment.newInstance(heros);
        Fragment02 fragment2 = new Fragment02();
        Fragment03 fragment3 = new Fragment03();
        Fragment04 fragment4 = new Fragment04();
        Fragment05 fragment5 = new Fragment05();

        fragments.add(fragment);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);

        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        //设置滑动监听
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) tabline.getLayoutParams();

                if (currentPage==0 && position==0){
                    ll.leftMargin = (int)(currentPage*tabLineLength+positionOffset*tabLineLength);
                } else if (currentPage==1 && position==0){
                    ll.leftMargin = (int)(currentPage*tabLineLength+positionOffset*tabLineLength-tabLineLength);
                } else if (currentPage==1 && position==1){
                    ll.leftMargin = (int)(currentPage*tabLineLength+positionOffset*tabLineLength);
                } else if (currentPage==2 && position==1){
                    ll.leftMargin = (int)(tabLineLength*(currentPage-1+positionOffset));
                } else if (currentPage==2 && position==2){
                    ll.leftMargin = (int)(tabLineLength*(currentPage+positionOffset));
                } else if (currentPage==3 && position==2){
                    ll.leftMargin = (int)(tabLineLength*(currentPage-1+positionOffset));
                } else if (currentPage==3 && position==3){
                    ll.leftMargin = (int)(tabLineLength*(currentPage+positionOffset));
                } else if (currentPage==4 && position==3){
                    ll.leftMargin = (int)(tabLineLength*(currentPage-1+positionOffset));
                }

                tabline.setLayoutParams(ll);
            }

            @Override
            public void onPageSelected(int position) {

                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);
                tv4.setTextColor(Color.BLACK);
                tv5.setTextColor(Color.BLACK);

                switch (position){
                    case 0:
                        tv1.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 1:
                        tv2.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 2:
                        tv3.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 3:
                        tv4.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 4:
                        tv5.setTextColor(Color.rgb(51, 153, 0));
                        break;
                }
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //清空英雄
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chess.clear();
                raceMap.clear();
                proMap.clear();
                tv_main.setText("");
            }
        });

    }

    @Override
    public void toActivity(Hero hero) {

        //这里按照英雄名称查找，并添加到已选择的英雄容器中
        if (chess.containsKey(hero)){
            chess.put(hero, chess.get(hero)+1);
        } else {
            chess.put(hero, 1);
        }

        if (raceMap.containsKey(hero.getRace())){
            raceMap.put(hero.getRace(), raceMap.get(hero.getRace())+1);
        } else {
            raceMap.put(hero.getRace(), 1);
        }

        if (proMap.containsKey(hero.getPro())){
            proMap.put(hero.getPro(), proMap.get(hero.getPro())+1);
        } else {
            proMap.put(hero.getPro(), 1);
        }

        //整理英雄羁绊信息并输出
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Hero, Integer> heroIntegerEntry : chess.entrySet()) {
            Hero hero0 = (Hero) ((Map.Entry) heroIntegerEntry).getKey();
            int num = (int) ((Map.Entry) heroIntegerEntry).getValue();
            sb.append(hero0.getName()).append(":").append(num).append(";");
        }
        sb.append("\n");

        for (Map.Entry<String, Integer> raceIntegerEntry : raceMap.entrySet()) {
            String race = (String) ((Map.Entry) raceIntegerEntry).getKey();
            int num = (int) ((Map.Entry) raceIntegerEntry).getValue();
            sb.append(race).append(":").append(num).append("\n");
        }

        for (Map.Entry<String, Integer> professionIntegerEntry : proMap.entrySet()) {
            String pro = (String) ((Map.Entry) professionIntegerEntry).getKey();
            int num = (int) ((Map.Entry) professionIntegerEntry).getValue();
            sb.append(pro).append(":").append(num).append("\n");
        }

        tv_main.setText(sb.toString());
    }

}
