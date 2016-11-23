package ylzpatient.hq.zz.com.yilianzhongpatient.http;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.D;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.JsonParam;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddAuthorizeRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddClinicBookingBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddPatientCerdBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AppDetialRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AppTimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.BindMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.CancleAuthorizeRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.CheckListRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.DelectCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.EnterClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.HisCancleBookReqBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.InspeDetialRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.LoginBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.MainInspeRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageHealthPatRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PagePatIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PatientIdCardsRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitQueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RecentAppBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RegisterBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RegisterPatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.ReportRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.SendAppBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UpdataUserInfoBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserId;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppDetialBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AuthorizeResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.BindCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CheckBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicalBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CreateCardResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthBasicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.InspectionDetailBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.MainInspectionBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.QueueBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReportHealths;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReportsBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult.HttpCreadPatCardResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult.HttpDeptResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult.HttpDocAppMessResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.subscriber.ProgressBarSubscribe;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.subscriber.SubscriberOnNextLisnter;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.logInfo;
import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.logInfoBean;
import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.outBeanLogInfo;
import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.outLogInfo;

/**
 * Created by Administrator on 2016-9-12.
 */
public class HttpMethods {
    private Retrofit retrofit;
    private Retrofit retrofit2;
    private ServiceNewApi hosApi;//新的hos api
    private ServiceApi.RxLoginService loginService;//登录
    private ServiceApi.RxLogintESTService loginTestService;//登录
    private ServiceApi.hospitalService hospitalService; //医院列表
    private ServiceApi.departmentAndDoctorService departmentAndDoctorService; //科室和医生
    private ServiceApi.departmentAndDoctorService2 departmentAndDoctorService2; //科室和医生
    private ServiceApi.appDoctorDateService appDoctorDateService; //医生信息
    private ServiceApi.patientCardService patientCardService; //请求登陆者的就诊卡信息
    private ServiceApi.getBindCardListService getBindCardListService;//获取绑定就诊卡列表
    private ServiceApi.newPatientCardService newPatientCard; //注册新的就诊卡
    private ServiceApi.onlineDoctorService onlineDoctorService; //提交预约信息
    private ServiceApi.registerService registerService; //注册新用户
    private ServiceApi.updataUserService updataUserService; //更新用户信息
    private ServiceApi.index indexService; //获取首页信息
    private ServiceApi.hosOrgService hosOrgService; //获取所属医院
    private ServiceApi.recentBookService recentBookService; //分页获取最近预约记录
    private ServiceApi.bindCardService bindCardService; //绑定就诊卡
    private ServiceApi.addPatientIdCardService addPatientIdCardService;//新增就诊卡
    private ServiceApi.toAuthorizesService toAuthorizesService;//获取我给予他人的授权
    private ServiceApi.fromAuthorizesService fromAuthorizesService;//获取他人给予我的授权
    private ServiceApi.cancleCardService cancleCardService;//取消绑定就诊卡
    private ServiceApi.cancleAuthorizeService cancleAuthorizeService;//取消授权
    private ServiceApi.getReportService getReportService;//获取报告查询列表
    private ServiceApi.addClinicBookingService addClinicBookingService;//提交预约信息到hos
    private ServiceApi.appMessageService appMessage; //提交预约信息到his
    private ServiceApi.getAppDetialService getAppDetialService; //获取预约详情
    private ServiceApi.hosCancelBookingService hosCancelBookingService;//hos端取消预约
    private ServiceApi.hisCancelBookingService hisCancelBookingService;//his端取消预约
    private ServiceApi.getListInspecService getListInspecService;//获取检验报告的主列表
    private ServiceApi.getSingleLisByTypeService getSingleLisByTypeService;////获取检验报告详情列表
    private ServiceApi.getBodyCheckListService getBodyCheckListService;//获取检查列表
    private ServiceApi.addAuthorizeService addAuthorizeService;//新增授权
    private ServiceApi.currentQueueService currentQueueService;//获取最近的在线诊所排队信息
    private ServiceApi.queueService queueService;//在线诊所排队
    private ServiceApi.quitQueueService quitQueueService;//退出在线诊所排队

