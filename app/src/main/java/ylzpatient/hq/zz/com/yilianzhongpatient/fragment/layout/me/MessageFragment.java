package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.BackEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.CircleTransform;

/**
 * Created by Administrator on 2016-8-31.
 */
public class MessageFragment extends SuperFragment {

    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.text_login_name)
    TextView textLoginName;
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.text_user_idcard)
    TextView textUserIdcard;
    @BindView(R.id.text_user_number)
    TextView textUserNumber;
    @BindView(R.id.text_user_email)
    TextView textUserEmail;
    @BindView(R.id.text_login_weixin)
    TextView textLoginWeixin;
    @BindView(R.id.text_login_weibo)
    TextView textLoginWeibo;
    @BindView(R.id.text_login_qq)
    TextView textLoginQq;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.img_user)
    ImageView imgUser;

    private UserTestBean_2 user;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.id_message_layout, container, false);
        ButterKnife.bind(this, v);
        user = UserManager.getInstance().getCurrentUser();
        initView();

        addClick();
        return v;
    }

    /**
     * 加载界面的点击事件
     */
    private void addClick() {
        //左上角的返回图标点击
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //退出登录
        RxView.clicks(tvBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showDialog();
                    }
                });

    }

    private void initView() {
        textViewName.setText("账号信息");
        textLoginName.setText(user.getLoginName());
        textUserName.setText(user.getName());
        textUserIdcard.setText(user.getCardNo());
        textUserNumber.setText(user.getMobile());
        textUserEmail.setText(user.getEmail());
        String s = user.getHead();
        Log.i(GeneralConfig.LOG_TAG, "患者图片的URL:" + s);
        if (!TextUtils.isEmpty(user.getHead())) { //判断患者图片的url是否为空,为空的话会导致程序崩溃
            Picasso.with(getContext())
                    .load(user.getHead()) //患者图片
                    .transform(new CircleTransform()) //圆
                    .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                    .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                    .tag(MessageFragment.class.getName())
                    .resize(300, 300) //图片宽高
                    .centerCrop()
                    .into(imgUser);
        }
    }

    /**
            * 这是兼容的 AlertDialog
    */
    private void showDialog() {
  /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("确认退出?");
        builder.setMessage("是否确认退出登录");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getFragmentManager().popBackStack();
                UserManager.getInstance().setSystemUser(null);
                EventBus.getDefault().post(new BackEvent(2));
            }
        });
        builder.show();
    }
}
