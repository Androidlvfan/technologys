package com.wd.tech.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.tech.R;
import com.wd.tech.data.utils.NoScrollViewPager;
import com.wd.tech.ui.fragment.CommunityFragment;
import com.wd.tech.ui.fragment.InformatiionFragment;
import com.wd.tech.ui.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TechHomeActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @BindView(R.id.activityHome_zxBtn)
    RadioButton activityHomeZxBtn;
    @BindView(R.id.activityHome_messageBtn)
    RadioButton activityHomeMessageBtn;
    @BindView(R.id.activityHome_groupBtn)
    RadioButton activityHomeGroupBtn;
    @BindView(R.id.activityHome_radioGroup)
    RadioGroup activityHomeRadioGroup;
    private List<Fragment> list;


    @Override
    protected int initLayout() {
        return R.layout.activity_tech_home;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        //创建集合 添加fragment
        list = new ArrayList<>();
        list.add(new InformatiionFragment());
        list.add(new MessageFragment());
        list.add(new CommunityFragment());
        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //禁止滑动
        viewPager.setNoScroll(true);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.activityHome_zxBtn, R.id.activityHome_messageBtn, R.id.activityHome_groupBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activityHome_zxBtn:

                viewPager.setCurrentItem(0);
                break;
            case R.id.activityHome_messageBtn:

                viewPager.setCurrentItem(1);
                break;
            case R.id.activityHome_groupBtn:

                viewPager.setCurrentItem(2);
                break;
        }
    }
}
