package com.wd.tech.wxapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.WxBean;
import com.wd.tech.data.utils.WeiXinUtil;
import com.wd.tech.di.contract.WxContract;
import com.wd.tech.di.presenter.WxPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.TechHomeActivity;

import java.util.List;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler ,WxContract.WxView {

    private String code;
    private WxPresenter wxPresenter;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_wxentry);
        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                code = ((SendAuth.Resp) baseResp).code;
                wxPresenter = new WxPresenter();
                wxPresenter.attahView(this);
                wxPresenter.requestData(userId,sessionId,"0110010010000",code);
                break;
            default:
                break;
        }
    }

    @Override
    public void showData(WxBean wxBean) {
        if (wxBean.getStatus().equals("0000")){
            //登录成功
            Toast.makeText(WXEntryActivity.this, ""+wxBean.getMessage(), Toast.LENGTH_SHORT).show();
            //跳转登录成功后的页面
            startActivity(new Intent(WXEntryActivity.this, TechHomeActivity.class));
            finish();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wxPresenter.deachView(this);
    }
}
