package com.wd.tech.data.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.data.app.App;
import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddFriendGroupBean;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.FriendByGroupIdBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.data.bean.GreenBean;
import com.wd.tech.data.constant.ApiService;
import com.wd.tech.data.utils.RetrofitUtils;
import com.wd.tech.di.contract.FriendByGroupIdContract;
import com.wd.tech.di.presenter.FriendByGroupIdPresenter;
import com.wd.tech.gen.DaoSession;
import com.wd.tech.gen.GreenBeanDao;
import com.wd.tech.ui.activity.FriendMessageByGroupActivity;

import java.security.acl.Group;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.POST;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/15 21:38
 * @fileName:FriendGroupAdapter
 * @packageName:com.wd.tech.data.adapter
 */
public class FriendGroupAdapter extends BaseQuickAdapter<FriendGroupBean.ResultBean,BaseViewHolder> implements FriendByGroupIdContract.FriendByGroupIdView {

    private boolean flag = false;
    private int userId;
    private String sessionId;
    private RecyclerView childrecy;
    private LinearLayout clickLinear;

    OnAddGroupClickListener onAddGroupClickListener;
    private int groupId;

    public void setOnAddGroupClickListener(OnAddGroupClickListener onAddGroupClickListener){
        this.onAddGroupClickListener = onAddGroupClickListener;
    }
    public interface OnAddGroupClickListener{
        void onAdd();
        void onUpd();
        void onDel();
        void onDelFriend();
        void moveFriend();
    }

