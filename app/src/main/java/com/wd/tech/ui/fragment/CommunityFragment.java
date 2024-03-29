package com.wd.tech.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.data.adapter.CommunityAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddCommunityBean;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.di.contract.AddCommunityContract;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.contract.LikeContract;
import com.wd.tech.di.contract.NoLikeContract;
import com.wd.tech.di.presenter.AddCommunityPresenter;
import com.wd.tech.di.presenter.CommunityPresenter;
import com.wd.tech.di.presenter.LikePresenter;
import com.wd.tech.di.presenter.NoLikePresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.DealActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:51
 * @fileName:InformatiionFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public class CommunityFragment extends BaseFragment implements CommunityContract.CommunityView, AddCommunityContract.AddCommunityView, LikeContract.LikeView, NoLikeContract.NoLikeView {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.Community_write)
    ImageView CommunityWrite;
    Unbinder unbinder1;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder2;
    private CommunityPresenter communityPresenter;
    private LikePresenter likePresenter;
    private NoLikePresenter noLikePresenter;
    private int userId;
    private int page = 1;
    private String sessionId;
    private EditText mEtContent;
    private TextView mSend;
    private AddCommunityPresenter addCommunityPresenter;
    private CommunityAdapter communityAdapter;
    private ArrayList<CommunityBean.ResultBean> resultBeans;

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_community_layout;
    }

    @Override
    protected void initData() {

        Fresco.initialize(getActivity());
        super.initData();
        unbinder = ButterKnife.bind(this, mRootView);
        refreshlayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        communityPresenter = new CommunityPresenter();
        communityPresenter.attahView(this);
        communityPresenter.requestData(1, 10);
        refreshlayout.setEnableRefresh(true);//启用刷新
        refreshlayout.setEnableLoadMore(true);//启用加载
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                resultBeans.clear();
                communityPresenter.requestData(1,10);
                refreshlayout.finishRefresh();
            }
        });
        refreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                communityPresenter.requestData(page,10);
                refreshlayout.finishLoadMore();
            }
        });
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        likePresenter = new LikePresenter();
        likePresenter.attahView(this);
        noLikePresenter = new NoLikePresenter();
        noLikePresenter.attahView(this);
        addCommunityPresenter = new AddCommunityPresenter();
        addCommunityPresenter.attahView(this);
        CommunityWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DealActivity.class));
                getActivity().finish();
            }
        });


    }



    @Override
    public void showData(CommunityBean communityBean) {

        List<CommunityBean.ResultBean> result = communityBean.getResult();
        resultBeans = new ArrayList<>();
        resultBeans.addAll(result);
        communityAdapter = new CommunityAdapter(getActivity(), resultBeans);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(communityAdapter);




        communityAdapter.setOnCommunityListClickListener(new CommunityAdapter.onCommunityListClickListener() {
            @Override
            public void onmHeadPicClick(int userid) {

            }

            @Override
            public void onmCommentClick(final int id, String name) {
                View inflate = View.inflate(getActivity(), R.layout.pop_comment, null);
                mEtContent = inflate.findViewById(R.id.et_content);
                mEtContent.setHint(name);
                final Dialog builder = new Dialog(getActivity(), R.style.BottomDialog);
                builder.setContentView(inflate);
                builder.setCanceledOnTouchOutside(true);
                ViewGroup.LayoutParams layoutParamsthreefilmreview = inflate.getLayoutParams();
                layoutParamsthreefilmreview.width = getResources().getDisplayMetrics().widthPixels;
                inflate.setLayoutParams(layoutParamsthreefilmreview);
                builder.getWindow().setGravity(Gravity.BOTTOM);
                builder.show();
                mSend = inflate.findViewById(R.id.send);
                mSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = mEtContent.getText().toString().trim();
                        if (s.isEmpty()) {
                            Toast.makeText(getContext(), "评论内容不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            addCommunityPresenter.requestData(userId, sessionId, id, s);
                            builder.dismiss();
                        }
                    }
                });
            }

            @Override
            public void onmPraiseClick(int id, int whetherGreat) {

                if (whetherGreat == 1) {
                    noLikePresenter.requestData(userId, sessionId, id);
                } else {
                    likePresenter.requestData(userId, sessionId, id);
                }

            }
        });
    }

    @Override
    public void showData(LikeBean likeBean) {
        Toast.makeText(getActivity(), "" + likeBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(NoLikeBean noLikeBean) {
        Toast.makeText(getActivity(), "" + noLikeBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddCommunityData(AddCommunityBean communityBean) {
        if (communityBean.getStatus().equals("0000")) {
            Toast.makeText(getActivity(), "" + communityBean.getMessage(), Toast.LENGTH_SHORT).show();
            communityAdapter.clear();
            communityPresenter.requestData(1, 10);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        communityPresenter.deachView(this);
        likePresenter.deachView(this);
        noLikePresenter.deachView(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;

    }
}
