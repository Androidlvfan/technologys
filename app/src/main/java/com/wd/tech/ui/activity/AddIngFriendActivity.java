package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.AddIngFriendContract;
import com.wd.tech.di.presenter.AddIngFriendPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddIngFriendActivity extends BaseActivity implements AddIngFriendContract.AddIngFriendView {


    @BindView(R.id.addingFriendActivity_BackImg)
    ImageView addingFriendActivityBackImg;
    @BindView(R.id.addingFriendActivity_sendBtn)
    TextView addingFriendActivitySendBtn;
    @BindView(R.id.addingFriendActivity_headImg)
    SimpleDraweeView addingFriendActivityHeadImg;
    @BindView(R.id.addingFriendActivity_nickName)
    TextView addingFriendActivityNickName;
    @BindView(R.id.addingFriendActivity_qianMing)
    TextView addingFriendActivityQianMing;
    @BindView(R.id.addingFriendActivity_remarksEdit)
    EditText addingFriendActivityRemarksEdit;
    private AddIngFriendPresenter addIngFriendPresenter;
    private int userId;
    private String sessionId;
    private int friendId;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_ing_friend;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //接收值
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String headPic = intent.getStringExtra("headPic");
        String signature = intent.getStringExtra("Signature");
        friendId = intent.getIntExtra("userId", 0);
        //头像
        addingFriendActivityHeadImg.setImageURI(headPic);
        //名字
        addingFriendActivityNickName.setText(nickName);
        //签名
        addingFriendActivityQianMing.setText(signature);
        //实例化p层
        addIngFriendPresenter = new AddIngFriendPresenter();
        addIngFriendPresenter.attahView(this);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
    }

    @Override
    protected void initListener() {
        //点击返回按钮
        addingFriendActivityBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击发送
        addingFriendActivitySendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editRemarks = addingFriendActivityRemarksEdit.getText().toString();
                addIngFriendPresenter.requestData(userId,sessionId,friendId,editRemarks);
            }
        });
    }

    @Override
    public void showData(AddIngFriendBean addIngFriendBean) {
        Log.d("AddIngFriendActivity", addIngFriendBean.getMessage());
        Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
        //让软键盘消失
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(addingFriendActivityRemarksEdit.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addIngFriendPresenter.deachView(this);
    }
}
