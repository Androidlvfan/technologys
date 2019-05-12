package com.wd.tech.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechStartActivity extends BaseActivity {


    @BindView(R.id.start_img)
    ImageView startImg;
    @BindView(R.id.start_name)
    TextView startName;
    @BindView(R.id.start_name1)
    TextView startName1;



    @Override
    protected int initLayout() {
        return R.layout.activity_tech_start;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(startImg,"scaleX",0,1);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(startName,"alpha",0,1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(objectAnimator1);
        animatorSet.setDuration(3000);
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TechStartActivity.this,TechLoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

    @Override
    protected void initListener() {

    }


}
