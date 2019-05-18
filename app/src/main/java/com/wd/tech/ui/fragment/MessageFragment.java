package com.wd.tech.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.wd.tech.R;
import com.wd.tech.data.adapter.FriendGroupAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.FriendGroupContract;
import com.wd.tech.di.presenter.FriendGroupPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.AddFriendActivity;
import com.wd.tech.ui.activity.CreateGroupActivity;
import com.wd.tech.ui.activity.FriendNotifyActivity;
import com.wd.tech.ui.activity.GroupActivity;
import com.wd.tech.ui.activity.GroupNotifyActivity;

import java.io.PipedOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:54
 * @fileName:MessageFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public class MessageFragment extends BaseFragment implements FriendGroupContract.FriendGroupView {


    @BindView(R.id.fragmentMessageRelativeLayout_messageBtn)
    CheckBox fragmentMessageRelativeLayoutMessageBtn;
    @BindView(R.id.fragmentMessageRelativeLayout_personBtn)
    CheckBox fragmentMessageRelativeLayoutPersonBtn;
    @BindView(R.id.fragmentMessageRelativeLayout_showPopBtn)
    ImageView fragmentMessageRelativeLayoutShowPopBtn;
    @BindView(R.id.fragmentMessageRelativeLayout_head)
    RelativeLayout fragmentMessageRelativeLayoutHead;
    @BindView(R.id.fragmentMessageRelativeLayout_recyclerview)
    RecyclerView fragmentMessageRelativeLayoutRecyclerview;
    @BindView(R.id.fragmentMessageRelativeLayout_message)
    RelativeLayout fragmentMessageRelativeLayoutMessage;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.fragmentMessageRelativeLayout_seachRelative)
    RelativeLayout fragmentMessageRelativeLayoutSeachRelative;
    @BindView(R.id.fragmentMessageRelativeLayout_friendNotify)
    LinearLayout fragmentMessageRelativeLayoutFriendNotify;
    @BindView(R.id.fragmentMessageRelativeLayout_groupNotify)
    LinearLayout fragmentMessageRelativeLayoutGroupNotify;
    @BindView(R.id.fragmentMessageRelativeLayout_groupIcon)
    LinearLayout fragmentMessageRelativeLayoutGroupIcon;
    @BindView(R.id.fragmentMessageRelativeLayout_groupDoubleList)
    RecyclerView fragmentMessageRelativeLayoutGroupDoubleList;
    @BindView(R.id.fragmentMessageRelativeLayout_person)
    RelativeLayout fragmentMessageRelativeLayoutPerson;
    Unbinder unbinder;
    private LinearLayout addperson;
    private LinearLayout creadTalk;
    private View view;
    private int userId;
    private String sessionId;
    private FriendGroupPresenter friendGroupPresenter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_message_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        //绑定布局
        unbinder = ButterKnife.bind(this,mRootView);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化分组p层
        friendGroupPresenter = new FriendGroupPresenter();
        friendGroupPresenter.attahView(this);
        friendGroupPresenter.requestData(userId,sessionId);
        //点击消息和联系人改变按钮状态
        changeMessagePersonListener();
        //创建pop布局
        view = LayoutInflater.from(getContext()).inflate(R.layout.message_popview, null);
        //pop里的控件
        addperson = view.findViewById(R.id.messagePopView_addPersonOrGroup);
        creadTalk = view.findViewById(R.id.messagePopView_creadTalkGroup);
        //点击加号弹出pop 加好友
        fragmentMessageRelativeLayoutShowPopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                //显示pop
                popupWindow.showAsDropDown(fragmentMessageRelativeLayoutShowPopBtn);
            }
        });

        //点击pop里的加好友
        addperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFriendActivity.class);
                startActivity(intent);
            }
        });
        //点击创建群聊
        creadTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateGroupActivity.class);
                startActivity(intent);
            }
        });
        //点击群组
        fragmentMessageRelativeLayoutGroupIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupActivity.class);
                startActivity(intent);
            }
        });
        //点击群组通知
        fragmentMessageRelativeLayoutGroupNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupNotifyActivity.class);
                startActivity(intent);
            }
        });
        //点击好友通知
        fragmentMessageRelativeLayoutFriendNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FriendNotifyActivity.class);
                startActivity(intent);
            }
        });


    }

    private void changeMessagePersonListener() {
        fragmentMessageRelativeLayoutMessageBtn.setChecked(true);
        fragmentMessageRelativeLayoutPersonBtn.setChecked(false);
        fragmentMessageRelativeLayoutMessageBtn.setTextColor(Color.WHITE);
        fragmentMessageRelativeLayoutPersonBtn.setTextColor(Color.BLACK);
        fragmentMessageRelativeLayoutMessage.setVisibility(View.VISIBLE);
        fragmentMessageRelativeLayoutPerson.setVisibility(View.GONE);

        fragmentMessageRelativeLayoutMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentMessageRelativeLayoutMessageBtn.setChecked(true);
                fragmentMessageRelativeLayoutPersonBtn.setChecked(false);
                fragmentMessageRelativeLayoutMessageBtn.setTextColor(Color.WHITE);
                fragmentMessageRelativeLayoutPersonBtn.setTextColor(Color.BLACK);
                fragmentMessageRelativeLayoutMessage.setVisibility(View.VISIBLE);
                fragmentMessageRelativeLayoutPerson.setVisibility(View.GONE);
            }
        });

        fragmentMessageRelativeLayoutPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentMessageRelativeLayoutMessageBtn.setChecked(false);
                fragmentMessageRelativeLayoutPersonBtn.setChecked(true);
                fragmentMessageRelativeLayoutMessageBtn.setTextColor(Color.BLACK);
                fragmentMessageRelativeLayoutPersonBtn.setTextColor(Color.WHITE);
                fragmentMessageRelativeLayoutMessage.setVisibility(View.GONE);
                fragmentMessageRelativeLayoutPerson.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //二级列表
    @Override
    public void showData(FriendGroupBean friendGroupBean) {
        List<FriendGroupBean.ResultBean> result = friendGroupBean.getResult();
        FriendGroupAdapter friendGroupAdapter = new FriendGroupAdapter(R.layout.friendgroup_item_layout, result);
        fragmentMessageRelativeLayoutGroupDoubleList.setAdapter(friendGroupAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragmentMessageRelativeLayoutGroupDoubleList.setLayoutManager(linearLayoutManager);
        //条目点击
        friendGroupAdapter.setOnAddGroupClickListener(new FriendGroupAdapter.OnAddGroupClickListener() {
            //实时刷新添加分组
            @Override
            public void onAdd() {
                friendGroupPresenter.requestData(userId,sessionId);

            }
            //实时刷新修改分组
            @Override
            public void onUpd() {
                friendGroupPresenter.requestData(userId,sessionId);
            }
            //实时刷新删除分组
            @Override
            public void onDel() {
                friendGroupPresenter.requestData(userId,sessionId);
            }
            //实时刷新删除好友
            @Override
            public void onDelFriend() {
                friendGroupPresenter.requestData(userId,sessionId);
            }
            //实时刷新移动好友到黑名单
            @Override
            public void moveFriend() {
                friendGroupPresenter.requestData(userId,sessionId);
            }
        });
    }

    @Override
    public void showLaHeiFriendData(AddIngFriendBean addIngFriendBean) {

    }

    @Override
    public void onResume() {
        super.onResume();
        friendGroupPresenter.requestData(userId,sessionId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        friendGroupPresenter.deachView(this);
    }
}
