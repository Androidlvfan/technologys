package com.wd.tech.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.data.adapter.PictureAdapter;
import com.wd.tech.data.bean.DealBean;
import com.wd.tech.di.contract.DealContract;
import com.wd.tech.di.presenter.DealPresenter;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealActivity extends BaseActivity implements View.OnClickListener,DealContract.DealView {

    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.txt_publish)
    TextView txtPublish;
    @BindView(R.id.community_publish_content)
    EditText communityPublishContent;
    @BindView(R.id.community_publish_num)
    TextView communityPublishNum;
    @BindView(R.id.community_image)
    RecyclerView communityImage;
    @BindView(R.id.linear_layout_container)
    LinearLayout linearLayoutContainer;
    private int max = 300;//限制的最大字数
    private PictureAdapter pictureAdapter;
    private PopupWindow popupWindow;
    private LinearLayout open_camera;
    private LinearLayout open_picture;
    private LinearLayout btn_cancel;
    private String absolutePath;
    private static final int TAKE_PICTURE = 1;
    private DealPresenter dealPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_deal;
    }

    @Override
    protected void initData() {

        ButterKnife.bind(this);

        dealPresenter = new DealPresenter();
        dealPresenter.attahView(this);

        pictureAdapter = new PictureAdapter(this);
        pictureAdapter.add(R.mipmap.addpicture);
        communityImage.setLayoutManager(new GridLayoutManager(this, 3));
        communityImage.setAdapter(pictureAdapter);
        pictureAdapter.setOnImageClickListener(new PictureAdapter.OnImageClickListener() {
            @Override
            public void onImageClick() {
                View view1 = View.inflate(DealActivity.this, R.layout.community_popwindow, null);
                popupWindow = new PopupWindow(view1);
                //设置充满父窗体
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setAnimationStyle(R.style.StyleNetChangedDialog_Animation);
                //点击外部关闭弹框
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
                open_camera = view1.findViewById(R.id.open_camera);
                open_picture = view1.findViewById(R.id.open_picture);
                btn_cancel = view1.findViewById(R.id.btn_cancel);
                //相机
                open_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        String path = Environment.getExternalStorageDirectory().toString() + "/elife/img";
                        File path1 = new File(path);
                        if (!path1.exists()) {
                            path1.mkdirs();
                        }
                        File file = new File(path1, System.currentTimeMillis() + ".jpg");
                        Uri mOutPutFileUri = Uri.fromFile(file);
                        absolutePath = file.getAbsolutePath();
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
                        startActivityForResult(intent, TAKE_PICTURE);
                    }
                });
                //相册
                open_picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                       /* DealActivity.this.startActivityForResult(intent, WDActivity.PHOTO);*/
                    }
                });
                //取消popupWindow的点击事件
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });




        //输入框字数限制
        InputFilter[] filters = {chineseFilter()};
        communityPublishContent.addTextChangedListener(passwordListener());
        communityPublishContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showData(DealBean dealBean) {



    }

    /**
     * 输入框
     * @return
     */
    private InputFilter chineseFilter() {
        return new InputFilter() {
            String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                float destCount = dest.toString().length()
                        + getChineseCount(dest.toString());
                float sourceCount = source.toString().length()
                        + getChineseCount(source.toString());
                if (destCount + sourceCount > 10) {
                    Log.e("log", "已经达到字数限制范围");
                    return "";

                } else {
                    return source;
                }
            }

            private float getChineseCount(String str) {
                float count = 0;
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(str);
                while (m.find()) {
                    for (int i = 0; i <= m.groupCount(); i++) {
                        count =count + 1;//
                    }
                }
                return count;
            }
        };
    }
    private TextWatcher passwordListener() {
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length>max){
                    Toast.makeText(DealActivity.this, "最多可输入"+max+"个字", Toast.LENGTH_SHORT).show();
                }else {
                    communityPublishNum.setText(length + "/" + max);

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
