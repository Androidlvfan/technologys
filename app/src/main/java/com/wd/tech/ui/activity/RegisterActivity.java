package com.wd.tech.ui.activity;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
=======
>>>>>>> lhpp
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;
<<<<<<< HEAD
import com.mob.commons.SMSSDK;
=======
>>>>>>> lhpp
import com.wd.tech.R;
import com.wd.tech.data.bean.RegisterBean;
import com.wd.tech.data.utils.RsaCoder;
import com.wd.tech.di.contract.RegisterContract;
import com.wd.tech.di.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 20:24
 * @fileName:RegisterActivity
 * @packageName:com.wd.tech.ui.activity
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView {


    @BindView(R.id.reg_imglogo)
    ImageView regImglogo;
    @BindView(R.id.reg_imgname)
    ImageView regImgname;
    @BindView(R.id.edit_reg_nickname)
    EditText editRegNickname;
    @BindView(R.id.relaone)
    RelativeLayout relaone;
    @BindView(R.id.viewone)
    View viewone;
    @BindView(R.id.reg_imgphone)
    ImageView regImgphone;
    @BindView(R.id.edit_reg_phone)
    EditText editRegPhone;
    @BindView(R.id.relatwo)
    RelativeLayout relatwo;
    @BindView(R.id.viewtwo)
    View viewtwo;
    @BindView(R.id.reg_imglock)
    ImageView regImglock;
    @BindView(R.id.edit_reg_lock)
    EditText editRegLock;
    @BindView(R.id.relaonethree)
    RelativeLayout relaonethree;
    @BindView(R.id.viewthree)
    View viewthree;
    @BindView(R.id.reg_imgyz)
    ImageView regImgyz;
    @BindView(R.id.edit_reg_yz)
    EditText editRegYz;
    @BindView(R.id.reg_btyz)
    TextView regBtyz;
    @BindView(R.id.relaonefour)
    RelativeLayout relaonefour;
    @BindView(R.id.viewfour)
    View viewfour;
    @BindView(R.id.btn_reg)
    Button btnReg;
    private RegisterPresenter presenter;
    private String name;
    private String phone;
    private String encrypt;
    int i = 30;
    String APPKEY = "2acc7c1acf79a";
    String APPSECRET = "05c9794ab95a43567c2358ddac8c937a";
    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        //绑定布局
        ButterKnife.bind(this);
        //实例化p层
        presenter = new RegisterPresenter();
        presenter.attahView(this);
        //启动短信验证
        MobSDK.init(this,APPKEY,APPSECRET);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        //注册回调监听接口
        cn.smssdk.SMSSDK.registerEventHandler(eh);

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.reg_btyz, R.id.btn_reg})
    public void onViewClicked(View view) {
       String phone = editRegPhone.getText().toString();
        switch (view.getId()) {
            //点击获取验证码
            case R.id.reg_btyz:
                // 1. 判断手机号是不是11位并且看格式是否合理
                if (!judgePhoneNums(phone)) {
                    return;
                } // 2. 通过sdk发送短信验证
                cn.smssdk.SMSSDK.getVerificationCode("86", phone);

                //把发送验证码变成不可点击 表示正在发送 并且显示倒计时
                regBtyz.setClickable(false);
                regBtyz.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;
            //点击注册
            case R.id.btn_reg:
                try {
                    name = editRegNickname.getText().toString();
                    phone = editRegPhone.getText().toString();
                    String pwd = editRegLock.getText().toString();
                    encrypt = RsaCoder.encryptByPublicKey(pwd);
                    //判空
                    if (name.equals("") || phone.equals("") || encrypt.equals("") || pwd.equals("")) {
                        Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //请求数据
                    presenter.requestData(phone, name, encrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //将收到的验证码和手机号提交再次核对
                cn.smssdk.SMSSDK.submitVerificationCode("86", phone, editRegYz
                        .getText().toString());
                break;
        }
    }
    /**
     * 判断手机号码是否合理
     *
     *
     */
    private boolean judgePhoneNums(String phone) {
        if (isMatchLength(phone, 11)
                && isMobileNO(phone)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                regBtyz.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                regBtyz.setText("获取验证码");
                regBtyz.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.d("RegisterActivity", "event:" + event);
                if (result == cn.smssdk.SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    //提交验证码成功
                    if (event == cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Toast.makeText(RegisterActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                    }  //获取验证码成功
                    else if (event == cn.smssdk.SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(RegisterActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "获取验证码不正确", Toast.LENGTH_SHORT).show();
                        ((Throwable) data).printStackTrace();
                    }


                }
            }
        }
    };




    /**
     * 判断一个字符串的位数
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

<<<<<<< HEAD


    @Override
    public void showData(RegisterBean registerBean) {
        //吐司信息
        Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deachView(this);
        cn.smssdk.SMSSDK.unregisterAllEventHandler();
    }
}
=======
>>>>>>> lhpp
