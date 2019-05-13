package com.wd.tech.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.wd.tech.R;
import com.wd.tech.ui.activity.AddFriendActivity;
import com.wd.tech.ui.activity.CreateGroupActivity;

import java.io.PipedOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:54
 * @fileName:MessageFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public class MessageFragment extends BaseFragment {


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

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_message_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        //绑定布局
        unbinder = ButterKnife.bind(this,mRootView);
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


}
