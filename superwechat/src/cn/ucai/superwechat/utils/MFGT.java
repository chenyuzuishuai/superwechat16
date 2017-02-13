package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.GuideActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;


/**
 * 页面的跳转
 */
public class MFGT {
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public static void startActivity(Activity context, Class<?> cls) {
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        context.startActivity(new Intent(context, cls));
    }

    public static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    //前往欢迎页面
    public static void gotoGuide(Activity activity) {
        startActivity(activity, GuideActivity.class);
    }

    //  前往登录页面
    public static void gotoLogin(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }

    //前往注册页面
    public static void gotoRegister(Activity activity) {
        startActivity(activity, RegisterActivity.class);
    }

    //前往设置页面
    public static void gotoSettings(FragmentActivity activity) {
        startActivity(activity, SettingsActivity.class);
    }

    //清空Task方法
    public static void gotoLoginCleanTask(Activity activity) {
        startActivity(activity, new Intent(activity, LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    //前往个人中心界面
    public static void gotoUserProfile(FragmentActivity activity) {
        startActivity(activity, UserProfileActivity.class);
    }

    //前往添加好友界面
    public static void gotoAddContact(Activity activity) {
        startActivity(activity, AddContactActivity.class);
    }
}
