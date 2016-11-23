package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.seeDoctor;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AppDetialRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.HisCancleBookReqBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppDetialBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 最近预约详情界面
 */
public class AppDetailFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_APP_DETIAL = "appDetial";
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_doctor)
    TextView tvDoctor;
    @BindView(R.id.tv_outpat_type)
    TextView tvOutpatType;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_patient_name)
    TextView tvPatientName;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_pain_text)
    TextView tvPainText;
    @BindView(R.id.tv_doctor_advice)
    TextView tvDoctorAdvice;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.btn_cancle_app)
    Button btnCancleApp;

    private AppMessBean appDetial;
    private AppDetialRequestBean request;//联网请求预约详情的的实体
    private AppDetialBean appDetialMore;//联网获取到的更加详细的预约详情
    private HisCancleBookReqBean hisCancleBookRequest;//提交到his的取消预约的请求实体
    private AppDetialRequestBean hosCancleBookRequest; //提交到hos的取消预约的请求实体

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param appMess Parameter 1.
     * @return A new instance of fragment AppDetailFragment.
     */
    public static AppDetailFragment newInstance(AppMessBean appMess) {
        AppDetailFragment fragment = new AppDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APP_DETIAL, appMess);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appDetial = getArguments().getParcelable(ARG_APP_DETIAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData();
        return view;
    }

    //初始化界面
    private void initView() {
        request = new AppDetialRequestBean();
        appDetialMore = new AppDetialBean();
        hisCancleBookRequest = new HisCancleBookReqBean();
        hosCancleBookRequest = new AppDetialRequestBean();
        hisCancleBookRequest.setBookingType("99");


        setText();
    }

    private void setText() {
        request.setBookingSn(appDetial.getBookingSn());
        textViewName.setText("门诊预约");
        if (!Uitls.isEmptyClazz(appDetialMore)) {
            tvAdress.setText(appDetial.getHospitalName() + " " + appDetial.getDepartment());
            tvTime.setText(appDetialMore.getTime() + " " + getWeek(appDetialMore.getWeekDay()) +" "+ appDetial.getTimeSpan());
            tvDoctor.setText(appDetial.getDoctor() + " " + appDetial.getDoctorTitle());
            tvPatientName.setText(appDetial.getPatientName());
            tvCardNumber.setText(appDetialMore.getPatientCardNo() + "");
            tvPainText.setText(appDetialMore.getDescription() + "");
            tvDoctorAdvice.setText(appDetialMore.getAdvice() + "");
            if (!TextUtils.isEmpty(appDetialMore.getMoney())) {
                tvMoney.append(appDetialMore.getMoney() + "");
            }
        }
        if (appDetial.getStatus()>0 &&appDetial.getStatus()<6) {
            btnCancleApp.setVisibility(View.VISIBLE);
        } else {
            btnCancleApp.setVisibility(View.GONE);

        }
    }

    //界面点击事件
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //取消预约
        RxView.clicks(btnCancleApp)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        cancleBooking();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    //加载数据
    private void loadingData() {
        HttpMethods.getInstance().getAppDetial(new Subscriber<ResquestResult<AppDetialBean>>() {
            @Override
            public void onCompleted() {
                setText();//刷新数据
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResquestResult<AppDetialBean> appDetialBeanResquestResult) {
                appDetialMore = appDetialBeanResquestResult.ResultData;
            }
        }, request);
    }

    private void cancleBooking() {
        Log.i(GeneralConfig.LOG_TAG,"开始取消预约");
        HisCancleBookReqBean.ScheduleDetailBean detail = new HisCancleBookReqBean.ScheduleDetailBean();
        detail.setQueueSn(appDetialMore.getQueueSn());
        detail.setScheduleId(appDetialMore.getScheduleId());
        hisCancleBookRequest.setScheduleDetail(detail);
        hosCancleBookRequest.setBookingSn(appDetial.getBookingSn());
        HttpMethods.getInstance().cancleBooking(new Subscriber<ResquestResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i(GeneralConfig.LOG_TAG, e.toString());
                Snackbar.make(btnCancleApp, "取消预约失败", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ResquestResult result) {
                Log.i(GeneralConfig.LOG_TAG, result.ResultCode + result.ResultCode + result.ResultData);
                if (!Uitls.isEmptyClazz(result)&&TextUtils.isEmpty(result.ResultCode)) {
                    Snackbar.make(btnCancleApp, "取消预约成功", Snackbar.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack();
                } else {
                    Snackbar.make(btnCancleApp, "取消预约失败", Snackbar.LENGTH_SHORT).show();
                }
            }
        }, hisCancleBookRequest, hosCancleBookRequest);
    }

    /**
     * 星期显示
     * @param intWeek
     */
    private String getWeek(String intWeek) {
        String result = "";
        if(intWeek==null) return result;
        switch (intWeek){
            case "1":
                result = "周一";
                break;
            case "2":
                result = "周二";
                break;
            case "3":
                result = "周三";
                break;
            case "4":
                result = "周四";
                break;
            case "5":
                result = "周五";
                break;
            case "6":
                result = "周六";
                break;
            case "0":
                result = "周日";
                break;
        }
        return result;
    }

}
