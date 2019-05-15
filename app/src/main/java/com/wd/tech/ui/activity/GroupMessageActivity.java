package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.CheckInGroupContract;
import com.wd.tech.di.presenter.CheckInGroupPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupMessageActivity extends BaseActivity implements CheckInGroupContract.CheckInGroupView {


    @BindView(R.id.find_group_details_back)
    ImageView findGroupDetailsBack;
    @BindView(R.id.find_group_details_name)
    TextView findGroupDetailsName;
    @BindView(R.id.find_group_details_icon)
    SimpleDraweeView findGroupDetailsIcon;
    @BindView(R.id.find_group_details_relative)
    RelativeLayout findGroupDetailsRelative;
    @BindView(R.id.find_group_details_num)
    TextView findGroupDetailsNum;
    @BindView(R.id.find_group_details_group)
    RelativeLayout findGroupDetailsGroup;
    @BindView(R.id.find_group_details_description)
    TextView findGroupDetailsDescription;
    @BindView(R.id.find_group_details_yes)
    Button findGroupDetailsYes;
    private int userId;
    private String sessionId;
    private int groupId;
    private int flag;
    private CheckInGroupPresenter checkInGroupPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_group_message;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //接收值
        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        String groupImage = intent.getStringExtra("groupImage");
        groupId = intent.getIntExtra("groupId", 0);
        String description = intent.getStringExtra("description");
        int currentCount = intent.getIntExtra("currentCount", 1);
        //群头像
        findGroupDetailsIcon.setImageURI(groupImage);
        //群名称
        findGroupDetailsName.setText(groupName);
        //群简介
        findGroupDetailsDescription.setText(description);
        //群成员
        findGroupDetailsNum.setText("共"+currentCount+"人");
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化P层
        checkInGroupPresenter = new CheckInGroupPresenter();
        checkInGroupPresenter.attahView(this);
        checkInGroupPresenter.requestData(userId,sessionId, groupId);
    }

    @Override
    protected void initListener() {
        //点击返回按钮
        findGroupDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击申请加群
        findGroupDetailsYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否加入过群
                if (flag == 1){

                }else if (flag == 2){
                    Intent intent = new Intent(GroupMessageActivity.this, AddIngGroupActivity.class);
                    intent.putExtra("groupId",groupId);
                    startActivity(intent);
                }

            }
        });
    }


    @Override
    public void showCheckInGroupData(CheckMyFriendBean checkMyFriendBean) {
        Toast.makeText(this, checkMyFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
        //如果frag=1是群成员 如果=2 不是群成员
        flag = checkMyFriendBean.getFlag();
        if (checkMyFriendBean.getFlag() == 1){
            findGroupDetailsYes.setText("发消息");
        }else if (checkMyFriendBean.getFlag() == 2){
            findGroupDetailsYes.setText("申请加群");
        }
    }

    @Override
    public void showAddIngGroupData(AddIngFriendBean addIngFriendBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkInGroupPresenter.deachView(this);
    }
}
