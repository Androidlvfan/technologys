package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class AddIngGroupActivity extends BaseActivity implements CheckInGroupContract.CheckInGroupView {


    @BindView(R.id.want_add_group_back)
    ImageView wantAddGroupBack;
    @BindView(R.id.want_add_group_send)
    TextView wantAddGroupSend;
    @BindView(R.id.want_add_group_message)
    EditText wantAddGroupMessage;
    @BindView(R.id.want_add_group_num)
    TextView wantAddGroupNum;
    private int userId;
    private String sessionId;
    private CheckInGroupPresenter checkInGroupPresenter;
    private int groupId;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_ing_group;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //接收值
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", 0);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化p层
        checkInGroupPresenter = new CheckInGroupPresenter();
        checkInGroupPresenter.attahView(this);

    }

    @Override
    protected void initListener() {
        //点击返回按钮
        wantAddGroupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击发送
        wantAddGroupSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = wantAddGroupMessage.getText().toString();
                //请求数据
                checkInGroupPresenter.requestAddIngGroupData(userId,sessionId,groupId,message);
            }
        });
    }


    @Override
    public void showCheckInGroupData(CheckMyFriendBean checkMyFriendBean) {

    }

    @Override
    public void showAddIngGroupData(AddIngFriendBean addIngFriendBean) {
        Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
        //让软键盘消失
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(wantAddGroupMessage.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkInGroupPresenter.deachView(this);
    }
}
