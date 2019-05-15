package com.wd.tech.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.tech.R;
import com.wd.tech.data.adapter.CommunityAdapter;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.presenter.CommunityPresenter;
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
public class CommunityFragment extends BaseFragment implements CommunityContract.CommunityView {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.Community_write)
    ImageView CommunityWrite;
    Unbinder unbinder1;
    private CommunityPresenter communityPresenter;


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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        communityPresenter.deachView(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
