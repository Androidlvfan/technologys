package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.adapter.FriendNotifyAdapter;
import com.wd.tech.data.adapter.GroupNotifyAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.FriendNotifyContract;
import com.wd.tech.di.presenter.FriendNotifyPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendNotifyActivity extends BaseActivity implements FriendNotifyContract.FriendNotifyView {


    @BindView(R.id.friend_notify_back)
    ImageView friendNotifyBack;
    @BindView(R.id.friend_notify_recy)
    RecyclerView friendNotifyRecy;
    private int userId;
    private String sessionId;
    private int page = 1;
    private int count =10;
    private FriendNotifyPresenter friendNotifyPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_friend_notify;
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
        friendNotifyPresenter = new FriendNotifyPresenter();
        friendNotifyPresenter.attahView(this);
        friendNotifyPresenter.requestFriendNotifyData(userId,sessionId,page,count);

    }

    @Override
    protected void initListener() {
        //点击返回按钮
        friendNotifyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //好友通知记录
    @Override
    public void showFriendNotifyData(FriendNotifyBean friendNotifyBean) {
        List<FriendNotifyBean.ResultBean> result = friendNotifyBean.getResult();
        FriendNotifyAdapter friendNotifyAdapter = new FriendNotifyAdapter(R.layout.friendnotify_item_layout,result);
        friendNotifyRecy.setAdapter(friendNotifyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        friendNotifyRecy.setLayoutManager(linearLayoutManager);
        //接口回调判断同意还是拒绝
        friendNotifyAdapter.setOnArgeeClickListener(new GroupNotifyAdapter.OnArgeeClickListener() {
            @Override
            public void onCallBack(int i, int notiftyId) {
                switch (i){
                    //同意
                    case 1:
                        friendNotifyPresenter.requestReviewFriendData(userId,sessionId,notiftyId,1);
                        break;
                        //拒绝
                    case 2:
                        friendNotifyPresenter.requestReviewFriendData(userId,sessionId,notiftyId,2);
                        break;
                }
            }
        });
    }
    //审核好友通知的成功回调
    @Override
    public void showReviewFriendData(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")){
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            friendNotifyPresenter.requestFriendNotifyData(userId,sessionId,page,count);
        }
    }
}
