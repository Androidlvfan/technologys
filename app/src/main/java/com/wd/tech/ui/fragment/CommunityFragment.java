package com.wd.tech.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wd.tech.R;
import com.wd.tech.data.adapter.CommunityAdapter;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.di.contract.CommunityContract;
import com.wd.tech.di.presenter.CommunityPresenter;

import java.util.List;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:51
 * @fileName:InformatiionFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public class CommunityFragment extends BaseFragment implements CommunityContract.CommunityView {


    private CommunityPresenter communityPresenter;
    private RecyclerView rv;
    private CommunityAdapter communityAdapter;


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
//        rv = getActivity().findViewById(R.id.rv);
         Button a  = getActivity().findViewById(R.id.a);
         a.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.e("tag","sdasd");
             }
         });
        communityPresenter = new CommunityPresenter();
        communityPresenter.attahView(this);
        communityPresenter.requestData(1, 10);

    }

    @Override
    public void showData(CommunityBean communityBean) {

        List<CommunityBean.ResultBean> result = communityBean.getResult();
//        communityAdapter = new CommunityAdapter(getActivity(), result);
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv.setAdapter(communityAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        communityPresenter.deachView(this);
    }




}
