package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RegisterBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Code;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 * 注册界面
 */
public class RegisterFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.tiet_uesr_id)
    TextInputEditText tietUesrId;
    @BindView(R.id.til_uesr_id)
    TextInputLayout tilUesrId;
    @BindView(R.id.tiet_pwd)
    TextInputEditText tietPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.tiet_img_code)
    TextInputEditText tietImgCode;
    @BindView(R.id.til_img_code)
    TextInputLayout tilImgCode;
    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.tiet_phone_number)
    TextInputEditText tietPhoneNumber;
    @BindView(R.id.til_phone_number)
    TextInputLayout tilPhoneNumber;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.tiet_code)
    TextInputEditText tietCode;
    @BindView(R.id.til_code)
    TextInputLayout tilCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;

    private RegisterBean register;
    private int restTime = 60;
    private Subscription subscribe;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        return view;
    }

    private void initView() {
        register = new RegisterBean();
        textViewName.setText("注册");

        imgCode.setImageBitmap(Code.getInstance().createBitmap());
    }

    private void addClick() {

        //重新生成图片验证码
        RxView.clicks(imgCode)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        imgCode.setImageBitmap(Code.getInstance().createBitmap());
                    }
                });
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //发送验证码
        RxView.clicks(tvSendCode)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mInterval();
                    }
                });

        //发送手机验证码的textview内容变化的监听
        RxTextView.afterTextChangeEvents(tvSendCode)
                //.debounce(1500,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textAfterChangeEvent) {
                        String s = textAfterChangeEvent.view().getText().toString();
                        if (restTime <= 0) {
                            tvSendCode.setClickable(true);
                            restTime = 60;
                            subscribe.unsubscribe();
                        }
                    }
                });
        //注册
        RxView.clicks(btnRegister)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .flatMap(new Func1<Void, Observable<ResquestResult<String>>>() {
                    @Override
                    public Observable<ResquestResult<String>> call(Void aVoid) {
                        if (checkInformation()) {
                            register.setLoginName(tietUesrId.getText().toString());
                            register.setLoginPwd(tietPwd.getText().toString());
                            register.setMobile(tietPhoneNumber.getText().toString());
                            return HttpMethods.getInstance().register(register);
                        } else {
                            return null;
                        }
                    }
                })
                .subscribe(new Observer<ResquestResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(btnRegister,"注册失败",3000).show();
                    }

                    @Override
                    public void onNext(ResquestResult<String> stringResult) {
                        String s = "";
                        if (stringResult != null && TextUtils.isEmpty(stringResult.ResultCode)) {
                            s = "注册成功";
                            getFragmentManager().popBackStack();
                        } else {
                            s = "注册失败,请稍后再试";
                        }
                        Snackbar.make(btnRegister,s,3000).show();
                    }
                });

    }

    /**
     * 检查注册信息
     * @return
     */
    private boolean checkInformation() {
        String s = "";
        boolean result = false;
        if (TextUtils.isEmpty(tietUesrId.getText().toString())) {
            s += "用户名不能为空!";
        } else if (TextUtils.isEmpty(tietPwd.getText().toString())) {
            s += "密码不能为空!";
        } else if (tietPwd.getText().toString().length()<6){
            s += "密码不能少于六位!";
        } else if (TextUtils.isEmpty(tietCode.getText().toString())) {
            s += "验证码不能为空!";
        } else if (TextUtils.isEmpty(tietImgCode.getText().toString())) {
            s += "图片验证码不能为空!";
        } else if (!tietImgCode.getText().toString().equalsIgnoreCase(Code.getInstance().getCode())) {
            s += "图片验证码输入有误";
        } else if (TextUtils.isEmpty(tietPhoneNumber.getText().toString())){
            s += "手机号不能为空";
        } else if (!tietPhoneNumber.getText().toString().matches("[1]\\d{10}")){
            s += "手机号格式错误";
        } else if (!tietCode.getText().toString().equals("8888")) {
            s += "数字验证码输入有误";
        }
        else {
            result = true;
        }
        if (!result) {
            Snackbar.make(btnRegister,s,3000).show();
        }
        return result;
    }

    //循环操作
    private void mInterval() {
        if (subscribe == null || subscribe.isUnsubscribed()) {
            subscribe = Observable.interval(1, TimeUnit.SECONDS)
                    //延时0 ，每间隔1，时间单位
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            restTime -= 1;
                            String s = "";
                            if (restTime >=0) {
                                s = "剩余时间"+restTime+"秒";
                                tvSendCode.setClickable(false);
                            } else {
                                s = "重新发送";
                                tvSendCode.setClickable(true);

                            }
                            tvSendCode.setText(s);
                        }
                    });
        }
    }

}
