package com.wd.tech.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.data.adapter.DetailFailAdapter;
import com.wd.tech.data.adapter.DetailOkAdapter;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.di.contract.CommentContract;
import com.wd.tech.di.contract.DetailContract;
import com.wd.tech.di.presenter.CommentPresenter;
import com.wd.tech.di.presenter.DetailPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity implements DetailContract.DetailView, CommentContract.CommentView {


    TextView detailTitle;
    TextView detailRelatedTime;
    TextView detailSource;
    @BindView(R.id.detail_edit)
    EditText detailEdit;
    @BindView(R.id.detail_comment_num)
    ImageView detailCommentNum;
    @BindView(R.id.parise_cb)
    CheckBox pariseCb;
    @BindView(R.id.sellect_cb)
    CheckBox sellectCb;
    @BindView(R.id.share_cb)
    CheckBox shareCb;
    ImageView detailThumbnail;
    @BindView(R.id.detail_rcv)
    XRecyclerView detailRcv;

    private DetailPresenter detailPresenter;
    private CommentPresenter commentPresenter;

    private DetailOkAdapter detailOkAdapter;
    private DetailFailAdapter detailFailAdapter;

    private String sessionId;
    private int userId;
    private int whetherPay;

    @Override
    protected int initLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        detailPresenter = new DetailPresenter();
        detailPresenter.attrView(this);

        commentPresenter = new CommentPresenter();
        commentPresenter.attrView(this);

        detailOkAdapter = new DetailOkAdapter(this);

        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();


        Intent intent = getIntent();
        int ids = intent.getExtras().getInt("id");//条目详情id
        whetherPay = intent.getExtras().getInt("whetherPay");//是否付费
        Log.i("DetailId", "" + ids+""+whetherPay);

        detailPresenter.requestData(userId, sessionId, ids);//详情列表

        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("count", "5");
        commentPresenter.requestData(userId, sessionId, ids, params);//评论列表
    }

    @Override
    protected void initListener() {
        View view = View.inflate(this, R.layout.head_detais, null);
        detailTitle = view.findViewById(R.id.detail_title);
        detailRelatedTime = view.findViewById(R.id.detail_relatedTime);
        detailSource = view.findViewById(R.id.detail_source);
        detailThumbnail = view.findViewById(R.id.detail_thumbnail);
        detailRcv.addHeaderView(view);//添加头部
    }

    //点击返回上一页
    @OnClick(R.id.return_page)
    public void onViewClicked() {
        finish();
    }

    /**
     * 详情页展示成功
     *
     * @param detailOkBean
     */
    @Override
    public void ShowData(DetailOkBean detailOkBean) {

        Log.i("sessionid", "" + sessionId+"%"+userId);

        detailTitle.setText(detailOkBean.getResult().getTitle());
        detailRelatedTime.setText(detailOkBean.getResult().getReleaseTime() + "");
        detailSource.setText(detailOkBean.getResult().getSource());

        detailOkAdapter.setResultBean(detailOkBean);

        Glide.with(this).load(detailOkBean.getResult().getThumbnail()).into(detailThumbnail);

        Log.i("Ok", detailOkBean.getMessage() + "");
        Log.i("whetherPay", "" + whetherPay);
        if (whetherPay == 1) {
            detailRcv.setLayoutManager(new LinearLayoutManager(this));
        } else if (whetherPay == 2) {
            detailRcv.setLayoutManager(new LinearLayoutManager(this));
        }else{
            Log.i("检查","检查");
        }

        if(detailOkBean.getResult().getReadPower() == 1){
            Toast.makeText(this,"免费阅读",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"需付费",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 评论列表展示成功
     *
     * @param detailCommentBean
     */
    @Override
    public void getData(DetailCommentBean detailCommentBean) {


        List<DetailCommentBean.ResultBean> result = detailCommentBean.getResult();
        Log.i("Okk", detailCommentBean.getResult().get(0).getNickName() + "");

        if(whetherPay == 2){//2为免费
            //detailOkAdapter = new DetailOkAdapter(this);
            detailOkAdapter.setComentBean(detailCommentBean.getResult());
            detailRcv.setAdapter(detailOkAdapter);//免费

        }else{
            detailFailAdapter = new DetailFailAdapter(this, result);
            detailRcv.setAdapter(detailFailAdapter);//付费
        }

        Log.i("Okkk", detailCommentBean.getMessage() + "");
        Toast.makeText(this, detailCommentBean.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.deachView(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
