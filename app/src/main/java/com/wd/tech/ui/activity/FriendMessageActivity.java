package com.wd.tech.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.di.contract.FriendMessageContract;
import com.wd.tech.di.presenter.FriendMessagePresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendMessageActivity extends BaseActivity implements FriendMessageContract.FriendMessageView {


    @BindView(R.id.showFriendMessageActivity_headImg)
    ImageView showFriendMessageActivityHeadImg;
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

    private int userId;
    private String sessionId;
    private FriendMessagePresenter friendMessagePresenter;
    private QueryFriendMessageBean.ResultBean result;

    @Override
    protected int initLayout() {
        return R.layout.activity_friend_message;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
//        //接收值
        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
//        String nickName = intent.getStringExtra("nickName");
//        String headPic = intent.getStringExtra("headPic");
//        String phone = intent.getStringExtra("phone");
//        int sex = intent.getIntExtra("sex",0);
//        String signature = intent.getStringExtra("signature");
//        int integral = intent.getIntExtra("integral", 1);
//        int whetherVip = intent.getIntExtra("whetherVip", 3);
        int friend = intent.getIntExtra("userId", 0);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化p层
        friendMessagePresenter = new FriendMessagePresenter();
        friendMessagePresenter.attahView(this);
        //请求数据
        friendMessagePresenter.requestData(userId,sessionId,friend);
        friendMessagePresenter.requestCheckFriendData(userId,sessionId,friend);
    }


    @Override
    protected void initListener() {
        //点击添加好友按钮
        showFriendMessageActivityAddFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendMessageActivity.this, AddIngFriendActivity.class);
                intent.putExtra("headPic",result.getHeadPic());
                intent.putExtra("nickName",result.getNickName());
                intent.putExtra("Signature",result.getSignature());
                intent.putExtra("userId",result.getUserId());
                startActivity(intent);
            }
        });
        //点击发送消息
        showFriendMessageActivitySendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendMessageActivity.this, FriendChatActivity.class);
                intent.putExtra("friendName",result.getNickName());
                intent.putExtra("userId",result.getUserId());
                startActivity(intent);
            }
        });
    }

    //好友信息的回调
    @Override
    public void showData(QueryFriendMessageBean queryFriendMessageBean) {
        Log.d("FriendMessageActivity", "查询好友的信息" + queryFriendMessageBean.getMessage());
        result = queryFriendMessageBean.getResult();
        //邮箱
        showFriendMessageActivityEmail.setText(result.getEmail());
        //头像
//        Uri uri = Uri.parse(result.getHeadPic());
//        DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(uri).build();
//        showFriendMessageActivityHeadImg.setController(controller);
        Glide.with(this).load(result.getHeadPic()).into(showFriendMessageActivityHeadImg);
        //名字
        showFriendMessageActivityName.setText(result.getNickName());
        //电话
        showFriendMessageActivityPhone.setText(result.getPhone());
        if (result.getSignature() == null){
            showFriendMessageActivityQianMing.setText("这个用户很懒,没有留下签名");
            return;
        }
        //签名
        showFriendMessageActivityQianMing.setText(result.getSignature());
        //积分
        showFriendMessageActivityScore.setText("("+ result.getIntegral()+"积分)");
        //性别
        if (result.getSex() == 1){
            showFriendMessageActivitySexBirthday.setText("男");
        }else {
            showFriendMessageActivitySexBirthday.setText("女");
        }

    }

    //检测是否为好友的回调
    @Override
    public void showCheckFriendData(CheckMyFriendBean checkMyFriendBean) {
        Toast.makeText(this, checkMyFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (checkMyFriendBean.getFlag() == 1){
            showFriendMessageActivitySendMessageBtn.setVisibility(View.VISIBLE);
            showFriendMessageActivityAddFriendBtn.setVisibility(View.GONE);
        }else{
            showFriendMessageActivityAddFriendBtn.setVisibility(View.VISIBLE);
            showFriendMessageActivitySendMessageBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUpdBeiZhuData(AddIngFriendBean addIngFriendBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        friendMessagePresenter.deachView(this);
    }
}