    private Gson gson = new Gson();


    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(GeneralConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String hosUrl = GeneralConfig.getHOSBaseURL();
        String hisUrl = GeneralConfig.getHISBaseURL();
        retrofit = new Retrofit.Builder()
                .baseUrl(hosUrl)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit2 = new Retrofit.Builder()
                .baseUrl(hisUrl)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        hosApi = retrofit.create(ServiceNewApi.class);
        loginService = retrofit.create(ServiceApi.RxLoginService.class);
        loginTestService = retrofit.create(ServiceApi.RxLogintESTService.class);
        hospitalService = retrofit.create(ServiceApi.hospitalService.class);
        departmentAndDoctorService = retrofit2.create(ServiceApi.departmentAndDoctorService.class);
        departmentAndDoctorService2 = retrofit2.create(ServiceApi.departmentAndDoctorService2.class);
        appDoctorDateService = retrofit2.create(ServiceApi.appDoctorDateService.class);
        patientCardService = retrofit.create(ServiceApi.patientCardService.class);
        newPatientCard = retrofit2.create(ServiceApi.newPatientCardService.class);
        appMessage = retrofit2.create(ServiceApi.appMessageService.class);
        onlineDoctorService = retrofit.create(ServiceApi.onlineDoctorService.class);
        registerService = retrofit.create(ServiceApi.registerService.class);
        updataUserService = retrofit.create(ServiceApi.updataUserService.class);
        indexService = retrofit.create(ServiceApi.index.class);
        hosOrgService = retrofit.create(ServiceApi.hosOrgService.class);
        recentBookService = retrofit.create(ServiceApi.recentBookService.class);
        bindCardService = retrofit2.create(ServiceApi.bindCardService.class);
        addPatientIdCardService = retrofit.create(ServiceApi.addPatientIdCardService.class);
        toAuthorizesService = retrofit.create(ServiceApi.toAuthorizesService.class);
        fromAuthorizesService = retrofit.create(ServiceApi.fromAuthorizesService.class);
        cancleCardService = retrofit.create(ServiceApi.cancleCardService.class);
        cancleAuthorizeService = retrofit.create(ServiceApi.cancleAuthorizeService.class);
        getReportService = retrofit.create(ServiceApi.getReportService.class);
        addClinicBookingService = retrofit.create(ServiceApi.addClinicBookingService.class);
        getAppDetialService = retrofit.create(ServiceApi.getAppDetialService.class);
        hosCancelBookingService = retrofit.create(ServiceApi.hosCancelBookingService.class);
        hisCancelBookingService = retrofit2.create(ServiceApi.hisCancelBookingService.class);
        getBindCardListService = retrofit.create(ServiceApi.getBindCardListService.class);
        getListInspecService = retrofit2.create(ServiceApi.getListInspecService.class);
        getSingleLisByTypeService = retrofit2.create(ServiceApi.getSingleLisByTypeService.class);
        getBodyCheckListService = retrofit2.create(ServiceApi.getBodyCheckListService.class);
        addAuthorizeService = retrofit.create(ServiceApi.addAuthorizeService.class);
        currentQueueService = retrofit.create(ServiceApi.currentQueueService.class);
        queueService = retrofit.create(ServiceApi.queueService.class);
        quitQueueService = retrofit.create(ServiceApi.quitQueueService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 公用方法体
     *
     * @param o   观察者
     * @param s   背观察者
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())//请求过程在io线程处理
                .unsubscribeOn(Schedulers.io())//解绑请求 在io线程处理
                .observeOn(AndroidSchedulers.mainThread())//数据返回后再安卓主线程处理
                .subscribe(s);
    }

