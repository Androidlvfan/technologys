package com.wd.tech.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.di.contract.LoginContract;
import com.wd.tech.di.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TechLoginActivity extends BaseActivity implements LoginContract.LoginView,View.OnClickListener {


    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.text_reg)
    TextView textReg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.raw)
    LinearLayout raw;
    private LoginPresenter loginPresenter;
    private SharedPreferences preferences;
    boolean tures;

    @Override
    protected int initLayout() {
        return R.layout.activity_tech_login;
    }

    @Override
    protected void initData() {

        ButterKnife.bind(this);

        btnLogin.setOnClickListener(this);
        textReg.setOnClickListener(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attahView(this);

        etLoginPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = etLoginPwd.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > etLoginPwd.getWidth()
                        - etLoginPwd.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    if (!tures) {
                        //明文设置
                        tures = true;
                        etLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        //密文设置
                        tures = false;
                        etLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                try {
                    String phone1 = etLoginName.getText().toString();
                    String pwd1 = etLoginPwd.getText().toString();
                    String publicKey = RsaCoder.encryptByPublicKey(pwd1);
                    if(phone1.equals("")&&phone1!=null||pwd1.equals("")){
                        Toast.makeText(TechLoginActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                    }
                    loginPresenter.requestData(phone1,publicKey);
                    Log.e("lhp",""+phone1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.text_reg:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                startActivity(new Intent(TechLoginActivity.this,RegisterActivity.class));
                break;
          /*  case R.id.wxLogin:
               *//* // 微信登录
                if (!WeiXinUtil.success(this)) {
                    Toast.makeText(this, "请先安装应用", Toast.LENGTH_SHORT).show();
                } else {
                    //  验证
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    WeiXinUtil.reg(LoginActivity.this).sendReq(req);
                }*//*
                break;*/
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showData(LoginBean loginBean) {


        if(loginBean.getStatus().equals("0000")){
           /* SpUtils.getInstance().saveData("userId", loginBean.getResult().getUserId() + "");
            SpUtils.getInstance().saveData("sessionId", loginBean.getResult().getSessionId() + "");
            */
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TechLoginActivity.this,TechHomeActivity.class));
            Log.i("userId",loginBean.getResult().getUserId()+"");
            Log.i("sessionId",loginBean.getResult().getSessionId()+"");

           /* SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("userId",loginBean.getResult().getUserId());
            edit.putString("sessionId",loginBean.getResult().getSessionId());
            edit.commit();*/
        }else {
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /*
    *  13835052810
    * */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.deachView(this);
    }


}
