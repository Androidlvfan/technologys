package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.wd.tech.R;
import com.wd.tech.data.adapter.FriendChatAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.di.contract.FriendChatContract;
import com.wd.tech.di.presenter.FriendChatPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;

public class FriendChatActivity extends BaseActivity implements FriendChatContract.FriendChatView {


    @BindView(R.id.friendchat_back)
    ImageView friendchatBack;
    @BindView(R.id.chatActivity_friendName)
    TextView chatActivityFriendName;
    @BindView(R.id.friendChatActivity_moreBtn)
    ImageView friendChatActivityMoreBtn;
    @BindView(R.id.chatActivity_reative)
    RelativeLayout chatActivityReative;
    @BindView(R.id.chat_Recycler)
    RecyclerView chatRecycler;
    @BindView(R.id.chat_input)
    ChatInputView chatInput;
    private int userId;
    private String sessionId;
    private int page = 1;
    private int count = 10;
    private int friendId;
    private FriendChatPresenter friendChatPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_friend_chat;
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
        friendId = intent.getIntExtra("userId", 0);
        String friendName = intent.getStringExtra("friendName");
        //名字
        chatActivityFriendName.setText(friendName);
        //实例化p层
        friendChatPresenter = new FriendChatPresenter();
        friendChatPresenter.attahView(this);
        friendChatPresenter.requestFriendChatData(userId,sessionId, friendId,page,count);
    }

    @Override
    protected void initListener() {
        //点击返回
        friendchatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //发送消息
        chatInput.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                // 输入框输入文字后，点击发送按钮事件
                try {
                    String encrypt = RsaCoder.encryptByPublicKey(input.toString());
                    friendChatPresenter.requestSendFriendMsg(userId, sessionId,friendId, encrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {
                // 选中文件或者录制完视频后，点击发送按钮触发此事件
            }

            @Override
            public boolean switchToMicrophoneMode() {
                // 点击语音按钮触发事件，显示录音界面前触发此事件
                // 返回 true 表示使用默认的界面，若返回 false 应该自己实现界面
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                // 点击图片按钮触发事件，显示图片选择界面前触发此事件
                // 返回 true 表示使用默认的界面
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                // 点击拍照按钮触发事件，显示拍照界面前触发此事件
                // 返回 true 表示使用默认的界面
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                // 点击表情按钮触发事件，显示表情界面前触发此事件
                // 返回 true 表示使用默认的界面
                return true;
            }
        });
    }

    //发送消息
    @Override
    public void sendFriendMsg(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")){
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            friendChatPresenter.requestFriendChatData(userId,sessionId, friendId,page,count);
        }
    }
    //查询对话记录
    @Override
    public void FriendChatData(FriendChatRecordBean friendChatRecordBean) {
        List<FriendChatRecordBean.ResultBean> result = friendChatRecordBean.getResult();
        Collections.reverse(result);
        FriendChatAdapter friendChatAdapter = new FriendChatAdapter(result,FriendChatActivity.this);
        chatRecycler.setAdapter(friendChatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatRecycler.setLayoutManager(linearLayoutManager);
    }
}
