package com.wd.tech.data.constant;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.CreateGroupBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.data.bean.GroupNotifyBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.data.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


import com.wd.tech.data.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    //添加好友 根据手机号好友查询信息
    @GET("techApi/user/verify/v1/findUserByPhone")
    Observable<AddFriendBean> getAddFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("phone")String phone);

    //加群  查询群根据群id
    @GET("techApi/group/verify/v1/findGroupInfo")
    Observable<AddGroupBean> getGroupIdData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("groupId")int groupId);

    //查询好友信息
    @GET("techApi/user/verify/v1/queryFriendInformation")
    Observable<QueryFriendMessageBean> getFriendMessageData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("friend")int friend);

    //检测是否为我的好友
    @GET("techApi/chat/verify/v1/checkMyFriend")
    Observable<CheckMyFriendBean> getCheckFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("friendUid")int friendUid);

    //添加好友
    @FormUrlEncoded
    @POST("techApi/chat/verify/v1/addFriend")
    Observable<AddIngFriendBean> getAddIngFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Field("friendUid") int friendUid,@Field("remark") String remark);

    //判断用户是否在群内
    @GET("techApi/group/verify/v1/whetherInGroup")
    Observable<CheckMyFriendBean> getWhetherInGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("groupId") int groupId);

    //申请加群
    @FormUrlEncoded
    @POST("techApi/group/verify/v1/applyAddGroup")
    Observable<AddIngFriendBean> getAddIngGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Field("groupId") int groupId,@Field("remark") String remark);

    //创建群
    @FormUrlEncoded
    @POST("techApi/group/verify/v1/createGroup")
    Observable<CreateGroupBean> getCreateGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                   @Field("name") String name, @Field("description") String description);

    //我创建的群组
    @GET("techApi/group/verify/v1/findGroupsByUserId")
    Observable<GroupBean> getGroupsByUserData(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //我加入的群组
    @GET("techApi/group/verify/v1/findUserJoinedGroup")
    Observable<GroupBean> getJoinedGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //群通知记录
    @GET("techApi/group/verify/v1/findGroupNoticePageList")
    Observable<GroupNotifyBean> getGroupNotifyData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                   @Query("page")int page,@Query("count")int count);

    //审核群申请
    @PUT("techApi/group/verify/v1/reviewGroupApply")
    Observable<AddIngFriendBean> getReviewGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                    @Query("noticeId") int noticeId,@Query("flag")int flag);

    //好友通知记录
    @GET("techApi/chat/verify/v1/findFriendNoticePageList")
    Observable<FriendNotifyBean> getFriendNotifyData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("page")int page,@Query("count")int count);

    @PUT("techApi/chat/verify/v1/reviewFriendApply")
    Observable<AddIngFriendBean> getReviewFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("noticeId") int noticeId,@Query("flag")int flag);

    //查询用户的所有分组
    @GET("techApi/chat/verify/v1/findFriendGroupList")
    Observable<GroupBean> getFriendGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId);
}
