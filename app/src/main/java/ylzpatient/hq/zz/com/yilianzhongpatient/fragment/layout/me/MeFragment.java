package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.BackEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.CircleTransform;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-8-24.
 */
public class MeFragment extends SuperFragment {
    @BindView(R.id.user_layout)
    LinearLayout userLayout;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.text_user_name)
    TextView textUserName;

    @BindView(R.id.rl_card)
    RelativeLayout rlCard;
    @BindView(R.id.tv_renzheng)
    TextView tvRenzheng;
    @BindView(R.id.rl_impower)
    RelativeLayout rlImpower;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    private UserTestBean_2 user;

    public interface SwitchFragListener {
        void onSwitchFrag(Fragment fragment);
    }

    public static MeFragment newInstance(Fragment f) {
        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        fragment.setTargetFragment(f, 300);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.me_layout, container, false);
        ButterKnife.bind(this, v);
        textViewName.setText("我的");
        initView();
        addClick();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private void initView() {
        user = UserManager.getInstance().getCurrentUser();
        if (!Uitls.isEmptyClazz(user)) {
            textUserName.setText(user.getLoginName());
            tvRenzheng.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(user.getHead())) { //判断患者图片的url是否为空,为空的话会导致程序崩溃
                Picasso.with(getContext())
                        .load(user.getHead()) //患者图片
                        .transform(new CircleTransform()) //圆
                        .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                        .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                        .tag(MeFragment.class.getName())
                        .resize(300, 300) //图片宽高
                        .centerCrop()
                        .into(imgUser);
            }
        } else {
            textUserName.setText("立即登录");
            tvRenzheng.setVisibility(View.GONE);
            Picasso.with(getContext())
                    .load(R.mipmap.body_bg_photo) //患者图片
                    .transform(new CircleTransform()) //圆
                    .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                    .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                    .tag(MeFragment.class.getName())
                    .resize(300, 300) //图片宽高
                    .centerCrop()
                    .into(imgUser);
        }

        tvVersionCode.setText("当前版本:"+Uitls.getVersion());
    }

    private void addClick() {
        //姓名的点击事件
        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
                    //跳转到登录界面
                    EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));

                } else {
                    //跳转到我的详情界面
                    sendBackResult();
                }
            }
        });

        //就诊卡
        RxView.clicks(rlCard)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        changeView(CardFragment.newInstance());

                    }
                });
        //授权管理
        RxView.clicks(rlImpower)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        changeView(AuthorizeFragment.newInstance());
                    }
                });
    }

    //这个界面通用的点击跳转界面的方法
    private void changeView(SuperFragment toFragment) {
        //如果没有登录
        if (Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
            //跳转到登录界面
            EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));
        } else {
            //跳转到新的界面
            EventBus.getDefault().post(new MainSwitchEvent(toFragment));
        }
    }

    //接收退出登录后的信息
    @Subscribe
    public void onSymptom(BackEvent event) {
        if (event.type == 2) {
            initView();
        }
    }

    public void sendBackResult() {
        SwitchFragListener listener = (SwitchFragListener) getTargetFragment();
        listener.onSwitchFrag(MessageFragment.newInstance());
    }


}
