package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.data.adapter.MyImageAdapter;
import com.wd.tech.data.app.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

public class FangActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = FangActivity.class.getSimpleName();
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private TextView mTvSaveImage;
    private List<String> Urls;
    private ArrayList<String> urls;
    private ArrayList<String> aaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fang);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_image_count);

    }

    private void initData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
        aaa = intent.getStringArrayListExtra("image");
        adapter = new MyImageAdapter(aaa, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition+1 + "/" + aaa.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + aaa.size());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_image_count:
                break;
        }
    }
}

