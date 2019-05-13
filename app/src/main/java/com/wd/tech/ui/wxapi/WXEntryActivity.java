package com.wd.tech.ui.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.utils.WeiXinUtil;
import com.wd.tech.di.contract.WxContract;
import com.wd.tech.di.presenter.WxPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.TechHomeActivity;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler ,WxContract.WxView {

    private String code;
    private WxPresenter wxPresenter;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }*/
        setContentView(R.layout.activity_wxentry);
        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                code = ((SendAuth.Resp) baseResp).code;
                //p层请求加入参数code值
                wxPresenter = new WxPresenter();
                wxPresenter.attahView(this);
                wxPresenter.requestData(code);
                break;
            default:
                break;
        }
    }

    @Override
    public void showData(LoginBean loginBean) {
        LoginBean.ResultBean result = loginBean.getResult();
        userId = result.getUserId();
        sessionId = result.getSessionId();
        //同步之前先查询数据库
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        //再添加之前先清空一遍数据库  保证每次查询数据库的数据都是最新的
        if (greenBeanDao.loadAll().size() > 0){
            greenBeanDao.deleteAll();
        }
        GreenBean greenBean = new GreenBean();
        greenBean.setUserId(userId);
        greenBean.setSessionId(sessionId);
        greenBeanDao.insertOrReplace(greenBean);
        if (loginBean.getStatus().equals("0000")){
            Toast.makeText(this, loginBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(WXEntryActivity.this,TechHomeActivity.class));
        }else {
            Toast.makeText(WXEntryActivity.this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wxPresenter.deachView(this);
    }
}
