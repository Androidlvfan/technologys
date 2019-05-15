package com.wd.tech.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.wd.tech.R;

import butterknife.ButterKnife;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:46
 * @fileName:BaseActivity
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.activity
 */
public abstract class BaseActivity extends AppCompatActivity {


//    /**
//     * 记录处于前台的Activity
//     */
//    private static BaseActivity mForegroundActivity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
       // 沉浸式状态栏   //statusBarDarkFont  原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
       ImmersionBar.with(this).statusBarDarkFont(true, 0.8f) //解决软键盘与底部输入框冲突问题
                .init();
        //绑定布局
        ButterKnife.bind(this);



        initData();
        initListener();
        stateNetWork();
    }
//    /**
//     * 生成一个和状态栏大小相同的矩形条
//     * @param activity 需要设置的activity
//     * @return 状态栏矩形条
//     */
//    private static View createStatusView(Activity activity) {
//        // 获得状态栏的高度
//        int resourceId = activity.getResources()
//                .getIdentifier("status_bar_height","dimen","android");
//        int statusBarHeight = activity.getResources()
//                .getDimensionPixelSize(resourceId);
//
//        // 绘制一个和状态栏一样高度的矩形
//        View statusView = new View(activity);
//        LinearLayout.LayoutParams params = new LinearLayout
//                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusBarHeight);
//        statusView.setLayoutParams(params);
//
//        return statusView;
//    }
//    public void setColor(Activity activity){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            // 设置状态栏透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 生成一个状态栏大小的矩形
//            View StatusView = createStatusView(activity);
//            // 添加statusView到布局中
//            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//            decorView.addView(StatusView);
//            // 设置根布局的参数
//            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content))
//                    .getChildAt(0);
//            rootView.setFitsSystemWindows(true);
//            rootView.setClipToPadding(true);
//        }
//    }
//
//    //透明式沉浸式状态栏
//    public void setImmersive(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//    }

//    /**
//     * 获取当前处于前台的activity
//     */
//    public static BaseActivity getForegroundActivity() {
//        return mForegroundActivity;
//    }

    //初始化布局
    protected abstract int initLayout();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();

    @Override
    protected void onStart() {
        super.onStart();
//        mForegroundActivity = this;
       // 沉浸式状态栏
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f). //解决软键盘与底部输入框冲突问题
        init();
    }
    private void stateNetWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mStatenetwork = new String[]{
                    //写的权限
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    //读的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    //入网权限
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    //WIFI权限
                    Manifest.permission.ACCESS_WIFI_STATE,
                    //读手机权限
                    Manifest.permission.READ_PHONE_STATE,
                    //网络权限
                    Manifest.permission.INTERNET,
//                    //相机
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.WRITE_APN_SETTINGS,
//                    Manifest.permission.ACCESS_NETWORK_STATE,
//                    //定位
//                    Manifest.permission.LOCATION_HARDWARE,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            };
            ActivityCompat.requestPermissions(this, mStatenetwork, 100);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
