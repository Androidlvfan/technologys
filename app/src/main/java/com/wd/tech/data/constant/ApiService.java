package com.wd.tech.data.constant;

import com.wd.tech.data.bean.AddCommunityBean;
import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.bean.DealBean;
import com.wd.tech.data.bean.DelInvitationBean;
import com.wd.tech.data.bean.InvitationBean;
import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.data.bean.RegisterBean;
import com.wd.tech.data.bean.ViewMoreBean;
import com.wd.tech.data.bean.WxBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    @FormUrlEncoded
    @POST("techApi/user/v1/weChatLogin")
    Observable<WxBean> getWx(@Header("userId") int userId, @Header("sessionId") String sessionId,@Header("ak") String ak, @Field("code") String code);

    //发布帖子
    @FormUrlEncoded
    @POST("techApi/community/verify/v1/releasePost")
    Observable<DealBean> getReleasePost(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                        @Field("content") String content, @Body MultipartBody multipartBody);

    // 删除帖子 community/verify/v1/deletePost
    @DELETE("techApi/community/verify/v1/deletePost")
    Observable<DelInvitationBean> getDeletePost(@Header("userId") long userid,
                                             @Header("sessionId") String sessionld,
                                             @Query("communityId") String communityId);

    //加群  查询群根据群id
    @GET("techApi/group/verify/v1/findGroupInfo")
    Observable<AddGroupBean> getGroupIdData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("groupId")int groupId);

    //社区列表展示
    @GET("techApi/community/v1/findCommunityList")
    Observable<CommunityBean> getCommunity(@Query("page") int page, @Query("count") int count);

    //社区用户评论列表（bean方式返参）
    @GET("techApi/community/v1/findCommunityUserCommentList")
    Observable<ViewMoreBean> getViewMore(@Query("communityId") int communityId,@Query("page") int page, @Query("count") int count);

    //社区评论
    @POST("techApi/community/verify/v1/addCommunityComment")
    @FormUrlEncoded
    Observable<AddCommunityBean> getAddCommunity(@Header("userId") int userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Field("communityId") int communityId,
                                                 @Field("content") String content);

    //发布帖子
    @POST("community/verify/v1/releasePost")
    Observable<InvitationBean> getInvita(@Header("userId") int userId,
                                          @Header("sessionId") String sessionId,
                                          @Query("content") String content,
                                          @Body MultipartBody multipartBody);
    //点赞成功
    @FormUrlEncoded
    @POST("techApi/community/verify/v1/addCommunityGreat")
    Observable<LikeBean> getLike(@Header("userId") int userId,
                                 @Header("sessionId") String sessionId,
                                 @Field("communityId") int communityId);
    //取消点赞
    @DELETE("techApi/community/verify/v1/cancelCommunityGreat")
    Observable<NoLikeBean> getNoLike(@Header("userId") int userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("communityId") int communityId);
}
