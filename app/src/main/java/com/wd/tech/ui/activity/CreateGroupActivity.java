package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.CreateGroupBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.CreateGroupContract;
import com.wd.tech.di.presenter.CreateGroupPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateGroupActivity extends BaseActivity implements CreateGroupContract.CreateGroupView {


    @BindView(R.id.add_group_back)
    ImageView addGroupBack;
    @BindView(R.id.linearone)
    LinearLayout linearone;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.create_friend_name)
    EditText createFriendName;
    @BindView(R.id.create_friend_details)
    EditText createFriendDetails;
    @BindView(R.id.create_friend_ok)
    Button createFriendOk;
    private int userId;
    private String sessionId;
    private CreateGroupPresenter createGroupPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_create_group;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化p层
        createGroupPresenter = new CreateGroupPresenter();
        createGroupPresenter.attahView(this);
    }

    @Override
    protected void initListener() {
        //点击返回按钮
        addGroupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击创建群
        createFriendOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = createFriendName.getText().toString();
                String details = createFriendDetails.getText().toString();
                //请求数据
                createGroupPresenter.requestData(userId,sessionId,name,details);
            }
        });
    }

    //成功回调
    @Override
    public void showData(CreateGroupBean createGroupBean) {
        if (createGroupBean.getMessage().equals("群名称已存在")){
            Toast.makeText(this, createGroupBean.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, createGroupBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
