package com.wd.tech.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.data.adapter.AddFriendAdapter;
import com.wd.tech.data.adapter.AddGroupAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.AddFriendContract;
import com.wd.tech.di.presenter.AddFriendPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFriendActivity extends BaseActivity implements AddFriendContract.AddFriendView {


    @BindView(R.id.addFriendActivity_back)
    ImageView addFriendActivityBack;
    @BindView(R.id.addFriendActivity_findPersonText)
    TextView addFriendActivityFindPersonText;
    @BindView(R.id.addFriendActivity_findGroupText)
    TextView addFriendActivityFindGroupText;
    @BindView(R.id.addFriendActivity_findPersonlogo)
    TextView addFriendActivityFindPersonlogo;
    @BindView(R.id.addFriendActivity_findGrouplogo)
    TextView addFriendActivityFindGrouplogo;
    @BindView(R.id.addFriendActivity_searchImg)
    ImageView addFriendActivitySearchImg;
    @BindView(R.id.addFriendActivity_findPersonEdit)
    EditText addFriendActivityFindPersonEdit;
    @BindView(R.id.addFriendActivity_notSelResult)
    TextView addFriendActivityNotSelResult;
    @BindView(R.id.addFriendActivity_recyclerView)
    RecyclerView addFriendActivityRecyclerView;
    @BindView(R.id.addFriendActivity_findPersonLinear)
    LinearLayout addFriendActivityFindPersonLinear;
    @BindView(R.id.addFriendActivity_searchImg2)
    ImageView addFriendActivitySearchImg2;
    @BindView(R.id.addFriendActivity_findGroupEdit)
    EditText addFriendActivityFindGroupEdit;
    @BindView(R.id.addFriendActivity_notSelResultGroup)
    TextView addFriendActivityNotSelResultGroup;
    @BindView(R.id.addgroup_recyclerView)
    RecyclerView addgroupRecyclerView;
    @BindView(R.id.addFriendActivity_findGroupLinear)
    LinearLayout addFriendActivityFindGroupLinear;
    private AddFriendPresenter addFriendPresenter;
    private int userId;
    private String sessionId;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //实例化p层
        addFriendPresenter = new AddFriendPresenter();
        addFriendPresenter.attahView(this);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();

    }

    @Override
    protected void initListener() {
        //点击返回
        addFriendActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击找群
        addFriendActivityFindGroupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //找群标志显示 找人标致隐藏
                addFriendActivityFindGrouplogo.setVisibility(View.VISIBLE);
                addFriendActivityFindPersonlogo.setVisibility(View.GONE);
                //找群内容显示 找人内容隐藏
                addFriendActivityFindGroupLinear.setVisibility(View.VISIBLE);
                addFriendActivityFindPersonLinear.setVisibility(View.GONE);
            }
        });
        //点击找人
        addFriendActivityFindPersonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //找群标志隐藏 找人标致显示
                addFriendActivityFindGrouplogo.setVisibility(View.GONE);
                addFriendActivityFindPersonlogo.setVisibility(View.VISIBLE);
                //找群内容隐藏 找人内容显示
                addFriendActivityFindGroupLinear.setVisibility(View.GONE);
                addFriendActivityFindPersonLinear.setVisibility(View.VISIBLE);
            }
        });
        //点击找人输入框
        addFriendActivityFindPersonEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count<11 || count>11){
                    //如果手机号 大于或小于11 显示无结果
                    addFriendActivityNotSelResult.setVisibility(View.VISIBLE);
                    addFriendActivityRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11){
                    //如果 等于 11位 获取输入的值
                    addFriendActivityNotSelResult.setVisibility(View.GONE);
                    String phone = addFriendActivityFindPersonEdit.getText().toString();
                    //请求接口
                    addFriendPresenter.requestData(userId,sessionId,phone);
                }
            }
        });

        //点击找群输入框
        addFriendActivityFindGroupEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after < 1){
                    addFriendActivityNotSelResult.setVisibility(View.VISIBLE);
                    addgroupRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1 || s.length() == 2){
                    addFriendActivityNotSelResult.setVisibility(View.GONE);
                    String editgroup = addFriendActivityFindGroupEdit.getText().toString();
                    int groupId = Integer.parseInt(editgroup);
                    addFriendPresenter.requestAddGroupData(userId,sessionId,groupId);
                }
            }
        });

    }

    //根据手机号查询好友回调
    @Override
    public void showAddFriendData(final AddFriendBean addFriendBean) {

        addFriendActivityRecyclerView.setVisibility(View.VISIBLE);
        AddFriendBean.ResultBean result = addFriendBean.getResult();
        Log.i("zcq_QueryFriendByPhone", "根据手机号查询用户: " + addFriendBean.getMessage());
        final List<AddFriendBean> list = new ArrayList<>();
        //添加数据到集合中
        list.clear();
        list.add(addFriendBean);
        //设置适配器
        AddFriendAdapter addFriendAdapter = new AddFriendAdapter(R.layout.addfriend_item_layout,list);
        addFriendActivityRecyclerView.setAdapter(addFriendAdapter);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        addFriendActivityRecyclerView.setLayoutManager(linearLayoutManager);
        //点击好友条目
        addFriendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AddFriendActivity.this, FriendMessageActivity.class);
                intent.putExtra("email",list.get(position).getResult().getEmail());
                intent.putExtra("headPic",list.get(position).getResult().getHeadPic());
                intent.putExtra("integral",list.get(position).getResult().getIntegral());
                intent.putExtra("nickName",list.get(position).getResult().getNickName());
                intent.putExtra("phone",list.get(position).getResult().getPhone());
                intent.putExtra("sex",list.get(position).getResult().getSex());
                intent.putExtra("signature",list.get(position).getResult().getSignature());
                intent.putExtra("userId",list.get(position).getResult().getUserId());
                intent.putExtra("whetherVip",list.get(position).getResult().getWhetherVip());
                intent.putExtra("whetherFaceId",list.get(position).getResult().getWhetherFaceId());
                startActivity(intent);
            }
        });
    }
    //根据群号查询的群组
    @Override
    public void showAddGroupData(AddGroupBean addGroupBean) {
        //显示群聊recyclerview
        addgroupRecyclerView.setVisibility(View.VISIBLE);
        Log.i("zcq_QueryGroupByPhone", "根据手机号查询群聊: " + addGroupBean.getMessage());
        List<AddGroupBean> list = new ArrayList<>();
        //添加数据到集合中
        list.clear();
        list.add(addGroupBean);
        //设置适配器
        AddGroupAdapter addGroupAdapter = new AddGroupAdapter(R.layout.addgroup_item_layout,list);
        addgroupRecyclerView.setAdapter(addGroupAdapter);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        addgroupRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addFriendPresenter.deachView(this);
    }
}
