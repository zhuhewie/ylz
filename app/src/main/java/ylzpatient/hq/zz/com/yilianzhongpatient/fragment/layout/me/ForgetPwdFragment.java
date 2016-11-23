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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Code;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ForgetPwdFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 忘记密码界面
 */
public class ForgetPwdFragment extends SuperFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tiet_phone_number)
    TextInputEditText tietPhoneNumber;
    @BindView(R.id.til_phone_number)
    TextInputLayout tilPhoneNumber;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.tiet_number_code)
    TextInputEditText tietNumberCode;
    @BindView(R.id.til_number_code)
    TextInputLayout tilNumberCode;
    @BindView(R.id.tiet_img_code)
    TextInputEditText tietImgCpde;
    @BindView(R.id.til_img_code)
    TextInputLayout tilImgCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.ll_forget_pwd)
    LinearLayout llForgetPwd;
    @BindView(R.id.img_code)
    ImageView imgCode;

    private int restTime = 60;
    private Subscription subscribe;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ForgetPwdFragment.
     */
    public static ForgetPwdFragment newInstance() {
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_pwd, container, false);
        ButterKnife.bind(this, view);


        initView();

        addClick();
        return view;
    }

    private void initView() {
        textViewName.setText("忘记密码");
        imgCode.setImageBitmap(Code.getInstance().createBitmap());

    }

    private void addClick() {
        //下一步
        RxView.clicks(btnNext)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (checkInformation()) {
                            switchFatherContent(ForgetPwdFragment.this, ResetPwdFragment.newInstance(tietPhoneNumber.getText().toString()));
                        }
                    }
                });
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
        //发送验证码点击事件
        RxView.clicks(tvSendCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void v) {
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
                            if (restTime >= 0) {
                                s = "剩余时间" + restTime + "秒";
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

    //验证输入信息
    private boolean checkInformation() {
        String s = "";
        boolean result = false;
        if (TextUtils.isEmpty(tietPhoneNumber.getText().toString())) {
            s += "手机号不能为空!";
        } else if (!tietPhoneNumber.getText().toString().matches("[1]\\d{10}")){
            s += "手机号格式错误";
        } else if (!tietNumberCode.getText().toString().equals("8888")) {
            s += "数字验证码输入有误";
        } else if (!tietImgCpde.getText().toString().equalsIgnoreCase(Code.getInstance().getCode())) {
            s += "图片验证码输入有误";
        } else {
            result = true;
        }
        if (!result) {
            Snackbar.make(btnNext,s,3000).show();
        }
        return result;
    }
}
