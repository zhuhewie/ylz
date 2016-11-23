package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UpdataUserInfoBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResetPwdFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 重新设置密码界面
 */
public class ResetPwdFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PHONE = "param1";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tiet_new_pwd)
    TextInputEditText tietNewPwd;
    @BindView(R.id.til_new_pwd)
    TextInputLayout tilNewPwd;
    @BindView(R.id.tiet_pwd_sure)
    TextInputEditText tietPwdSure;
    @BindView(R.id.til_pwd_sure)
    TextInputLayout tilPwdSure;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.activity_reset_pwd)
    LinearLayout activityResetPwd;

    private UpdataUserInfoBean updataUser;
    private String phone;//手机号
    private InputMethodManager imm;



    public ResetPwdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ResetPwdFragment.
     */
    public static ResetPwdFragment newInstance(String param1) {
        ResetPwdFragment fragment = new ResetPwdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHONE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phone = getArguments().getString(ARG_PHONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reser_pwd, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        return view;
    }

    private void initView() {
        textViewName.setText("密码重置");
        updataUser = new UpdataUserInfoBean();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });

        //下一步
        RxView.clicks(btnOk)
                .flatMap(new Func1<Void, Observable<ResquestResult<String>>>() {
                    @Override
                    public Observable<ResquestResult<String>> call(Void aVoid) {
                        if (tietNewPwd.getText().toString().equals(tietPwdSure.getText().toString()) ) {
                            //提交新密码
                            updataUser.setPassword(tietNewPwd.getText().toString());
                            updataUser.setUpdateType(6);
                            updataUser.setMobile(phone);
                            imm.hideSoftInputFromWindow(btnOk.getWindowToken(), 0); //强制隐藏键盘
                            return HttpMethods.getInstance().updataUser(updataUser);
                        } else {
                            Snackbar.make(btnOk,"两次输入密码不一致,请重新输入",3000).show();
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
                        Snackbar.make(btnOk,"重置密码出现未知的错误",3000).show();
                        Log.e(GeneralConfig.LOG_TAG,e.toString());
                    }

                    @Override
                    public void onNext(ResquestResult<String> stringResult) {
                        if (stringResult != null && TextUtils.isEmpty(stringResult.ResultCode)) {
                            Snackbar.make(btnOk,"密码重置成功",3000).show();
                            getFragmentManager().popBackStack();
                            getFragmentManager().popBackStack();
                        } else {
                            Snackbar.make(btnOk,"密码重置失败",3000).show();
                        }
                    }
                });

    }


}
