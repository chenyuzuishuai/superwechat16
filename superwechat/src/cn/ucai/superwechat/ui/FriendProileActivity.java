package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.domain.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.net.OnCompleteListener;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class FriendProileActivity extends BaseActivity {
    User user;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.tv_userinfo_nick)
    TextView tvUserinfoNick;
    @BindView(R.id.tv_userinfo_name)
    TextView tvUserinfoName;
    @BindView(R.id.txt_note_mark)
    TextView txtNoteMark;
    @BindView(R.id.txt_left)
    TextView txtLeft;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.view_user)
    RelativeLayout viewUser;
    @BindView(R.id.btn_add_contact)
    Button btnAddContact;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.btn_send_video)
    Button btnSendVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_proile);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText("个人信息");
        user = (User) getIntent().getSerializableExtra(I.User.TABLE_NAME);
        if (user != null) {
            showUserInfo();
        } else {
            String username = getIntent().getStringExtra(I.User.USER_NAME);
            if (username==null) {
                MFGT.finish(this);
            }else {
             syncUserInfo(username);
            }
        }
    }

    private void syncUserInfo(String username) {
        NetDao.getUserInfoByUsername(this, username, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s!=null){
                    Result result = ResultUtils.getResultFromJson(s,User.class);
                    if (result!=null){
                        if (result.isRetMsg()){
                            User u = (User) result.getRetData();
                            if (u!=null){
                                user = u;
                                showUserInfo();
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void showUserInfo() {
        tvUserinfoNick.setText(user.getMUserNick());
        tvUserinfoName.setText("微信号 " + user.getMUserName());
        EaseUserUtils.setAPPUserAvatarByPath(this, user.getAvatar(), profileImage);
        if (isFriend()){
            btnSendMsg.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        }else {
            btnAddContact.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFriend() {
        User u = SuperWeChatHelper.getInstance().getAPPContactList().get(user.getMUserName());
        if (u == null) {
           return false;
        } else {
            SuperWeChatHelper.getInstance().saveAPPContact(user);
            return true;
        }
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        MFGT.finish(this);
    }

    @OnClick({R.id.btn_add_contact, R.id.btn_send_msg, R.id.btn_send_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_contact:
                MFGT.gotoAddFriendMsg(this,user.getMUserName());
                break;
            case R.id.btn_send_msg:
                break;
            case R.id.btn_send_video:
                break;
        }
    }
}
