package cn.ucai.superwechat.net;

import android.content.Context;

import com.hyphenate.easeui.domain.User;

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
}
