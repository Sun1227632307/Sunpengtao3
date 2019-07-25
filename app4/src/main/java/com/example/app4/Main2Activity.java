package com.example.app4;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app4.adapters.FragmentAdapter;
import com.example.app4.fragments.OneFragment;
import com.example.app4.fragments.TwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    /**
     * toolbar标题
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private TabLayout mTable;
    private LinearLayout mLl;
    private DrawerLayout mDr;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        inittoolbar();
        initData();
    }

    private void initData() {

        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(fragmentAdapter);
        mTable.addTab(mTable.newTab().setText("首页").setIcon(R.drawable.select01));
        mTable.addTab(mTable.newTab().setText("我的").setIcon(R.drawable.select01));
        mTable.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    mToolbarTitle.setText("首页");
                }else {
                    mToolbarTitle.setText("我的");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void inittoolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTable = (TabLayout) findViewById(R.id.table);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mDr = (DrawerLayout) findViewById(R.id.Dr);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
