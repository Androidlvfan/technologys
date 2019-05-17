package com.wd.tech.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.tech.R;
import com.wd.tech.data.adapter.CommunityAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.contract.LikeContract;
import com.wd.tech.di.contract.NoLikeContract;
import com.wd.tech.di.presenter.CommunityPresenter;
import com.wd.tech.di.presenter.LikePresenter;
import com.wd.tech.di.presenter.NoLikePresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.DealActivity;

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
public class CommunityFragment extends BaseFragment implements CommunityContract.CommunityView ,LikeContract.LikeView,NoLikeContract.NoLikeView {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.Community_write)
    ImageView CommunityWrite;
    Unbinder unbinder1;
    private CommunityPresenter communityPresenter;
    private LikePresenter likePresenter;
    private NoLikePresenter noLikePresenter;
    private int userId;
    private String sessionId;

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
        communityPresenter = new CommunityPresenter();
        communityPresenter.attahView(this);
        communityPresenter.requestData(1, 10);

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

        CommunityWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DealActivity.class));
                getActivity().finish();
            }
        });


    }

    @Override
    public void showData(CommunityBean communityBean) {

        List<CommunityBean.ResultBean> result = communityBean.getResult();
        CommunityAdapter communityAdapter = new CommunityAdapter(getActivity(), result);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(communityAdapter);

        communityAdapter.setOnCommunityListClickListener(new CommunityAdapter.onCommunityListClickListener() {
            @Override
            public void onmHeadPicClick(int userid) {

            }

            @Override
            public void onmCommentClick(int id, String name) {

            }

            @Override
            public void onmPraiseClick(int id, int whetherGreat) {

                    if (whetherGreat == 1) {
                        noLikePresenter.requestData(userId,sessionId, id);
                    } else {
                        likePresenter.requestData(userId,sessionId, id);
                    }

            }
        });
    }

    @Override
    public void showData(LikeBean likeBean) {
        Toast.makeText(getActivity(), ""+likeBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(NoLikeBean noLikeBean) {
        Toast.makeText(getActivity(), ""+noLikeBean.getMessage(), Toast.LENGTH_SHORT).show();
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



}