    /**
     * 登录的方法
     *
     * @param loginSubscriber
     * @param loginer         登陆者的实体
     */
    public void loginService(Subscriber<UserBean> loginSubscriber, LoginBean loginer) {
        loginService.getUser(loginer)
                .map(new HttpResultMaper<UserBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginSubscriber);
    }

    /**
     * 登录的方法
     *
     * @param loginSubscriber
     * @param loginer         登陆者的实体
     */
    public void loginTestService(Subscriber<UserTestBean_2> loginSubscriber, LoginBean loginer) {
        loginTestService.getUser_2(loginer)
                .map(new HttpResultMaper<UserTestBean_2>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginSubscriber);
    }

    /**
     * 获取医院列表
     *
     * @param getHopitalList
     */
    public void getHospital(Subscriber<ResquestResult<List<Hopital>>> getHopitalList) {
        hospitalService.getHospitalList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getHopitalList);
    }

    /**
     * 获取科室和医生列表
     *
     * @param getDepartDoctor
     */
    public void getDepartAndDoctor(Subscriber<D<List<DepartmentBean>>> getDepartDoctor, JsonParam<TimeBean> time) {
        departmentAndDoctorService.getDepartmentDoctor(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getDepartDoctor);
    }

    /**
     * 获取科室和医生列表
     *
     * @param getDepartDoctor
     */
    public void getDepartAndDoctorString(Subscriber<List<DepartmentBean>> getDepartDoctor, JsonParam<TimeBean> time) {
        departmentAndDoctorService2.getDepartmentDoctor(time)
                .map(HttpDeptResult.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getDepartDoctor);
    }

    /**
     * 获取指定医生的排班信息
     *
     * @param listDoctor
     * @param appTime
     */
    public void getAppDoctor(Subscriber<List<Object>> listDoctor, JsonParam<AppTimeBean> appTime) {
        appDoctorDateService.getAppDoctorDate(appTime)
                .map(new HttpDocAppMessResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listDoctor);
    }

    /**
     * 获取指定医生的排班信息
     *
     * @param context
     * @param lisnter
     * @param appTime 请求的实体
     */
    public void getAppDoctor2(FragmentActivity context, SubscriberOnNextLisnter lisnter, JsonParam<AppTimeBean> appTime) {

        Observable ob = appDoctorDateService.getAppDoctorDate(appTime)
                .map(new HttpDocAppMessResult());
        toSubscribe(ob, new ProgressBarSubscribe(lisnter, context));
    }

    /**
     * 根据登录者id获取就诊卡信息
     *
     * @param listPatientCard 返回的就诊卡信息
     * @param userId          登录者id
     */
    public void getPatientCard(Subscriber<List<PatientCardBean>> listPatientCard, UserId userId) {
//        Observable ob = patientCardService.getPatientCard(userId)
//                .map(new HttpResultMaper<List<PatientCardBean>>());
//        toSubscribe(ob,listPatientCard);
        Log.i(GeneralConfig.LOG_TAG,"根据userid,获取就诊卡信息的请求体:" + gson.toJson(userId));
        patientCardService.getPatientCard(userId)
                .map(new HttpResultMaper<List<PatientCardBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listPatientCard);
    }

    /**
     * 根据患者的userid
     * 获取绑定的就诊卡列表
     */
    public void getBindCardList(Subscriber<ResquestResult<List<CardBean>>> listPatientCard, PatientIdCardsRequest userId) {
        getBindCardListService.getBindCardList(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listPatientCard);
    }

    /**
     * 注册新的就诊卡
     *
     * @param result
     * @param card
     */
    public void creadPatientCard(Subscriber<CreateCardResultBean> result, RegisterPatientCardBean card) {
        newPatientCard.creatPatCard(new JsonParam<RegisterPatientCardBean>(card))
                .map(HttpCreadPatCardResult.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result);
    }

    /**
     * 注册新的就诊卡
     *
     * @param card
     */
    public Observable<CreateCardResultBean> creadPatientCard2(RegisterPatientCardBean card) {
        return newPatientCard.creatPatCard(new JsonParam<>(card))
                .map(HttpCreadPatCardResult.getInstance());
//                .subscribeOn(Schedulers.io());
//                .observeOn(AndroidSchedulers.mainThread());
//                .subscribe(result);
    }

    /**
     * 提交预约申请到his
     */
    public Observable<AppResultBean> sendApp(SendAppBean appMess) {
        JsonParam<SendAppBean> js = new JsonParam<>();
        js.setJsonParam(appMess);
        Log.i(GeneralConfig.LOG_TAG,"提交预约申请(HIS)" + GeneralConfig.getHISBaseURL() + "ExecBooking");
        Log.i(GeneralConfig.LOG_TAG,gson.toJson(js));
        return appMessage.sendAppMess(js)
                .map(new Func1<D<String>, AppResultBean>() {
                    @Override
                    public AppResultBean call(D<String> stringD) {
                        logInfo("提交预约申请的返回:" + stringD.toString());
                        outLogInfo("提交预约申请的返回:" + stringD.toString());
                        Gson gson = new Gson();
                        AppResultBean result = null;
                        try {
                            result = gson.fromJson(stringD.d, AppResultBean.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 提交预约信息到hos_2
     *  返回的是Observable
     * @param addClinicBooking
     * @return
     */
    public Observable<ResquestResult> addClinicBooking(AddClinicBookingBean addClinicBooking) {
        String str = gson.toJson(addClinicBooking);
        Log.i(GeneralConfig.LOG_TAG,"提交到hos的预约信息"+str);
        return addClinicBookingService.addClinicBooking(addClinicBooking)
                .retry(5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 提交预约信息到hos
     *
     * @param addClinicBooking
     * @return
     */
    public void addClinicBooking(Subscriber<ResquestResult> ddClinic, AddClinicBookingBean addClinicBooking) {
        String str = gson.toJson(addClinicBooking);
        logInfo(str);

        outLogInfo("提交预约申请(HOS)" + GeneralConfig.getHOSBaseURL() + "user/addClinicBooking");
        outBeanLogInfo(addClinicBooking);
        addClinicBookingService.addClinicBooking(addClinicBooking)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ddClinic);
    }

    /**
     * 获取在线诊所医生
     *
     * @param page
     * @return
     */
    public void getOnlineDoctor(Subscriber<ResquestCountResult<List<OnlineDoctorBean>>> onlineDoctor, PageRequestBean page) {
        onlineDoctorService.getOnlineDoctor(page)
                //.map(new HttpResultMaper<UserBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onlineDoctor);
    }

    /**
     * 注册新用户
     *
     * @param register
     * @return
     */
    public Observable register(RegisterBean register) {
        return registerService.sendRegister(register)
                //.map(new HttpResultMaper<String>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //.subscribe(subscriber);
    }

    /**
     * 更新患者信息
     *
     * @param updataUser
     */
    public Observable updataUser(UpdataUserInfoBean updataUser) {
        return updataUserService.updataUser(updataUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //.subscribe(updataUserResult);
    }

    /**
     * 获取主页信息
     */
    public void getIndex(Subscriber<IndexBean> indexSub) {
        indexService.getIndex()
                .map(new Func1<ResquestResult<IndexBean>, IndexBean>() {
                    @Override
                    public IndexBean call(ResquestResult<IndexBean> index) {
                        return index.ResultData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(indexSub);
    }

    /**
     * 获取医院组织列表
     *
     * @param hosOrg
     */
    public void getHosOrg(Subscriber<List<Hopital>> hosOrg) {
        hosOrgService.getHosOrg()
                .map(new Func1<ResquestResult<List<Hopital>>, List<Hopital>>() {
                    @Override
                    public List<Hopital> call(ResquestResult<List<Hopital>> listResult) {
                        return listResult.ResultData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hosOrg);
    }

    /**
     * 获取最近预约列表
     */
    public void getRecentApp(Subscriber<ResquestCountResult<List<AppMessBean>>> listAppMesResult, RecentAppBean recentApp) {
        recentBookService.getRecentApp(recentApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listAppMesResult);
    }


    /**
     * 新增就诊卡
     */
    public void addPatientIdCard(Subscriber<ResquestResult> result, AddPatientCerdBean addPatientCerd) {
        addPatientIdCardService.addCard(addPatientCerd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result);
    }

    /**
     * 新增就诊卡
     */
    public Observable<ResquestResult> addResquestObservable(AddPatientCerdBean addPatientCerd) {
        outLogInfo("新增就诊卡(HOS):" + GeneralConfig.getHOSBaseURL() + "user/addPatientIdCard");
        outBeanLogInfo(addPatientCerd);
        Log.i(GeneralConfig.LOG_TAG, gson.toJson(addPatientCerd));
        return addPatientIdCardService.addCard(addPatientCerd)
                .retry(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 绑定就诊卡
     *
     * @param bindMess
     */
    public void bindCard(Subscriber<BindCardBean> bindCardSubscriber, BindMessBean bindMess) {
        JsonParam<BindMessBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(bindMess);
        bindCardService.bindCard(jsonParam)
                .map(new Func1<D<String>, BindCardBean>() {
                    @Override
                    public BindCardBean call(D<String> stringD) {
                        logInfo("提交预约申请的返回:" + stringD.toString());
                        Gson gson = new Gson();
                        BindCardBean result = null;
                        try {
                            result = gson.fromJson(stringD.d, BindCardBean.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "绑定就诊卡返回信息解析失败:" + e.toString());
                            Toast.makeText(UtilApplication.getContextObject(), "绑定就诊卡返回信息解析失败", Toast.LENGTH_SHORT).show();
                        }
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bindCardSubscriber);

    }

    /**
     * 绑定就诊卡2
     *
     * @param bindMess
     * @return
     */
    public Observable<BindCardBean> bindCard_2(BindMessBean bindMess) {

        JsonParam<BindMessBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(bindMess);
        logInfo("绑定就诊卡(HIS):" + GeneralConfig.getHISBaseURL() + "GetBookingOutPatientInfo");
        logInfoBean(jsonParam);
        outLogInfo("绑定就诊卡(HIS):" + GeneralConfig.getHISBaseURL() + "GetBookingOutPatientInfo");
        outBeanLogInfo(jsonParam);
        return bindCardService.bindCard(jsonParam)
                .map(new Func1<D<String>, BindCardBean>() {
                    @Override
                    public BindCardBean call(D<String> stringD) {
                        logInfo("绑定就诊卡的返回:" + stringD.toString());
                        outLogInfo("绑定就诊卡的返回:" + stringD.toString());
                        BindCardBean result = null;
                        try {
                            result = gson.fromJson(stringD.d, BindCardBean.class);
                            if (Uitls.isEmptyClazz(result)) {
                                throw new IllegalArgumentException("绑定就诊卡失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "绑定就诊卡返回信息解析失败:" + e.toString() + stringD.d);
                            Toast.makeText(UtilApplication.getContextObject(), "绑定就诊卡返回信息解析失败", Toast.LENGTH_SHORT).show();
                        }
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;

    }


    /**
     * 绑定就诊卡,并新建就诊卡
     *
     * @param bindMess
     * @param selectPatientCard
     * @return
     */
    public void bindAndNewCard(final Subscriber<ResquestResult> bindAndNewCardResult,
                               BindMessBean bindMess, final Hopital selectHospital,
                               final PatientCardBean selectPatientCard) {
        bindCard_2(bindMess)
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
                            AddPatientCerdBean card = new AddPatientCerdBean();
                            card.setUserId(user.getUserId());
                            card.setPatientId(bindCardBean.getPatientID());
                            card.setHospitalId(selectHospital.getOrgId());
                            card.setName(bindCardBean.getPatientName());
                            card.setMobile(bindCardBean.getMobile());
                            card.setSocialNo(bindCardBean.getSocialCode());
                            card.setSex(bindCardBean.getSex());
                            card.setBirthday(bindCardBean.getBirthDay() + " 00:00:00");
                            card.setCardNo(bindCardBean.getSunCodeNo());
                            return addResquestObservable(card);
                        } else {
                            return null;
                        }
                    }
                })
//                .retry(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bindAndNewCardResult);
    }

    /**
     * 绑定就诊卡后,循环新建就诊卡
     *
     * @param bindMess
     */
    public void bindAndForNewCard(Subscriber<BindCardBean> bindCardSubscriber, BindMessBean bindMess) {
        JsonParam<BindMessBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(bindMess);
        final RegisterPatientCardBean cardBean = new RegisterPatientCardBean();

        bindCardService.bindCard(jsonParam)
                .flatMap(new Func1<D<String>, Observable<BindCardBean>>() {
                    @Override
                    public Observable<BindCardBean> call(D<String> stringD) {
                        logInfo("提交预约申请的返回:" + stringD.toString());
                        Gson gson = new Gson();
                        BindCardBean result = null;
                        try {
                            result = gson.fromJson(stringD.d, BindCardBean.class);

                            cardBean.setPatientName(result.getPatientName());
                            cardBean.setMobile(result.getMobile());
                            cardBean.setIdCard(result.getSocialCode());
                            cardBean.setSex(result.getSex());
                            cardBean.setBirthday(result.getBirthDay());
                            cardBean.setAddress(result.getAddress());
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "绑定就诊卡返回信息解析失败:" + e.toString() + stringD.d);
                            Toast.makeText(UtilApplication.getContextObject(), "绑定就诊卡返回信息解析失败", Toast.LENGTH_SHORT).show();
                            //return Observable.<BindCardBean>error(new NullPointerException("Token is null!"));
                        }
                        return Observable.just(result);
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException) {

                                    return creadPatientCard2(cardBean)
                                            .doOnNext(new Action1<CreateCardResultBean>() {
                                                @Override
                                                public void call(CreateCardResultBean createCardResultBean) {

                                                }
                                            });
                                }
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bindCardSubscriber);
    }

    /**
     * 获取我给予他人的授权
     *
     * @param authorizeSubscriber
     * @param authorize
     */
    public void getToAuthorize(Subscriber<ResquestResult<List<AuthorizeResultBean>>> authorizeSubscriber, UserIdRequestBean authorize) {
        toAuthorizesService.getToAuthorizes(authorize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authorizeSubscriber);
    }

    /**
     * 获取他人给予我的授权
     *
     * @param authorizeSubscriber
     * @param authorize
     */
    public void getFromAuthorize(Subscriber<ResquestResult<List<AuthorizeResultBean>>> authorizeSubscriber, UserIdRequestBean authorize) {
        fromAuthorizesService.getFromAuthorizes(authorize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authorizeSubscriber);
    }

    /**
     * 取消取消绑定就诊卡
     */
    public void getCancleCardResult(Subscriber<ResquestResult> cancleResult, DelectCardBean delectCardRequest) {
        cancleCardService.getCancleResult(delectCardRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cancleResult);
    }

    /**
     * 取消授权
     */
    public void getCancleAuthResult(Subscriber<ResquestResult> resultSubscriber, CancleAuthorizeRequestBean request) {
        cancleAuthorizeService.cancleAuthorize(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultSubscriber);
    }

    /**
     * 获取报告查询列表
     */
    public void getReports(Subscriber<ResquestResult<List<ReportsBean>>> reportSubscriber, ReportRequestBean request) {
        getReportService.getReport(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportSubscriber);
    }

    /**
     * 获取预约详情
     *
     * @param appSubscriber
     * @param request
     */
    public void getAppDetial(Subscriber<ResquestResult<AppDetialBean>> appSubscriber, AppDetialRequestBean request) {
        getAppDetialService.getAppDetial(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(appSubscriber);
    }

    /**
     * HOS端的取消预约
     */
    public Observable<ResquestResult> hosCancleBooking(AppDetialRequestBean request) {
        logInfoBean(request);
        return hosCancelBookingService.cancelBooking(request);
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(resultSubscriber);
    }

    /**
     * his端取消预约
     */
    public Observable<D<String>> hisCancelBook(HisCancleBookReqBean hisCancleBookReq) {
        JsonParam<HisCancleBookReqBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(hisCancleBookReq);
        logInfo(gson.toJson(jsonParam));
        return hisCancelBookingService.hisCancelBooking(jsonParam);
    }

    /**
     * 组合取消预约
     */
    public void cancleBooking(Subscriber<ResquestResult> resultSubscriber,
                              HisCancleBookReqBean hisCancleBookReq, final AppDetialRequestBean request) {
        hisCancelBook(hisCancleBookReq)
                .flatMap(new Func1<D<String>, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(D<String> d) {
                        if ("true".equals(d.d)) {
                            logInfo("his取消预约结果:" + d.d);
                            return hosCancleBooking(request);
                        } else {
                            ResquestResult errResult = new ResquestResult();
                            errResult.ResultCode = "false";
                            errResult.ResultMsg = "取消预约失败";
                            return Observable.just(errResult);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultSubscriber);
    }

    /**
     * 获取检验报告的主列表
     */
    public void getListInspec(Subscriber<List<MainInspectionBean>> inspecSubscriber, MainInspeRequest request) {
        JsonParam<MainInspeRequest> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(request);
        Log.i(GeneralConfig.LOG_TAG, "获取主检验列表的请求体:" +gson.toJson(jsonParam));
        getListInspecService.getListInspection(jsonParam)
                .map(new Func1<D<String>, List<MainInspectionBean>>() {
                    @Override
                    public List<MainInspectionBean> call(D<String> d) {
                        Log.i(GeneralConfig.LOG_TAG, "获取主检验列表的返回体:" +d.toString());
                        List<MainInspectionBean> list = null;
                        try {
//                            result = gson.fromJson(d.d, MainInspectionBean.class);
                            list = gson.fromJson(d.d, new TypeToken<List<MainInspectionBean>>(){}.getType());

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "获取主检验列表返回体解析失败:" + e.toString());
                            Toast.makeText(UtilApplication.getContextObject(), "获取主检验列表返回体解析失败", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "获取主检验列表返回体解析失败:" + e.toString());
                            Toast.makeText(UtilApplication.getContextObject(), "获取主检验列表返回体解析失败", Toast.LENGTH_SHORT).show();
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(inspecSubscriber);
    }

    /**
     * 获取检验报告的详情列表
     */
    public void getInspecDetialList(Subscriber<InspectionDetailBean> insDeSubscriber, InspeDetialRequestBean request) {
        JsonParam<InspeDetialRequestBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(request);
        Log.i(GeneralConfig.LOG_TAG, "获取检验详情列表的请求体:" +gson.toJson(jsonParam));
        getSingleLisByTypeService.getInspeDteil(jsonParam)
                .map(new Func1<D<String>,InspectionDetailBean>() {
                    @Override
                    public InspectionDetailBean call(D<String> d) {
                        InspectionDetailBean ins = null;
                        Log.i(GeneralConfig.LOG_TAG, "获取检验详情列表的返回体:" +d.d);
                        try {
                            ins = gson.fromJson(d.d, new TypeToken<InspectionDetailBean>(){}.getType());
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "获取检验详情列表返回体解析失败:" + e.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "获取检验详情列表处理异常:" + e.toString());
                        }
                        return ins;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(insDeSubscriber);
    }

    /**
     * 获取检查列表
     * @param listCheckSubscriber
     * @param request
     */
    public void getCheckList(Subscriber<List<CheckBean>> listCheckSubscriber, CheckListRequestBean request) {
        JsonParam<CheckListRequestBean> jsonParam = new JsonParam<>();
        jsonParam.setJsonParam(request);
        Log.i(GeneralConfig.LOG_TAG, "获取检查列表的请求体:" +gson.toJson(jsonParam));
        getBodyCheckListService.getCheckList(jsonParam)
                .map(new Func1<D<String>, List<CheckBean>>() {
                    @Override
                    public List<CheckBean> call(D<String> d) {
                        Log.i(GeneralConfig.LOG_TAG, "获取检查列表的返回体:" +d.d);
                        List<CheckBean> listCheck = new ArrayList<CheckBean>();
                        try {
                            listCheck = gson.fromJson(d.d, new TypeToken<List<CheckBean>>(){}.getType());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(GeneralConfig.LOG_TAG, "返回的检查体解析异常:"+e.toString());
                        }
                        return listCheck;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCheckSubscriber);
    }

    /**
     * 新增授权
     */
    public void addAuthorize(Subscriber<ResquestResult> resultSubscriber, AddAuthorizeRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG, "新增授权的请求体:" +gson.toJson(request));
        addAuthorizeService.addAuthorize(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultSubscriber);

    }

    /**
     * 获取最近的在线诊所排队信息
     */
    public void currentQueue(Subscriber<ResquestResult<QueueBean>> queueSub,UserIdRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"获取最近排队的请求体:" + gson.toJson(request));
        currentQueueService.currentQueue(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(queueSub);
    }

    /**
     * 在线诊所排队
     */
    public void getQueue(Subscriber<ResquestResult<QueueBean>> queueSub,QueueRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"在线诊所排队的请求体:" + gson.toJson(request));
        queueService.addQueue(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(queueSub);
    }

    /**
     * 退出在线诊所排队
     */
    public Observable<ResquestResult> quitQueue(QuitQueueRequestBean ruquest) {
        return quitQueueService.quitQueue(ruquest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 进入诊室
     */
    public Observable<ResquestResult<ClinicBean>> enterClinic(EnterClinicRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"进入诊室的请求体:"+gson.toJson(request));
        return hosApi.enterClinic(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 退出在线诊室
     */
    public void quitClinic(Subscriber<ResquestResult> quitCliSub, QuitClinicRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"退出在线诊室的请求体:"+gson.toJson(request));
        hosApi.quitClinic(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quitCliSub);
    }

    /**
     * 分页获取健康档案患者列表
     */
    public void getHealthPatients(Subscriber<ResquestCountResult<List<HealthPatientBean>>> healthListSub, PageHealthPatRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"分页获取健康档案患者列表:"+gson.toJson(request));
        hosApi.getHealthPatients(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(healthListSub);
    }

    /**
     * 获取健康档案基本信息
     */
    public void getReportBasic(Subscriber<ResquestCountResult<HealthBasicBean>> reportBasicSub, UserIdRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"获取健康档案基本信息:"+gson.toJson(request));
        hosApi.getReportBasic(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportBasicSub);
    }
    /**
     * 获取健康档案临床报告
     */
    public void getReportClinical(Subscriber<ResquestCountResult<List<ClinicalBean>>> reportClinicalSub, PagePatIdRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"获取健康档案临床报告:"+gson.toJson(request));
        hosApi.getReportClinical(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportClinicalSub);
    }
    /**
     * 获取健康档案健康信息
     */
    public void getReportHealths(Subscriber<ResquestCountResult<List<ReportHealths>>> reportHealthsSub, PagePatIdRequestBean request) {
        Log.i(GeneralConfig.LOG_TAG,"获取健康档案健康信息:"+gson.toJson(request));
        hosApi.getReportHealths(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportHealthsSub);
    }

}