    public FriendGroupAdapter(int layoutResId, @Nullable List<FriendGroupBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final FriendGroupBean.ResultBean item) {
        //取出userId和sessionId
        DaoSession daoSession = App.getDaoSession();
        GreenBeanDao greenBeanDao = daoSession.getGreenBeanDao();
        List<GreenBean> greenBeans = greenBeanDao.loadAll();
        userId = greenBeans.get(0).getUserId();
        sessionId = greenBeans.get(0).getSessionId();
        //实例化对象
        final FriendByGroupIdPresenter friendByGroupIdPresenter = new FriendByGroupIdPresenter();
        friendByGroupIdPresenter.attahView(this);
        if (item.getGroupName().equals("黑名单")){
            groupId = item.getGroupId();
        }

        //分组名
        helper.setText(R.id.OneListRecyclerItem_groupText,item.getGroupName());
        //分组人数
        helper.setText(R.id.OneListRecyclerItem_friendNum, "2/" + item.getCurrentNumber());
        //分组下的recycle
        childrecy = helper.getView(R.id.OneListRecyclerItem_childRecycler);
        //分组条
        clickLinear = helper.getView(R.id.OneListRecyclerItem_clickLinear);
        //三角图片
        final ImageView icon = helper.getView(R.id.OneListRecyclerItem_showIcon);
        //点击条目
        clickLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    childrecy = helper.getView(R.id.OneListRecyclerItem_childRecycler);
                    flag = true;
                    //设置为下三角
                    icon.setImageResource(R.mipmap.bottom_sanjiao);
                    //列表可见
                    childrecy.setVisibility(View.VISIBLE);
                    //请求数据
                    friendByGroupIdPresenter.requestData(userId,sessionId,item.getGroupId());
                }else{
                    childrecy = helper.getView(R.id.OneListRecyclerItem_childRecycler);
                    flag = false;
                    //设置为正三角
                    icon.setImageResource(R.mipmap.right_sanjiao);
                    //列表不可见
                    childrecy.setVisibility(View.GONE);
                }
            }
        });
        //右击监听
        clickLinear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                View inflate = LayoutInflater.from(mContext).inflate(R.layout.group_pop_layout, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
                TextView addGroup = inflate.findViewById(R.id.add_group);
                final TextView deleteGroup = inflate.findViewById(R.id.delete_group);
                final TextView updGroup = inflate.findViewById(R.id.update_group);
                TextView cancelGroup = inflate.findViewById(R.id.cancel_group);
                //添加分组
                addGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //pop消失
                        popupWindow.dismiss();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("添加分组");
                        View view1=LayoutInflater.from(mContext).inflate(R.layout.group_edit_layout,null);
                        final EditText editText=view1.findViewById(R.id.personal_edit);
                        builder.setView(view1);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String groupedit = editText.getText().toString();
                                if (groupedit.equals("")){
                                    //消失弹框
                                    Toast.makeText(mContext, "请输入分组名称", Toast.LENGTH_SHORT).show();
                                }else {
                                    //请求数据
                                    Observable<AddFriendGroupBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getAddFriendGroupData(userId, sessionId, groupedit);
                                    observable
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<AddFriendGroupBean>() {
                                                @Override
                                                public void accept(AddFriendGroupBean addFriendGroupBean) throws Exception {
                                                    if (addFriendGroupBean.getStatus().equals("0000")){
                                                        Toast.makeText(mContext, addFriendGroupBean.getMessage(), Toast.LENGTH_SHORT).show();
                                                        //让软键盘消失
                                                        ((InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE)).
                                                                hideSoftInputFromWindow(clickLinear.getWindowToken(),
                                                                        InputMethodManager.HIDE_NOT_ALWAYS);
                                                        //接口回调
                                                        onAddGroupClickListener.onAdd();

                                                    }

                                                }
                                            });
                                }
                            }
                        });
                        //点击取消
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //显示弹框
                        builder.show();
                    }
                });
                updGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //pop消失
                        popupWindow.dismiss();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("修改分组");
                        final View view1=LayoutInflater.from(mContext).inflate(R.layout.group_edit_layout,null);
                        final EditText editText=view1.findViewById(R.id.personal_edit);
                        builder.setView(view1);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String updgroup = editText.getText().toString();
                                if (updGroup.equals("")){
                                    Toast.makeText(mContext, "请输入新的名称", Toast.LENGTH_SHORT).show();
                                }else{
                                    //请求数据
                                    Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getUpdGroupNameData(userId, sessionId, item.getGroupId(), updgroup);
                                    observable
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<AddIngFriendBean>() {
                                                @Override
                                                public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                                                    if (addIngFriendBean.getStatus().equals("0000")){
                                                        Toast.makeText(mContext, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
                                                        //让软键盘消失
                                                        ((InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE)).
                                                                hideSoftInputFromWindow(view1.getWindowToken(),
                                                                        InputMethodManager.HIDE_NOT_ALWAYS);
                                                        //接口回调
                                                        onAddGroupClickListener.onUpd();
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        //点击取消
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //显示弹框
                        builder.show();
                    }
                });
                //删除分组
                deleteGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //pop消失
                        popupWindow.dismiss();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("删除分组");
                        builder.setMessage("您确定要删除该分组吗?");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getDeleteGroupData(userId, sessionId, item.getGroupId());
                                observable
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<AddIngFriendBean>() {
                                            @Override
                                            public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                                                if (addIngFriendBean.getStatus().equals("0000")){
                                                    Toast.makeText(mContext, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
                                                    //让软键盘消失
                                                    ((InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE)).
                                                            hideSoftInputFromWindow(deleteGroup.getWindowToken(),
                                                                    InputMethodManager.HIDE_NOT_ALWAYS);
                                                    //接口回调
                                                    onAddGroupClickListener.onDel();
                                                }
                                            }
                                        });
                            }
                        });
                        //点击取消
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //显示弹框
                        builder.show();
                    }
                });
                //点击取消
                cancelGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                //显示pop
                popupWindow.showAtLocation(clickLinear,Gravity.BOTTOM,0,0);
                return true;
            }
        });
}


    //二级列表的回调
    @Override
    public void showData(final FriendByGroupIdBean friendByGroupIdBean) {
        final List<FriendByGroupIdBean.ResultBean> result = friendByGroupIdBean.getResult();
        FriendByGroupIdAdapter friendByGroupIdAdapter = new FriendByGroupIdAdapter(R.layout.friendbygroup_item_layout,result);
        childrecy.setAdapter(friendByGroupIdAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        childrecy.setLayoutManager(linearLayoutManager);
        //条目点击
        friendByGroupIdAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.friend_pop_layout, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                TextView delfriend = inflate.findViewById(R.id.del_friend);
                TextView laheifriend = inflate.findViewById(R.id.lahei_friend);
                //点击删除好友
                delfriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getDeleteFriendData(userId, sessionId, friendByGroupIdBean.getResult().get(position).getFriendUid());
                        observable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<AddIngFriendBean>() {
                                    @Override
                                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                                        if (addIngFriendBean.getStatus().equals("0000")){
                                            Toast.makeText(mContext, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
                                            onAddGroupClickListener.onDelFriend();
                                        }
                                    }
                                });
                    }
                });
                //点击拉黑好友
                laheifriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Observable<AddIngFriendBean> observable = RetrofitUtils.getInstance().create(ApiService.class).getLaHeiFriendData(userId, sessionId, groupId, friendByGroupIdBean.getResult().get(position).getFriendUid());
                        observable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<AddIngFriendBean>() {
                                    @Override
                                    public void accept(AddIngFriendBean addIngFriendBean) throws Exception {
                                        if (addIngFriendBean.getStatus().equals("0000")){
                                            Toast.makeText(mContext, addIngFriendBean.getMessage(), Toast.LENGTH_SHORT).show();
                                            //接口回调
                                            onAddGroupClickListener.moveFriend();
                                        }
                                    }
                                });
                    }
                });
                popupWindow.showAsDropDown(view);
                return true;
            }
        });
        friendByGroupIdAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, FriendMessageByGroupActivity.class);
                intent.putExtra("friendId",friendByGroupIdBean.getResult().get(position).getFriendUid());
                mContext.startActivity(intent);
            }
        });
    }



}
