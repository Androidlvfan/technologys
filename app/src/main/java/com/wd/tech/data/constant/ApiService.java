package com.wd.tech.data.constant;

import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;
import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.data.bean.RegisterBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.SerachByTitleBean;
import com.wd.tech.data.bean.ShowBean;

import java.util.HashMap;

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
    //首页Banner
    @GET("techApi/information/v1/bannerShow")
    Observable<BannerBean> getBanner();
    //添加好友 根据手机号查询信息
    @GET("techApi/user/verify/v1/findUserByPhone")
    Observable<AddFriendBean> getAddFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("phone")String phone);

    @GET("techApi/information/v1/infoRecommendList")
    Observable<ShowBean> getShow(@QueryMap HashMap<String,String> params,
                                 @Header("userId") String userId,
                                 @Header("sessionId") String sessionId
    );
    //加群  查询群根据群id
    @GET("techApi/group/verify/v1/findGroupInfo")
    Observable<AddGroupBean> getGroupIdData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("groupId")int groupId);
    //首页Menu
    @GET("techApi/information/v1/findAllInfoPlate")
    Observable<MenuBean> getMenu();
    //详情展示
    @GET("techApi/information/v1/findInformationDetails")
    Observable<DetailOkBean> getDetailOk(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("id") int id);
    //评论列表展示
    @GET("techApi/information/v1/findAllInfoCommentList")
    Observable<DetailCommentBean> getDetailComent(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("infoId")int infoId,@QueryMap HashMap<String,String> params);
    //根据标题模糊查询
    @GET("techApi/information/v1/findInformationByTitle")
    Observable<SerachByTitleBean> getSearchByTitle(@Query("title") String title,@QueryMap HashMap<String,String> params);
}
