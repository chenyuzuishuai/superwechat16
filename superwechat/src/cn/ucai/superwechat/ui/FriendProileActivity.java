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
import cn.ucai.superwechat.utils.MFGT;

public class FriendProileActivity extends AppCompatActivity {
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
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user != null) {
            showUserInfo(user);
        } else {
            MFGT.finish(this);
        }
    }

    private void showUserInfo(User user) {
        tvUserinfoNick.setText(user.getMUserNick());
        tvUserinfoName.setText("微信号 " + user.getMUserName());
        EaseUserUtils.setAPPUserAvatarByPath(this, user.getAvatar(),profileImage );
        isFriend();
    }

    private void isFriend() {
        User u = SuperWeChatHelper.getInstance().getAPPContactList().get(user.getMUserName());
        if (u==null){
            btnSendMsg.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        }else {
            btnAddContact.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        MFGT.finish(this);
    }
}
