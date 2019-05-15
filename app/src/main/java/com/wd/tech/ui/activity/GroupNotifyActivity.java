package com.wd.tech.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.adapter.GroupNotifyAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.GroupNotifyBean;
import com.wd.tech.di.contract.GroupNotifyContract;
import com.wd.tech.di.presenter.GroupNotifyPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupNotifyActivity extends BaseActivity implements GroupNotifyContract.GroupNotifyView {


    @BindView(R.id.group_notify_back)
    ImageView groupNotifyBack;
    @BindView(R.id.group_notify_recy)
    RecyclerView groupNotifyRecy;
    private int page =1;
    private int count = 10;
    private GroupNotifyPresenter groupNotifyPresenter;
    private int userId;
    private String sessionId;
    @Override
    protected int initLayout() {
        return R.layout.activity_group_notify;
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
        groupNotifyPresenter = new GroupNotifyPresenter();
        groupNotifyPresenter.attahView(this);
        groupNotifyPresenter.requestData(userId,sessionId,page,count);
    }

    @Override
    protected void initListener() {
        //点击返回按钮
        groupNotifyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //群通知的成功回调
    @Override
    public void showData(GroupNotifyBean groupNotifyBean) {
        Log.d("GroupNotifyActivity", groupNotifyBean.getMessage());
        List<GroupNotifyBean.ResultBean> result = groupNotifyBean.getResult();
        GroupNotifyAdapter groupNotifyAdapter = new GroupNotifyAdapter(R.layout.groupnotify_item_layout,result);
        groupNotifyRecy.setAdapter(groupNotifyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        groupNotifyRecy.setLayoutManager(linearLayoutManager);

        groupNotifyAdapter.setOnArgeeClickListener(new GroupNotifyAdapter.OnArgeeClickListener() {
            @Override
            public void onCallBack(int i, int notiftyId) {
                switch (i){
                    //同意
                    case 1:
                        groupNotifyPresenter.requestReviewGroupData(userId,sessionId,notiftyId,1);
                        break;
                        //拒绝
                    case 2:
                        groupNotifyPresenter.requestReviewGroupData(userId,sessionId,notiftyId,2);
                        break;
                }
            }
        });
    }

    //审核群的回调
    @Override
    public void showReviewGrouData(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")){
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            groupNotifyPresenter.requestData(userId, sessionId, page,count);
        }
    }
}
