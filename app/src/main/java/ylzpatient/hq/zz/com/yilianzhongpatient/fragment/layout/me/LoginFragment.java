package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.LoginController;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.LoginBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.BackEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
 * 登录的fragment
 */
public class LoginFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @BindView(R.id.login_id)
    EditText loginId;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.checkbox_pass_watch)
    CheckBox checkboxPassWatch;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForget;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.ll_login_top)
    LinearLayout llTop;

    private LoginBean loginer;
    private LoginController loginCon;
    private InputMethodManager imm;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        return view;
    }

    private void initView() {
        loginer = new LoginBean();
        loginCon = new LoginController(getContext());
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        llTop.setBackgroundResource(R.color.transparent);
        textViewName.setText("登录");
    }

    //界面的点击事件
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //登录按钮的点击事件,使用RxBinding 与RxAndroid
        RxView.clicks(loginBtn)
                .throttleFirst(1, TimeUnit.SECONDS) //防止手抖点击多次登录按钮
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        loginTest();
                    }
                });

        /**
         * 密码框内容是否可见
         */
        RxCompoundButton.checkedChanges(checkboxPassWatch)
                //.throttleFirst(666, TimeUnit.MILLISECONDS) //防止手抖点击多次登录按钮
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        //判断是否要显示密码
                        int type = aBoolean ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        //设置密码输入框是否显示明文
                        loginPassword.setInputType(type);
                    }
                });

        //密码输入框的软键盘,完成 点击事件
        loginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.login || actionId == EditorInfo.IME_ACTION_DONE) {
                    loginTest();
                    imm.hideSoftInputFromWindow(loginPassword.getWindowToken(), 0); //强制隐藏键盘

                    return true;
                }
                return false;
            }
        });
        //忘记密码
        RxView.clicks(tvForget)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //跳转到忘记密码界面
                        switchFatherContent(LoginFragment.this, ForgetPwdFragment.newInstance());
                    }
                });
        //注册
        RxView.clicks(tvRegister)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //跳转到注册界面
                        switchFatherContent(LoginFragment.this, RegisterFragment.newInstance());
                    }
                });
    }

    /**
     * 登录的方法,正在用的登录方法
     */
    private void loginTest() {
        Subscriber<UserTestBean_2> subscriber = new Subscriber<UserTestBean_2>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(container, "登录出现异常", 2500).show();
                Log.e(GeneralConfig.LOG_TAG, "登录出现异常:" + e.toString());

            }

            @Override
            public void onNext(UserTestBean_2 user) {
                if (!Uitls.isEmptyClazz(user)) {
                    UserManager.getInstance().setSystemUser(user);
                    Log.i(GeneralConfig.LOG_TAG, "登录成功.登陆者:" + user.getLoginName());
                    EventBus.getDefault().post(new BackEvent(2));
                    //登录成功,界面跳转
                    getFragmentManager().popBackStack();
                } else {
                    Snackbar.make(container, "登录失败", 2500).show();
                    Log.i(GeneralConfig.LOG_TAG, "登录失败,返回的user信息为空");

                }
            }
        };
        if (checkIdPassword()) {
            HttpMethods.getInstance().loginTestService(subscriber, loginer);
        }

    }

    /**
     * 判断输入的id和密码是否为空
     * 不为空返回ture 可以登录
     * 空返回false 不允许登录
     *
     * @return
     */
    private boolean checkIdPassword() {
        String id = loginId.getText().toString();
        String password = loginPassword.getText().toString();
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(password)) {
            loginer.setName(id);
            loginer.setPwd(password);
            Log.i(GeneralConfig.LOG_TAG, "账号密码验证通过可以登录");
            return true;
        } else {
            Snackbar.make(loginBtn, "账号或者密码不能为空", 2500).show();
            Log.i(GeneralConfig.LOG_TAG, "账号密码验证失败,格式不正确");
            return false;
        }
    }


}
