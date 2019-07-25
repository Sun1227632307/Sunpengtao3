package com.example.app2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Main3Activity extends AppCompatActivity {

    private ImageView mIv;
    /**
     * name
     */
    private TextView mTv1;
    /**
     * mima
     */
    private TextView mTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        String name = getIntent().getStringExtra("name");
        String mima = getIntent().getStringExtra("mima");
        String tupian = getIntent().getStringExtra("tupian");
        Glide.with(this).load(tupian).into(mIv);
        mTv1.setText(name);
        mTv2.setText(mima);
    }
}
