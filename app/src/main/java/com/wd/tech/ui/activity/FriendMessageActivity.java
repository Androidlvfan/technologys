package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendMessageActivity extends BaseActivity {


    @BindView(R.id.showFriendMessageActivity_headImg)
    SimpleDraweeView showFriendMessageActivityHeadImg;
    @BindView(R.id.showFriendMessageActivity_name)
    TextView showFriendMessageActivityName;
    @BindView(R.id.showFriendMessageActivity_score)
    TextView showFriendMessageActivityScore;
    @BindView(R.id.showFriendMessageActivity_qianMing)
    TextView showFriendMessageActivityQianMing;
    @BindView(R.id.gerenziliao_tv)
    TextView gerenziliaoTv;
    @BindView(R.id.showFriendMessageActivity_sexBirthday)
    TextView showFriendMessageActivitySexBirthday;
    @BindView(R.id.showFriendMessageActivity_phone)
    TextView showFriendMessageActivityPhone;
    @BindView(R.id.showFriendMessageActivity_email)
    TextView showFriendMessageActivityEmail;
    @BindView(R.id.showFriendMessageActivity_addFriendBtn)
    Button showFriendMessageActivityAddFriendBtn;
    @BindView(R.id.showFriendMessageActivity_sendMessageBtn)
    Button showFriendMessageActivitySendMessageBtn;

    @Override
    protected int initLayout() {
        return R.layout.activity_friend_message;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }


}
