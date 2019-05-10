package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:46
 * @fileName:BaseActivity
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.activity
 */
public abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        //沉浸式状态栏
        ImmersionBar.with(this).init();
        //绑定布局
        ButterKnife.bind(this);
        initData();
        initListener();
    }


    //初始化布局
    protected abstract int initLayout();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();

    @Override
    protected void onStart() {
        super.onStart();
        //沉浸式状态栏
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
