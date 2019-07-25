package com.example.app4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 点击跳过
     */
    private Button mBt;
    private ImageView mIv;
    /**
     * 4s
     */
    private TextView mTv;
    private int time=3;

Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.what==11){
            mTv.setText(time--+"");
            if (time<0){
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                finish();
            }else {
                handler.sendEmptyMessageDelayed(11,1000);
            }
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDonghua();
    }

    private void initDonghua() {

        			AnimationSet animationSet = new AnimationSet(true);
        			//可以设置动画集合的执行时间，那么单独的动画执行时间就会变为无效
        			animationSet.setDuration(3000);
        			AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        			alphaAnimation.setDuration(3000);// 设置动画执行时间
        			animationSet.addAnimation(alphaAnimation);
        			RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        			rotateAnimation.setDuration(3000);// 设置动画执行时间
        			animationSet.addAnimation(rotateAnimation);
        			ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        			scaleAnimation.setDuration(3000);// 设置动画执行时间
        			animationSet.addAnimation(scaleAnimation);
        			TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
        					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
        			translateAnimation.setDuration(3000);// 设置动画执行时间
        			animationSet.addAnimation(translateAnimation);
        			mIv.startAnimation(animationSet);
        			handler.sendEmptyMessageDelayed(11,1000);
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
