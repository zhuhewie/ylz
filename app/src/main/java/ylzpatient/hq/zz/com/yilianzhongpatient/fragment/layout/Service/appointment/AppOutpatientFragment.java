package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddClinicBookingBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddPatientCerdBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.BindMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.SendAppBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.BindCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorAppMessageBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.AppTimeEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.PatientCardDialogEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.WriteSymptomEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.ChooseAppTimeDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.ChoosePatientCardDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.WriteSymptomDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me.LoginFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.seeDoctor.RecentAppFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-19.
 * 预约门诊界面
 */
public class AppOutpatientFragment extends SuperFragment {


    private static final String ARG_APP_MESS = "arg_app_mess";
    private static final String ARG_HOSPITAL = "hospital";
    private static final String ARG_AMPM = "ap-pm";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.img_app)
    ImageView imgApp;
    @BindView(R.id.text_app_doctor_name)
    TextView textAppDoctorName;
    @BindView(R.id.text_doctor_message)
    TextView textDoctorMessage;
    @BindView(R.id.text_app_date)
    TextView textAppDate;
    @BindView(R.id.text_app_adress)
    TextView textAppAdress;
    @BindView(R.id.text_app_type)
    TextView textAppType;
    @BindView(R.id.text_app_money)
    TextView textAppMoney;
    @BindView(R.id.text_app_time)
    TextView textAppTime;
    @BindView(R.id.layout_app_time)
    LinearLayout layoutAppTime;
    @BindView(R.id.text_jibingmiaosu)
    TextView textJibingmiaosu;
    @BindView(R.id.layout_symptom_describe)
    LinearLayout layoutSymptomDescribe;
    @BindView(R.id.text_patient_card)
    TextView textPatientCard;
    @BindView(R.id.ll_parent_card)
    LinearLayout llParentCard;
    @BindView(R.id.new_patient_card)
    LinearLayout layoutNewParentCard;
    @BindView(R.id.tiet_phone)
    TextInputEditText tietPhone;
    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;
    @BindView(R.id.tiet_card_name)
    TextInputEditText tietCardName;
    @BindView(R.id.til_card_name)
    TextInputLayout tilCardName;
    @BindView(R.id.tiet_card_idcard)
    TextInputEditText tietCardIdcard;
    @BindView(R.id.til_card_idcard)
    TextInputLayout tilCardIdcard;
    @BindView(R.id.tiet_card_number)
    TextInputEditText tietCardNumber;
    @BindView(R.id.til_card_number)
    TextInputLayout tilCardNumber;
    @BindView(R.id.btn_app_ok)
    Button btnAppOk;
    @BindView(R.id.btn_bind_app_ok)
    Button btnBindAppOk;
    @BindView(R.id.rl_ok)
    RelativeLayout rlOk;

    private DoctorAppMessageBean appMessage;
    private TimeBean appTime; //选中的预约时间
    private String stringSymptom;//症状描述
    //    private RegisterPatientCardBean patientCardRequest;//旧的添加就诊卡请求体
    private AddPatientCerdBean addPatientCerdRequest;//新的添加就诊卡请求体
    private SendAppBean sendAppMessage; //用于请求预约申请的实体
    private PatientCardBean selectPatientCard; //选中的就诊卡
    private Hopital selectHospital;
    private String noSelectCardString = "选择就诊卡";
    private BindMessBean bindMess; //绑定就诊卡的请求实体,存放输入的就诊卡数据
    private Handler handler = new Handler();
    private int ampm; //上下午


    /**
     * 创建界面
     *
     * @param appMes
     * @param hopital
     * @param ampm    :用于判断上下午.1:上午,2:下午
     * @return
     */
    public static AppOutpatientFragment newInstance(DoctorAppMessageBean appMes, Hopital hopital, int ampm) {
        AppOutpatientFragment fragment = new AppOutpatientFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APP_MESS, appMes);
        args.putParcelable(ARG_HOSPITAL, hopital);
        args.putInt(ARG_AMPM, ampm);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            appMessage = bundle.getParcelable(ARG_APP_MESS);
            selectHospital = bundle.getParcelable(ARG_HOSPITAL);
            ampm = bundle.getInt(ARG_AMPM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.app_outpatient_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        return v;
    }

    private void initView() {
        //patientCardRequest = new RegisterPatientCardBean();
        appTime = new TimeBean();
        if (ampm == 1) {
            appTime.BeginTime = "8:00";
            appTime.EndTime = "9:00";
        } else if (ampm == 2) {
            appTime.BeginTime = "14:00";
            appTime.EndTime = "15:00";
        }
        sendAppMessage = new SendAppBean("99", new SendAppBean.OutPatBean(), new SendAppBean.ScheduleDetailBean());
        selectPatientCard = new PatientCardBean();
        addPatientCerdRequest = new AddPatientCerdBean();
        bindMess = new BindMessBean();

        textAppTime.setText(appTime.BeginTime + "-" + appTime.EndTime);
        textViewName.setText("预约门诊");
        textPatientCard.setText(noSelectCardString);
        if (!Uitls.isEmptyClazz(appMessage)) {
            textAppDoctorName.setText(appMessage.getDoctorName());
            textDoctorMessage.setText(getDocShowText());
            textAppAdress.setText(appMessage.getRoomName());
            textAppMoney.append(appMessage.getClinicResponceFee());
            textAppDate.setText(appMessage.getScheduleDate().substring(0, 10));
        }
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


    private void addClick() {
        //左上角返回图片点击事件
        RxView.clicks(imgBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //就诊卡点击事件
        RxView.clicks(llParentCard)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
                            switchFatherContent(AppOutpatientFragment.this, LoginFragment.newInstance());
                        } else {
                            showDialog(ChoosePatientCardDialog.newInstance());
                        }
                    }
                });
        //预约时间点击事件
        RxView.clicks(layoutAppTime)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showDialog(ChooseAppTimeDialog.newInstance(ampm));
                    }
                });
        //症状描述点击事件
        RxView.clicks(layoutSymptomDescribe)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showDialog(WriteSymptomDialog.newInstance());
                    }
                });

        //简单的确认预约
        RxView.clicks(btnAppOk)
                .throttleFirst(1, TimeUnit.SECONDS)
                //1:提交预约信息到his
                .flatMap(new Func1<Void, Observable<AppResultBean>>() {
                    @Override
                    public Observable<AppResultBean> call(Void result) {
                        sendAppMessage.getOutPat().setPatientName(selectPatientCard.getPatName());
                        sendAppMessage.getOutPat().setPatientID(selectPatientCard.getPatId());
                        sendAppMessage.getOutPat().setMobile(selectPatientCard.getPatMobile());
//                        sendAppMessage.getScheduleDetail().setBeginTime(appTime.BeginTime);
//                        sendAppMessage.getScheduleDetail().setEndTime(appTime.EndTime);
                        sendAppMessage.getScheduleDetail().setScheduleId(appMessage.getScheduleId());
                        return HttpMethods.getInstance().sendApp(sendAppMessage);
                    }
                })
                //2.同步数据到hos
                .flatMap(new Func1<AppResultBean, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(AppResultBean appResult) {
                        //HIS预约成功
                        if (!Uitls.isEmptyClazz(appResult) && appResult.isBookingResult()) {
                            //装填要提交的信息 ,有可能出现类型转换的错误,和字符串切割导致的下标越界
                            try {
                                AddClinicBookingBean addClinicBookingMes = new AddClinicBookingBean();
                                addClinicBookingMes.setHospitalId(appResult.getOrgID());
                                addClinicBookingMes.setPatientId(appResult.getPatientID());
                                addClinicBookingMes.setPatientName(appResult.getPatientName());
                                addClinicBookingMes.setRegId(appResult.getRegId());
                                addClinicBookingMes.setDepartmentCode(appResult.getDeptCode());
                                addClinicBookingMes.setDepartmentName(appResult.getDeptName());
                                addClinicBookingMes.setScheduleId(appResult.getScheduleId());
                                addClinicBookingMes.setQueneSn(appResult.getQueueSn());
                                addClinicBookingMes.setAdviceTime(appResult.getAdviceTime());
                                addClinicBookingMes.setDoctorCode(appResult.getDoctorCode());
                                addClinicBookingMes.setDoctorName(appResult.getDoctorName());
                                addClinicBookingMes.setHospitalId(selectHospital.getOrgId());
                                addClinicBookingMes.setPatientId(selectPatientCard.getPatId());
                                addClinicBookingMes.setPatientName(selectPatientCard.getPatName());
                                addClinicBookingMes.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
                                addClinicBookingMes.setStatus(Integer.parseInt(appResult.getStatus()));

                                addClinicBookingMes.setBeginTime(appResult.getBeginTime());
                                addClinicBookingMes.setEndTime(appResult.getEndTime());
                                addClinicBookingMes.setRoomNo(appResult.getRoomNo());
                                addClinicBookingMes.setBookDate(appResult.getBookDate());
                                addClinicBookingMes.setVisitId(appResult.getVisitingId());
                                if (!TextUtils.isEmpty(appResult.getPayFlag())) {
                                    addClinicBookingMes.setStatus(Integer.parseInt(appResult.getStatus()));
                                }
                                //从his返回的是String 上传到hos的是int型
                                if (!TextUtils.isEmpty(appResult.getPayFlag())) {
                                    addClinicBookingMes.setVisitPayFlag(Integer.parseInt(appResult.getPayFlag()));
                                }
                                return HttpMethods.getInstance().addClinicBooking(addClinicBookingMes);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Observable.just(null);
                            }

                        } else {
                            ResquestResult result = new ResquestResult();
                            result.ResultCode = "false";
                            result.ResultMsg = appResult.getErrorMessage();
                            return Observable.just(result);
                        }

                    }
                })
                .subscribe(new Action1<ResquestResult>() {
                    @Override
                    public void call(ResquestResult result) {
                        if (result != null && TextUtils.isEmpty(result.ResultCode)) {
                            //预约成功
                            Snackbar.make(btnAppOk, "预约成功", Snackbar.LENGTH_LONG).show();
                            //界面跳转到最近预约列表
                            switchFatherContent(AppOutpatientFragment.this, RecentAppFragment.newInstance());
                        } else if (result != null) {
                            Snackbar.make(btnAppOk, "预约失败" + result.ResultMsg, Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "预约失败" + result.ResultMsg);
                        } else {
                            Snackbar.make(btnAppOk, "预约失败", Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "预约失败");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(btnAppOk, "预约异常", Snackbar.LENGTH_LONG).show();
                        Log.e(GeneralConfig.LOG_TAG, "预约异常" + throwable.toString());
                    }
                });

        //复杂的确定点击事件,绑定并预约
        RxView.clicks(btnBindAppOk)
                .throttleFirst(1, TimeUnit.SECONDS)
                //1:提交绑定信息到his
                .flatMap(new Func1<Void, Observable<BindCardBean>>() {
                    @Override
                    public Observable<BindCardBean> call(Void aVoid) {
                        if (checkInputCardMess()) {
                            bindMess.setKeyWord(tietCardNumber.getText().toString());
                            bindMess.setSecondaryKey(tietCardName.getText().toString());
                            bindMess.setKeySearchType(2);
                            return HttpMethods.getInstance().bindCard_2(bindMess);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                //2:同步绑定就诊卡数据到hos
                .flatMap(new Func1<BindCardBean, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(BindCardBean bindCardBean) {
                        UserTestBean_2 user = UserManager.getInstance().getCurrentUser();
                        //绑定就诊卡的返回不能为空,登录信息不能为空,选中的医院不能为空

                        if (!Uitls.isEmptyClazz(bindCardBean) && !Uitls.isEmptyClazz(user)
                                && !Uitls.isEmptyClazz(selectHospital)) {
                            //如果his绑定就诊卡成功,赋值给选中的就诊卡
                            if (selectPatientCard != null) {
                                selectPatientCard.setPatId(bindCardBean.getPatientID());
                                selectPatientCard.setPatMobile(bindCardBean.getMobile());
                                selectPatientCard.setPatName(bindCardBean.getPatientName());
                            }
//                            selectPatientCard.setPatMobile(tietPhone.getText().toString());//使用患者输入的手机号
//                            selectPatientCard.setPatName(tietCardName.getText().toString());
//                            selectPatientCard.setPatSocilNo(tietCardIdcard.getText().toString());

                            //封装上传信息,以便于提交到hos
                            AddPatientCerdBean card = new AddPatientCerdBean();
                            card.setUserId(user.getUserId());
                            card.setPatientId(bindCardBean.getPatientID());
                            card.setHospitalId(selectHospital.getOrgId());
                            card.setName(tietCardName.getText().toString());
                            card.setMobile(tietPhone.getText().toString());
                            card.setSocialNo(tietCardIdcard.getText().toString());
                            card.setSex(bindCardBean.getSex());
                            card.setBirthday(getBrith(tietCardIdcard.getText().toString()));
                            card.setCardNo(bindCardBean.getSunCodeNo());
                            //联网同步数据到hos
                            return HttpMethods.getInstance().addResquestObservable(card);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                //3:提交预约信息到his
                .flatMap(new Func1<ResquestResult, Observable<AppResultBean>>() {
                    @Override
                    public Observable<AppResultBean> call(ResquestResult result) {
                        sendAppMessage.getOutPat().setPatientName(selectPatientCard.getPatName());
                        sendAppMessage.getOutPat().setPatientID(selectPatientCard.getPatId());
                        sendAppMessage.getOutPat().setMobile(selectPatientCard.getPatMobile());
//                        sendAppMessage.getScheduleDetail().setBeginTime(appTime.BeginTime);
//                        sendAppMessage.getScheduleDetail().setEndTime(appTime.EndTime);
                        sendAppMessage.getScheduleDetail().setScheduleId(appMessage.getScheduleId());
                        return HttpMethods.getInstance().sendApp(sendAppMessage);
                    }
                })
                //4.同步数据到hos
                .flatMap(new Func1<AppResultBean, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(AppResultBean appResult) {
                        //HIS预约成功
                        if (!Uitls.isEmptyClazz(appResult) && appResult.isBookingResult()) {
                            //装填要提交的信息 ,有可能出现类型转换的错误,和字符串切割导致的下标越界
                            try {
                                AddClinicBookingBean addClinicBookingMes = new AddClinicBookingBean();
                                addClinicBookingMes.setHospitalId(appResult.getOrgID());
                                addClinicBookingMes.setPatientId(appResult.getPatientID());
                                addClinicBookingMes.setPatientName(appResult.getPatientName());
                                addClinicBookingMes.setRegId(appResult.getRegId());
                                addClinicBookingMes.setDepartmentCode(appResult.getDeptCode());
                                addClinicBookingMes.setDepartmentName(appResult.getDeptName());
                                addClinicBookingMes.setScheduleId(appResult.getScheduleId());
                                addClinicBookingMes.setQueneSn(appResult.getQueueSn());
                                addClinicBookingMes.setAdviceTime(appResult.getAdviceTime());
                                addClinicBookingMes.setDoctorCode(appResult.getDoctorCode());
                                addClinicBookingMes.setDoctorName(appResult.getDoctorName());
                                addClinicBookingMes.setHospitalId(selectHospital.getOrgId());
                                addClinicBookingMes.setPatientId(selectPatientCard.getPatId());
                                addClinicBookingMes.setPatientName(selectPatientCard.getPatName());
                                addClinicBookingMes.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
                                addClinicBookingMes.setStatus(Integer.parseInt(appResult.getStatus()));

                                addClinicBookingMes.setBeginTime(appResult.getBeginTime());
                                addClinicBookingMes.setEndTime(appResult.getEndTime());
                                addClinicBookingMes.setRoomNo(appResult.getRoomNo());
                                addClinicBookingMes.setBookDate(appResult.getBookDate());
                                addClinicBookingMes.setVisitId(appResult.getVisitingId());
                                if (!TextUtils.isEmpty(appResult.getPayFlag())) {
                                    addClinicBookingMes.setStatus(Integer.parseInt(appResult.getStatus()));
                                }
                                //从his返回的是String 上传到hos的是int型
                                if (!TextUtils.isEmpty(appResult.getPayFlag())) {
                                    addClinicBookingMes.setVisitPayFlag(Integer.parseInt(appResult.getPayFlag()));
                                }
                                return HttpMethods.getInstance().addClinicBooking(addClinicBookingMes);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        } else {
                            ResquestResult result = new ResquestResult();
                            result.ResultCode = "false";
                            result.ResultMsg = appResult.getErrorMessage();
                            return Observable.just(result);
                        }

                    }
                })
                .subscribe(new Action1<ResquestResult>() {
                    @Override
                    public void call(ResquestResult result) {
                        if (result != null && TextUtils.isEmpty(result.ResultCode)) {
                            //预约成功
                            Snackbar.make(btnAppOk, "预约成功", Snackbar.LENGTH_LONG).show();
                            //界面跳转到最近预约列表
                            switchFatherContent(AppOutpatientFragment.this, RecentAppFragment.newInstance());
                        } else if (result != null) {
                            Snackbar.make(btnAppOk, "预约失败" + result.ResultMsg, Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "预约失败" + result.ResultMsg);
                        } else {
                            Snackbar.make(btnAppOk, "预约失败", Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "预约失败");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(btnAppOk, "预约异常", Snackbar.LENGTH_LONG).show();
                        Log.e(GeneralConfig.LOG_TAG, "预约异常" + throwable.toString());
                    }
                });
    }

    //接收就诊卡界面的
    // 其他就诊卡点击事件,并相应
    @Subscribe
    public void onEvent(PatientCardDialogEvent event) {
        //switchFatherContent(this, DoctorFragment.newInstance(event.departmentName,hospital));
        if (Uitls.isEmptyClazz(event.patientCard)) {
            layoutNewParentCard.setVisibility(View.VISIBLE);
            textPatientCard.setText("其他就诊人");
            btnBindAppOk.setVisibility(View.VISIBLE);//显示绑定提交预约的按钮
            btnAppOk.setVisibility(View.GONE); //隐藏预约按钮
        } else {
            //Snackbar.make(llParentCard, "选择就诊卡:" + event.patientCard.getPatName(), 3000).show();
            textPatientCard.setText(event.patientCard.getPatName());
            selectPatientCard = event.patientCard;
            layoutNewParentCard.setVisibility(View.GONE);//隐藏就诊卡输入界面
            btnAppOk.setVisibility(View.VISIBLE); //显示预约按钮
            btnBindAppOk.setVisibility(View.GONE); //隐藏绑定并预约按钮
        }
    }

    //接收 预约时段界面,选中的预约时间
    @Subscribe
    public void onAppTimeEvent(AppTimeEvent event) {
        appTime = event.selectTime;
        Snackbar.make(layoutAppTime, "预约时间:" + appTime.BeginTime + "-" + appTime.EndTime, 3000)
                .show();
        textAppTime.setText(appTime.BeginTime + "-" + appTime.EndTime);
    }

    //症状描述界面的相应,
    @Subscribe
    public void onSymptom(WriteSymptomEvent event) {
        stringSymptom = event.symptom;
        Snackbar.make(layoutSymptomDescribe, "症状:" + stringSymptom, 3000).show();
    }

    //显示的医院信息
    private String getDocShowText() {
        String stringDoctor = "";
        if (!TextUtils.isEmpty(appMessage.getClinicResponceName())) {
            stringDoctor += appMessage.getClinicResponceName() + "   |   ";
        }
        if (!TextUtils.isEmpty(selectHospital.getOrgName())) {
            stringDoctor += selectHospital.getOrgName();
        }
        if (!TextUtils.isEmpty(appMessage.getOrganizationName())) {
            stringDoctor += appMessage.getOrganizationName() + "   |   ";
        }
        if (!TextUtils.isEmpty(appMessage.getDeptName())) {
            stringDoctor += appMessage.getDeptName();
        }
        return stringDoctor;
    }



    /**
     * 验证用户输入信息
     *
     * @return
     */
    private boolean checkInputCardMess() {
        boolean result = false;
        String tipString = "";
        if (llParentCard.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(tietCardNumber.getText().toString())) {
                tipString = "就诊卡号不能为空";
            } else if (TextUtils.isEmpty(tietCardName.getText().toString())) {
                tipString = "姓名不能为空";
            } else if (TextUtils.isEmpty(tietCardIdcard.getText().toString())) {
                tipString = "身份证号不能为空";
            } else if (tietCardIdcard.getText().toString().length() != 18) {
                tipString = "身份证号输入有误";
            } else if (TextUtils.isEmpty(tietPhone.getText().toString())) {
                tipString = "手机号不能为空";
            } else if (!tietPhone.getText().toString().matches("[1]\\d{10}")) {
                tipString = "手机号输入有误";
            } else {
                result = true;
            }
            if (!result) {
                Snackbar.make(btnAppOk, tipString, Snackbar.LENGTH_LONG).show();
            }
        }
        return result;
    }

    /**
     * 根据身份证号,剪切出来出生日期
     */
    private String getBrith(String idCard) {
        String brith = "1970-01-01 00:00:00";
        if (!TextUtils.isEmpty(idCard) && idCard.length() == 18) {
            brith = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14) + " 00:00:00";
        }
        return brith;
    }

    @Override
    public void onDestroy() {
        handler = null;
        super.onDestroy();
    }
}
