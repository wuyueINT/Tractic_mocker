package com.wuyue.tractic_mocker;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.pm.PackageManager;
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

import java.io.IOException;
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

    //动态权限申请
    private String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chess = new HashMap<>();
        raceMap = new HashMap<>();
        proMap = new HashMap<>();

        initTabLine();
        initView();
        requestPermission(PERMISSIONS_STORAGE);
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

        //从xml文件中解析heros数据
        ArrayList<Hero> heros1 = null;
        ArrayList<Hero> heros2 = null;
        ArrayList<Hero> heros3 = null;
        ArrayList<Hero> heros4 = null;
        ArrayList<Hero> heros5 = null;
        try {
            heros1 = XmlParser.parseXml(getAssets().open("heros_1.xml"));
            heros2 = XmlParser.parseXml(getAssets().open("heros_2.xml"));
            heros3 = XmlParser.parseXml(getAssets().open("heros_3.xml"));
            heros4 = XmlParser.parseXml(getAssets().open("heros_4.xml"));
            heros5 = XmlParser.parseXml(getAssets().open("heros_5.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fragments.add(BlankFragment.newInstance(heros1));
        fragments.add(BlankFragment.newInstance(heros2));
        fragments.add(BlankFragment.newInstance(heros3));
        fragments.add(BlankFragment.newInstance(heros4));
        fragments.add(BlankFragment.newInstance(heros5));

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

    public void requestPermission(String[] permissions){
        List<String> permissionList = new ArrayList<>();
        // 遍历每一个申请的权限，把没有通过的权限放在集合中
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        // 申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), 1);
        }
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
