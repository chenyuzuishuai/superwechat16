package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.GuideActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;


/**
 * �����л�Activityʱ�Ķ���
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
}
