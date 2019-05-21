package com.wd.tech.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.adapter.SearchAdapter;
import com.wd.tech.data.bean.SerachByTitleBean;
import com.wd.tech.di.contract.SearchContract;
import com.wd.tech.di.presenter.SearchPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchContract.SearchView {

    @BindView(R.id.search_btn)
    ImageView searchBtn;
    @BindView(R.id.search_cancel)
    TextView searchCancel;
    @BindView(R.id.blockchain)
    TextView blockchain;
    @BindView(R.id.midlife_crisis)
    TextView midlifeCrisis;
    @BindView(R.id.hammer_tech)
    TextView hammerTech;
    @BindView(R.id.bullet_SMS)
    TextView bulletSMS;
    @BindView(R.id.private_enterprise)
    TextView privateEnterprise;
    @BindView(R.id.Tesla)
    TextView Tesla;
    @BindView(R.id.alipay)
    TextView alipay;
    @BindView(R.id.capital_market)
    TextView capitalMarket;
    @BindView(R.id.TV_play)
    TextView TVPlay;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.linear_one)
    LinearLayout linearOne;
    @BindView(R.id.linear_two)
    LinearLayout linearTwo;
    @BindView(R.id.linear_three)
    LinearLayout linearThree;
    @BindView(R.id.search_rcv)
    RecyclerView searchRcv;
    @BindView(R.id.search_none)
    TextView searchNone;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;


    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        searchPresenter = new SearchPresenter();
        searchPresenter.attrView(this);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.search_btn, R.id.search_cancel, R.id.blockchain, R.id.midlife_crisis, R.id.hammer_tech, R.id.bullet_SMS, R.id.private_enterprise, R.id.Tesla, R.id.alipay, R.id.capital_market, R.id.TV_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_btn://点击搜索
                String s = searchEdit.getText().toString();
                Log.i("TasT", s + "");
                HashMap<String, String> params = new HashMap<>();
                params.put("page", "1");
                params.put("count", "5");
                searchPresenter.requestData(s, params);
                break;
            case R.id.search_cancel://点击取消
                finish();
                break;
            case R.id.blockchain://区块链
                String block = blockchain.getText().toString();
                searchEdit.setText(block);//给输入框赋值
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("page", "1");
                params1.put("count", "5");
                searchPresenter.requestData(block, params1);
                break;
            case R.id.midlife_crisis://中年危机
                String midlife = midlifeCrisis.getText().toString();
                searchEdit.setText(midlife);//给输入框赋值
                HashMap<String, String> params2 = new HashMap<>();
                params2.put("page", "1");
                params2.put("count", "5");
                searchPresenter.requestData(midlife, params2);
                break;
            case R.id.hammer_tech://锤子科技
                String hammer = hammerTech.getText().toString();
                searchEdit.setText(hammer);//给输入框赋值
                HashMap<String, String> params3 = new HashMap<>();
                params3.put("page", "1");
                params3.put("count", "5");
                searchPresenter.requestData(hammer, params3);
                break;
            case R.id.bullet_SMS://子弹短信
                String SMS = bulletSMS.getText().toString();
                searchEdit.setText(SMS);//给输入框赋值
                HashMap<String, String> params4 = new HashMap<>();
                params4.put("page", "1");
                params4.put("count", "5");
                searchPresenter.requestData(SMS, params4);
                break;
            case R.id.private_enterprise://民营企业
                String Enterprise = privateEnterprise.getText().toString();
                searchEdit.setText(Enterprise);//给输入框赋值
                HashMap<String, String> params5 = new HashMap<>();
                params5.put("page", "1");
                params5.put("count", "5");
                searchPresenter.requestData(Enterprise, params5);
                break;
            case R.id.Tesla://特斯拉
                String tesla = Tesla.getText().toString();
                searchEdit.setText(tesla);//给输入框赋值
                HashMap<String, String> params6 = new HashMap<>();
                params6.put("page", "1");
                params6.put("count", "5");
                searchPresenter.requestData(tesla, params6);
                break;
            case R.id.alipay://支付宝
                String Alipay = alipay.getText().toString();
                searchEdit.setText(Alipay);//给输入框赋值
                HashMap<String, String> params7 = new HashMap<>();
                params7.put("page", "1");
                params7.put("count", "5");
                searchPresenter.requestData(Alipay, params7);
                break;
            case R.id.capital_market://资本市场
                String Market = capitalMarket.getText().toString();
                searchEdit.setText(Market);//给输入框赋值
                HashMap<String, String> params8 = new HashMap<>();
                params8.put("page", "1");
                params8.put("count", "5");
                searchPresenter.requestData(Market, params8);
                break;
            case R.id.TV_play://电视剧
                String TV_play = TVPlay.getText().toString();
                searchEdit.setText(TV_play);//给输入框赋值
                HashMap<String, String> params9 = new HashMap<>();
                params9.put("page", "1");
                params9.put("count", "5");
                searchPresenter.requestData(TV_play, params9);
                break;
        }
    }

    /**
     * 搜索请求成功
     *
     * @param serachByTitleBean
     */
    @Override
    public void SearchData(SerachByTitleBean serachByTitleBean) {

        Log.i("Search", serachByTitleBean.getMessage() + "");

        searchAdapter = new SearchAdapter(this, serachByTitleBean.getResult());
        searchRcv.setAdapter(searchAdapter);
        searchRcv.setLayoutManager(new LinearLayoutManager(this));
        if(serachByTitleBean.getResult().size() != 0){
            searchRcv.setVisibility(View.VISIBLE);
            linearOne.setVisibility(View.GONE);
            linearTwo.setVisibility(View.GONE);
            linearThree.setVisibility(View.GONE);
            searchNone.setVisibility(View.GONE);
        }else{
            searchNone.setVisibility(View.VISIBLE);
            searchRcv.setVisibility(View.GONE);
            linearOne.setVisibility(View.VISIBLE);
            linearTwo.setVisibility(View.VISIBLE);
            linearThree.setVisibility(View.VISIBLE);
        }

        Toast.makeText(this, serachByTitleBean.getMessage(), Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
