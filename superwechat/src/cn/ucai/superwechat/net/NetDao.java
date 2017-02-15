package cn.ucai.superwechat.net;

import android.content.Context;

import com.hyphenate.easeui.domain.User;

import java.io.File;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.utils.MD5;
import cn.ucai.superwechat.utils.OkHttpUtils;

/**
 * Created by yu chen on 2017/2/8.
 */

public class NetDao {
    /**
     * 注册请求.Post请求
     */
    public static void register(Context context,String username,String nick,String password,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    /**
     * 取消注册
     */
    public static void unregister(Context context,String  username,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 登录
     */
   public static void login(Context context, String  username, String password,
                            OnCompleteListener<String> listener){
       OkHttpUtils<String> utils = new OkHttpUtils<>(context);
       utils.setRequestUrl(I.REQUEST_LOGIN)
               .addParam(I.User.USER_NAME,username)
               .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))
               .targetClass(String.class)
               .execute(listener);
   }

    /**
     * 获取用户信息
     */
    public static void getUserInfoByUsername(Context context,String username,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 更新用户昵称
     */
    public static void updateUsernick(Context context, String username , String usernick,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,usernick)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 更新用户头像
     */
    public static void uploadAPPUserAvatar(Context context, String username, File file, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,username)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    /**
     * 添加好友
     */
    public static void addContact(Context context, String username,String cname,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME,username)
                .addParam(I.Contact.CU_NAME,cname)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 从服务器下载所有好友信息
     */
    public static void loadContact(Context context, String username ,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST )
                .addParam(I.Contact.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }
}
