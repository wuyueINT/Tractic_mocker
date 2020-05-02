package com.wuyue.tractic_mocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class FetterActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_heros;
    private TextView tv_effect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetter);

        initView();

        //获取需要显示的羁绊信息，并显示在视图中
        Intent intent = getIntent();
        String fetterName = intent.getStringExtra("wuyue");
        ArrayList<Fetter> fetters;
        try {
            fetters = XmlParser.parseXmlFetters(getAssets().open("fetters.xml"));
            for (Fetter fetter: fetters){
                if (fetter.getName().equals(fetterName)){
                    tv_name.setText(fetter.getName());
                    tv_heros.setText(fetter.getHerosInFetter());
                    tv_effect.setText(fetter.getEffetOfFetter());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        tv_name = findViewById(R.id.tv_name);
        tv_heros = findViewById(R.id.tv_heros);
        tv_effect = findViewById(R.id.tv_effect);
    }
}
