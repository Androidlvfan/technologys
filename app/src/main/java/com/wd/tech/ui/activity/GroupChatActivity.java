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
import com.wd.tech.data.adapter.GroupChatAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.GroupChatRecordBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.di.contract.GroupChatContract.GroupChatView;
import com.wd.tech.di.presenter.GroupChatPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;

public class GroupChatActivity extends BaseActivity implements GroupChatView {


    @BindView(R.id.chatActivity_friendName)
    TextView chatActivityFriendName;
    @BindView(R.id.groupChatActivity_moreBtn)
    ImageView groupChatActivityMoreBtn;
    @BindView(R.id.chatActivity_reative)
    RelativeLayout chatActivityReative;
    @BindView(R.id.chat_Recycler)
    RecyclerView chatRecycler;
    @BindView(R.id.chat_input)
    ChatInputView chatInput;
    @BindView(R.id.groupchat_back)
    ImageView groupchatBack;
    private int userId;
    private String sessionId;
    private GroupChatPresenter groupChatPresenter;
    private int groupId;
    private int page = 1;
    private int count = 10;

    @Override
    protected int initLayout() {
        return R.layout.activity_group_chat;
    }

    @Override
    protected void initData() {

        //绑定布局
        ButterKnife.bind(this);
        //接收值
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", 0);
        String groupImg = intent.getStringExtra("groupImg");
        String groupName = intent.getStringExtra("groupName");
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化p层
        groupChatPresenter = new GroupChatPresenter();
        groupChatPresenter.attahView(this);
        groupChatPresenter.requestGroupChatData(userId, sessionId, groupId, page, count);
    }

    @Override
    protected void initListener() {
        //发送消息
        chatInput.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                // 输入框输入文字后，点击发送按钮事件
                try {
                    String encrypt = RsaCoder.encryptByPublicKey(input.toString());
                    groupChatPresenter.requestSendGroupMsg(userId, sessionId, groupId, encrypt);
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

        //点击返回
        groupchatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void sendGroupMsg(AddIngFriendBean addIngFriendBean) {
        if (addIngFriendBean.getStatus().equals("0000")) {
            Toast.makeText(this, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
            groupChatPresenter.requestGroupChatData(userId, sessionId, groupId, page, count);
        }
    }

    @Override
    public void groupChatData(GroupChatRecordBean groupChatRecordBean) {
        List<GroupChatRecordBean.ResultBean> result = groupChatRecordBean.getResult();
        Collections.reverse(result);
        GroupChatAdapter groupChatAdapter = new GroupChatAdapter(result, GroupChatActivity.this);
        chatRecycler.setAdapter(groupChatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        groupChatPresenter.deachView(this);
    }


}
