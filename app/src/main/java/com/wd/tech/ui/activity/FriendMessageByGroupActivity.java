package com.wd.tech.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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

public class FriendMessageByGroupActivity extends BaseActivity implements FriendMessageContract.FriendMessageView {


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

    @BindView(R.id.showFriendMessageActivity_moreBtn)
    ImageView showFriendMessageActivityMoreBtn;
    @BindView(R.id.showFriendMessageActivity_headImg)
    SimpleDraweeView showFriendMessageActivityHeadImg;
    @BindView(R.id.showFriendMessageActivity_sendMessage)
    Button showFriendMessageActivitySendMessage;

    private int userId;
    private String sessionId;
    private FriendMessagePresenter friendMessagePresenter;
    private int friendId;
    private View view1;
    private View inflate;
    private QueryFriendMessageBean.ResultBean result;

    @Override
    protected int initLayout() {
        return R.layout.activity_friend_message_by_group;
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
        friendId = intent.getIntExtra("friendId", 0);
        Log.d("FriendMessageByGroupAct", "friendId:" + friendId);
        //实例化p层
        friendMessagePresenter = new FriendMessagePresenter();
        friendMessagePresenter.attahView(this);
        friendMessagePresenter.requestData(userId, sessionId, friendId);
    }

    @Override
    protected void initListener() {


        //点击三点按钮
        showFriendMessageActivityMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflate = LayoutInflater.from(FriendMessageByGroupActivity.this).inflate(R.layout.friendmessage_pop_layout, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(showFriendMessageActivityMoreBtn);
                TextView pop_updbeizhu = inflate.findViewById(R.id.pop_upd_beizhu);
                TextView moveGroup = inflate.findViewById(R.id.popMove_moveGroup);
                TextView liaotianjilu = inflate.findViewById(R.id.liaotianjilu);
                //修改备注
                pop_updbeizhu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(FriendMessageByGroupActivity.this);
                        builder.setTitle("修改备注");
                        view1 = LayoutInflater.from(FriendMessageByGroupActivity.this).inflate(R.layout.group_edit_layout, null);
                        final EditText editText = view1.findViewById(R.id.personal_edit);
                        builder.setView(view1);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String beizhu = editText.getText().toString();
                                if (beizhu.equals("")) {
                                    Toast.makeText(FriendMessageByGroupActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                                } else {
                                    friendMessagePresenter.requestUpdBeiZhuData(userId, sessionId, friendId, beizhu);
                                }
                            }
                        });
                        //点击取消
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //显示弹框
                        builder.show();
                    }
                });
                //点击移动分组
                moveGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FriendMessageByGroupActivity.this, CheckGroupActivity.class);
                        intent.putExtra("friendId", friendId);
                        startActivity(intent);
                    }
                });
                //点击聊天记录
                liaotianjilu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FriendMessageByGroupActivity.this, ChatRecordsActivity.class);
                        intent.putExtra("friendId", friendId);
                        startActivity(intent);
                    }
                });

            }
        });
        //点击发消息
        showFriendMessageActivitySendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendMessageByGroupActivity.this, FriendChatActivity.class);
                intent.putExtra("userId", result.getUserId());
                intent.putExtra("friendName", result.getNickName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(QueryFriendMessageBean queryFriendMessageBean) {
        result = queryFriendMessageBean.getResult();
        //邮箱
        showFriendMessageActivityEmail.setText(result.getEmail());
        //头像
        showFriendMessageActivityHeadImg.setImageURI(result.getHeadPic());
        //名字
        showFriendMessageActivityName.setText(result.getNickName());
        //电话
        showFriendMessageActivityPhone.setText(result.getPhone());
        if (result.getSignature() == null) {
            showFriendMessageActivityQianMing.setText("这个用户很懒,没有留下签名");
        }
        //签名
        showFriendMessageActivityQianMing.setText(result.getSignature());
        //积分
        showFriendMessageActivityScore.setText("(" + result.getIntegral() + "积分)");
        //性别
        if (result.getSex() == 1) {
            showFriendMessageActivitySexBirthday.setText("男");
        } else {
            showFriendMessageActivitySexBirthday.setText("女");
        }
    }

    @Override
    public void showCheckFriendData(CheckMyFriendBean checkMyFriendBean) {

    }

    @Override
    public void showUpdBeiZhuData(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")) {
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            //让软键盘消失
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(inflate.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        friendMessagePresenter.deachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
