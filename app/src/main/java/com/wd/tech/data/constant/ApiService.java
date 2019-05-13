package com.wd.tech.data.constant;

import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.RegisterBean;
import com.wd.tech.data.bean.WxBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @FormUrlEncoded
    @POST("techApi/user/v1/register")
    Observable<RegisterBean> getRegisterData(@Field("phone")String phone,@Field("nickName")String nickName,@Field("pwd")String pwd);

    @FormUrlEncoded
    @POST("techApi/user/v1/login")
    Observable<LoginBean> getLogin(@Field("phone") String phone, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("techApi/user/v1/weChatLogin")
    Observable<WxBean> getWx(@Header("ak") String ak, @Field("code") String code);

    @GET("techApi/community/v1/findCommunityList")
    Observable<CommunityBean> getCommunity(@Query("page") int page, @Query("count") int count);

}
