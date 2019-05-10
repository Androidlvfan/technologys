package com.wd.tech.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:46
 * @fileName:BaseFragment
 * @packageName:com.wd.tech.dimensionalitytechnology.ui.fragment
 */
public abstract class BaseFragment extends Fragment {

    //当前依附的Activity
    private FragmentActivity fragmentActivity;

    //根布局
    private View view;

    //是否对用户可见
    protected boolean isVisible;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutResouceId(), container, false);
        initData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //得到fragment状态,是否与用户交互
        this.isVisible = isVisibleToUser;
        if (isVisibleToUser){
            onVisibleToUser();
        }
    }

    //用户可见时的操作
    protected void onVisibleToUser()
    {
        if (mIsPrepare && isVisible)
        {
            onLazyLoad();
        }
    }
    //懒加载，仅当用户可见切view初始化结束后才会执行
    protected abstract void onLazyLoad();
    //布局
    protected abstract int setLayoutResouceId();
    //初始化数据
    protected abstract void initData();
}
