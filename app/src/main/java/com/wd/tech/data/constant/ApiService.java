package com.wd.tech.data.constant;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:29
 * @fileName:ApiService
 * @packageName:com.wd.tech.dimensionalitytechnology.data.constant
 */
public interface ApiService {
    //注册
    @FormUrlEncoded
    @POST("techApi/user/v1/register")
    Observable<RegisterBean> getRegisterData(@Field("phone")String phone,@Field("nickName")String nickName,@Field("pwd")String pwd);
    //登陆
    @FormUrlEncoded
    @POST("techApi/user/v1/login")
    Observable<LoginBean> getLogin(@Field("phone") String phone, @Field("pwd") String pwd);

    //添加好友 根据手机号查询信息
    @GET("techApi/user/verify/v1/findUserByPhone")
    Observable<AddFriendBean> getAddFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("phone")String phone);

    //加群  查询群根据群id
    @GET("techApi/group/verify/v1/findGroupInfo")
    Observable<AddGroupBean> getGroupIdData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("groupId")int groupId);

}
