package com.example.sunpengtao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sunpengtao.apis.MyApplication;
import com.example.sunpengtao.beans.Shuju;
import com.example.sunpengtao.db.ShujuDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入用户名
     */
    private EditText mEt1;
    /**
     * 请输入密码
     */
    private EditText mEt2;
    /**
     * 登录
     */
    private Button mBt1;
    /**
     * 注册
     */
    private Button mBt2;
    private ShujuDao shujuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shujuDao = MyApplication.getInstance().getDaoSession().getShujuDao();
        initView();
        initData();
    }

    private void initData() {
        String name = getIntent().getStringExtra("name");
        mEt1.setText(name);
    }


    private void initView() {
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt2 = (EditText) findViewById(R.id.et2);
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //获取到name+mima
        String name1 = mEt1.getText().toString();
        String mima1 = mEt2.getText().toString();

        switch (v.getId()) {
            default:
                break;
            case R.id.bt1:
                //判断账号密码
                if (mima1!=null&&name1!=null){
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Main3Activity.class));
                }else{
                    Toast.makeText(this, "数据为空请重新登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt2:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
        }
    }
}
