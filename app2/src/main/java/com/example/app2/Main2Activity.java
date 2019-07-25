package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app2.fragments.OneFragment;
import com.example.app2.fragments.TwoFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRl;
    /**
     * 首页
     */
    private Button mBt1;
    /**
     * 我的
     */
    private Button mBt2;
    private LinearLayout mLl;
    private FragmentManager fs;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private FragmentTransaction transaction;
    private FragmentTransaction transaction1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initFragment();
    }

    private void initFragment() {
        fs = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        transaction = fs.beginTransaction();
        transaction.add(R.id.Rl,oneFragment);
        transaction.add(R.id.Rl,twoFragment);
        transaction.hide(twoFragment);
        transaction.show(oneFragment);
        transaction.commit();
    }

    private void initView() {
        mRl = (RelativeLayout) findViewById(R.id.Rl);
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        mLl = (LinearLayout) findViewById(R.id.ll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt1:
                transaction1 = fs.beginTransaction();
                transaction1.show(oneFragment);
                transaction1.hide(twoFragment);
                transaction1.commit();
                break;
            case R.id.bt2:
                transaction1 = fs.beginTransaction();
                transaction1.show(twoFragment);
                transaction1.hide(oneFragment);
                transaction1.commit();
                break;
        }
    }
}
