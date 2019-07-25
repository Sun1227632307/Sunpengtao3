package com.example.sunpengtao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sunpengtao.apis.MyApplication;
import com.example.sunpengtao.beans.Shuju;
import com.example.sunpengtao.db.ShujuDao;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIv;
    /**
     * 用户名
     */
    private EditText mEt1;
    /**
     * 密码
     */
    private EditText mEt2;
    /**
     * 确认密码
     */
    private EditText mEt3;
    /**
     * 注册
     */
    private Button mBt;
    private ShujuDao shujuDao;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        shujuDao = MyApplication.getInstance().getDaoSession().getShujuDao();
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt2 = (EditText) findViewById(R.id.et2);
        mEt3 = (EditText) findViewById(R.id.et3);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = mEt1.getText().toString();
        String pass = mEt2.getText().toString();
        String cpass = mEt3.getText().toString();

        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                if (name !=null&&pass.equals(cpass)){
                    Shuju shuju = new Shuju(null, name, pass);
                    shujuDao.insert(shuju);
                }
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
        }
    }
}
