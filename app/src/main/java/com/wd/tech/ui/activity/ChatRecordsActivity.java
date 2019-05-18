package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.data.adapter.ChatRecordsAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.ChatRecordBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.ChatRecordContract;
import com.wd.tech.di.presenter.ChatRecordPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRecordsActivity extends BaseActivity implements ChatRecordContract.ChatRecordView {


    @BindView(R.id.imt_back)
    ImageView imtBack;
    @BindView(R.id.recycler_view_ltjl)
    RecyclerView recyclerViewLtjl;

    private int userId;
    private String sessionId;

    private int page = 1;
    private int count = 10;
    private ChatRecordPresenter chatRecordPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_chat_records;
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
        //接收值
        Intent intent = getIntent();
        int friendId = intent.getIntExtra("friendId", 0);
        //实例化p层
        chatRecordPresenter = new ChatRecordPresenter();
        chatRecordPresenter.attahView(this);
        chatRecordPresenter.requestData(userId,sessionId,friendId,page,count);
    }

    @Override
    protected void initListener() {
        //点击返回
        imtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //聊天记录成功回调
    @Override
    public void showData(ChatRecordBean chatRecordBean) {
        List<ChatRecordBean.ResultBean> result = chatRecordBean.getResult();
        ChatRecordsAdapter chatRecordsAdapter = new ChatRecordsAdapter(R.layout.chatrecord_item_layout,result);
        recyclerViewLtjl.setAdapter(chatRecordsAdapter);
        recyclerViewLtjl.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatRecordPresenter.deachView(this);
    }
}
