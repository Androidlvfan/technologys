package com.wd.tech.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.tech.R;
import com.wd.tech.data.adapter.CommunityAdapter;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.presenter.CommunityPresenter;

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
    private CommunityPresenter communityPresenter;
    private CommunityAdapter communityAdapter;
    private View rootView;


    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.frag_community_layout;
    }

    @Override
    protected void initData() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }*/
        Fresco.initialize(getActivity());
        super.initData();
        unbinder = ButterKnife.bind(this, mRootView);
        communityPresenter = new CommunityPresenter();
        communityPresenter.attahView(this);
        communityPresenter.requestData(1, 10);

    }

    @Override
    public void showData(CommunityBean communityBean) {

        List<CommunityBean.ResultBean> result = communityBean.getResult();
        communityAdapter = new CommunityAdapter(getActivity(), result);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
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
