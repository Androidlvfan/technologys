package com.wd.tech.data.constant;

import com.wd.tech.data.bean.AddFriendBean;
import com.wd.tech.data.bean.AddFriendGroupBean;
import com.wd.tech.data.bean.AddGroupBean;
import com.wd.tech.data.bean.AddIngFriendBean;
import com.wd.tech.data.bean.ChatRecordBean;
import com.wd.tech.data.bean.CheckMyFriendBean;
import com.wd.tech.data.bean.CreateGroupBean;
import com.wd.tech.data.bean.FriendByGroupIdBean;
import com.wd.tech.data.bean.FriendChatRecordBean;
import com.wd.tech.data.bean.FriendGroupBean;
import com.wd.tech.data.bean.FriendNotifyBean;
import com.wd.tech.data.bean.GroupBean;
import com.wd.tech.data.bean.GroupChatRecordBean;
import com.wd.tech.data.bean.GroupNotifyBean;
import com.wd.tech.data.bean.QueryFriendMessageBean;
import com.wd.tech.data.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.RegisterBean;

import java.security.acl.Group;

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
    //审核好友申请
    @PUT("techApi/chat/verify/v1/reviewFriendApply")
    Observable<AddIngFriendBean> getReviewFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("noticeId") int noticeId,@Query("flag")int flag);

    //查询用户的所有分组
    @GET("techApi/chat/verify/v1/findFriendGroupList")
    Observable<FriendGroupBean> getFriendGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId);


    //查询分组下的好友列表
    @GET("techApi/chat/verify/v1/findFriendListByGroupId")
    Observable<FriendByGroupIdBean> getFriendByGroupIdData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                           @Query("groupId")int groupId);

    //创建新的好友分组
    @FormUrlEncoded
    @POST("techApi/chat/verify/v1/addFriendGroup")
    Observable<AddFriendGroupBean> getAddFriendGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                         @Field("groupName") String groupName);

    //修改好友分组的名字
    @PUT("techApi/chat/verify/v1/modifyGroupName")
    Observable<AddIngFriendBean> getUpdGroupNameData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("groupId")int groupId,@Query("groupName") String groupName);

    //删除好友分组
    @DELETE("techApi/chat/verify/v1/deleteFriendGroup")
    Observable<AddIngFriendBean> getDeleteGroupData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("groupId")int groupId);

    //删除好友
    @DELETE("techApi/chat/verify/v1/deleteFriendRelation")
    Observable<AddIngFriendBean> getDeleteFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                    @Query("friendUid")int friendUid);

    //拉黑好友  移动好友到别的分组
    @PUT("techApi/chat/verify/v1/transferFriendGroup")
    Observable<AddIngFriendBean> getLaHeiFriendData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                 @Query("newGroupId") int newGroupId,
                                                 @Query("friendUid")int friendUid);

    //修改好友备注
    @PUT("techApi/chat/verify/v1/modifyFriendRemark")
    Observable<AddIngFriendBean> getUpdBeiZhuData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                     @Query("friendUid")int friendUid,@Query("remarkName")String remarkName);

    //查询好友聊天记录
    @GET("techApi/chat/verify/v1/findChatRecordPageList")
    Observable<ChatRecordBean> getChatRecordData(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                 @Query("friendUid")int friendUid, @Query("page")int page, @Query("count")int count);

    //发送群消息
    @FormUrlEncoded
    @POST("techApi/group/verify/v1/sendGroupMessage")
    Observable<AddIngFriendBean> SendGroupMessage(@Header("userId") int userId, @Header("sessionId") String sessionId
            ,@Field("groupId")int groupId,@Field("content")String content);

    //查询群聊天内容
    @GET("techApi/group/verify/v1/findGroupChatRecordPage")
    Observable<GroupChatRecordBean> getGroupChatRecordData(@Header("userId") int userId, @Header("sessionId") String sessionId ,
                                                           @Query("groupId")int groupId, @Query("page")int page, @Query("count")int count);

    //查询好友对话记录
    @GET("techApi/chat/verify/v1/findDialogueRecordPageList")
    Observable<FriendChatRecordBean> getFriendChatRecordData(@Header("userId") int userId, @Header("sessionId") String sessionId ,
                                                             @Query("friendUid")int friendUid, @Query("page")int page, @Query("count")int count);


    //发送消息
    @FormUrlEncoded
    @POST("techApi/chat/verify/v1/sendMessage")
    Observable<AddIngFriendBean> SendFriendMessage(@Header("userId") int userId, @Header("sessionId") String sessionId
            ,@Field("receiveUid")int receiveUid,@Field("content")String content);

}
