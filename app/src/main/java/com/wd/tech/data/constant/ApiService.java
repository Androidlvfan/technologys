package com.wd.tech.data.constant;

import com.wd.tech.data.bean.AddCollectBean;
import com.wd.tech.data.bean.BannerBean;
import com.wd.tech.data.bean.AddCommunityBean;
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
import com.wd.tech.data.bean.CommunityBean;
import com.wd.tech.data.bean.DealBean;
import com.wd.tech.data.bean.DelInvitationBean;
import com.wd.tech.data.bean.InvitationBean;
import com.wd.tech.data.bean.LikeBean;
import com.wd.tech.data.bean.LoginBean;
import com.wd.tech.data.bean.NoLikeBean;
import com.wd.tech.data.bean.DetailCommentBean;
import com.wd.tech.data.bean.DetailOkBean;
import com.wd.tech.data.bean.MenuBean;
import com.wd.tech.data.bean.RegisterBean;
import com.wd.tech.data.bean.ViewMoreBean;
import com.wd.tech.data.bean.WxBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.DELETE;
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
    //首页Banner
    @GET("techApi/information/v1/bannerShow")
    Observable<BannerBean> getBanner();
    //添加好友 根据手机号查询信息

    //添加好友 根据手机号好友查询信息
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

    @GET("techApi/information/v1/infoRecommendList")
    Observable<ShowBean> getShow(@QueryMap HashMap<String,String> params,
                                 @Header("userId") String userId,
                                 @Header("sessionId") String sessionId
    );
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

    //添加收藏
    @POST("techApi/user/verify/v1/addCollection")
    Observable<AddCollectBean> getAddCollection(@Query("infoId") String infoId, @Header("userId") int userId, @Header("sessionId") String sessionId);

}
