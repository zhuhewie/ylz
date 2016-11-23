package ylzpatient.hq.zz.com.yilianzhongpatient.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
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
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.HisCancleBookReqBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.InspeDetialRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.LoginBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.MainInspeRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PatientIdCardsRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitQueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RecentAppBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RegisterBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RegisterPatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.ReportRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UpdataUserInfoBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserId;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppDetialBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AuthorizeResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.QueueBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReportsBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;

/**
 * Created by Administrator on 2016-9-12.
 */
public class ServiceApi {

    /**
     * 获取首页数据
     */
    public interface index {
        @POST("user/index")
        Observable<ResquestResult<IndexBean>> getIndex();
    }

    /**
     * 登录的retrofit接口
     */
    public interface LoginService {
        @POST("login")
        Call<ResquestResult<UserBean>> addUser(@Body LoginBean user);
    }

    /**
     * retrofit 与rxjava结合的登录接口
     */
    public interface RxLoginService {
        @POST("user/login")
        Observable<ResquestResult<UserBean>> getUser(@Body LoginBean user);
    }

    /**
     * retrofit 与rxjava结合的登录接口
     */
    public interface RxLogintESTService {
        @POST("user/login")
        Observable<ResquestResult<UserTestBean_2>> getUser_2(@Body LoginBean user);
    }

    /**
     * 获取医院列表
     */
    public interface hospitalService {
        @GET("org")
        Observable<ResquestResult<List<Hopital>>> getHospitalList();
    }

    /**
     * 获取预约科室列表和医生列表
     */
    public interface departmentAndDoctorService {
        @POST("GetBookingDeptDoctorList")
        Observable<D<List<DepartmentBean>>> getDepartmentDoctor(@Body JsonParam<TimeBean> time);
    }

    /**
     * 测试一下,返回String 在自己解析gson
     * 获取预约科室列表和医生列表
     */
    public interface departmentAndDoctorService2 {
        @POST("GetBookingDeptDoctorList")
        Observable<D<String>> getDepartmentDoctor(@Body JsonParam<TimeBean> time);
    }

    /**
     * 根据医生id 科室编码,开始时间和结束时间,获取该医生的指定时间内的预约信息
     */
    public interface appDoctorDateService {
        @POST("GetEnableBookDoctorList")
        Observable<D<String>> getAppDoctorDate(@Body JsonParam<AppTimeBean> time);
    }

    /**
     * 获取就诊卡信息
     * UserId:登录人的id
     */
    public interface patientCardService {
        @POST("user/bindlist")
        Observable<ResquestResult<List<PatientCardBean>>> getPatientCard(@Body UserId userid);
    }

    /**
     * 获取就诊卡列表(新的)
     * UserId:登录人的id
     */
    public interface getBindCardListService {
        @POST("user/patientIdCards")
        Observable<ResquestResult<List<CardBean>>> getBindCardList(@Body PatientIdCardsRequest userId);
    }



    /**
     * 注册就诊卡 v1
     */
    public interface newPatientCardService {
        @POST("RegisterOutPatient")
        Observable<D<String>> creatPatCard(@Body JsonParam<RegisterPatientCardBean> mess);
    }

    /**
     * 新增就诊卡 v2
     */
    public interface addPatientIdCardService {
        @POST("user/addPatientIdCard")
        Observable<ResquestResult> addCard(@Body AddPatientCerdBean addPatientCerd);
    }

    /**
     * 提交预约信息给HIS (亚玲)
     */
    public interface appMessageService {
        @POST("ExecBooking")
        Observable<D<String>> sendAppMess(@Body JsonParam jsonParam);
    }

    /**
     * 提交预约信息给hos (湘智)
     */
    public interface addClinicBookingService {
        @POST("user/addClinicBooking")
        Observable<ResquestResult> addClinicBooking(@Body AddClinicBookingBean addClinicBooking);

    }

    /**
     * 获取在线诊所医生
     */
    public interface onlineDoctorService {
        @POST("user/onlineDoctors")
        Observable<ResquestCountResult<List<OnlineDoctorBean>>> getOnlineDoctor(@Body PageRequestBean page);
    }

    /**
     * 注册新用户
     */
    public interface registerService {
        @POST("user/register")
        Observable<ResquestResult<String>> sendRegister(@Body RegisterBean register);
    }

    /**
     * 更新用户信息
     */
    public interface updataUserService {
        @POST("user/update")
        Observable<ResquestResult<String>> updataUser(@Body UpdataUserInfoBean updataUser);
    }

    /**
     * 获取医院组织列表,新建就诊卡界面,
     */
    public interface hosOrgService {
        @GET("org")
        Observable<ResquestResult<List<Hopital>>> getHosOrg();
    }

    /**
     * 获取最近预约列表
     */
    public interface recentBookService {
        @POST("user/recentBookings")
        Observable<ResquestCountResult<List<AppMessBean>>> getRecentApp(@Body RecentAppBean recentApp);
    }

    /**
     * 绑定就诊卡
     */
    public interface bindCardService {
        @POST("GetBookingOutPatientInfo")
        Observable<D<String>> bindCard(@Body JsonParam<BindMessBean> jsonParam);

    }

    /**
     * 获取我给予他人的授权
     */
    public interface toAuthorizesService {
        @POST("user/toAuthorizes")
        Observable<ResquestResult<List<AuthorizeResultBean>>> getToAuthorizes(@Body UserIdRequestBean request);
    }

    /**
     * 获取他人给我的授权
     */
    public interface fromAuthorizesService {
        @POST("user/fromAuthorizes")
        Observable<ResquestResult<List<AuthorizeResultBean>>> getFromAuthorizes(@Body UserIdRequestBean request);
    }

    /**
     * 取消授权
     */
    public interface cancleAuthorizeService {
        @POST("user/cancleAuthorize")
        Observable<ResquestResult> cancleAuthorize(@Body CancleAuthorizeRequestBean request);
    }

    /**
     * 取消绑定就诊卡
     */
    public interface cancleCardService {
        @POST("user/deletePatientIdCard")
        Observable<ResquestResult> getCancleResult(@Body DelectCardBean cancleCardRequest);
    }

    /**
     * 获取报告查询列表
     */
    public interface getReportService {
        @POST("user/reports")
        Observable<ResquestResult<List<ReportsBean>>> getReport(@Body ReportRequestBean request);
    }

    /**
     * 获取门诊预约详情
     */
    public interface getAppDetialService {
        @POST("user/clinicBooking")
        Observable<ResquestResult<AppDetialBean>> getAppDetial(@Body AppDetialRequestBean bookingSn);
    }

    /**
     * hos端取消预约
     */
    public interface hosCancelBookingService {
        @POST("user/cancelBooking")
        Observable<ResquestResult> cancelBooking(@Body AppDetialRequestBean  request);
    }

    /**
     * HIS端取消预约
     */
    public interface hisCancelBookingService {
        @POST("CancelBooking")
        Observable<D<String>> hisCancelBooking(@Body JsonParam<HisCancleBookReqBean> jsonParam);
    }

    /**
     * 从his端获取主检验报告信息
     */
    public interface getListInspecService{
        @POST("GetLisMainReportForDate")
        Observable<D<String>> getListInspection(@Body JsonParam<MainInspeRequest> jsonParam);
    }

    /**
     * 从his端获取检验报告的详细信息
     */
    public interface getSingleLisByTypeService {
        @POST("GetSingleLisResultByType")
        Observable<D<String>> getInspeDteil(@Body JsonParam<InspeDetialRequestBean> jsonParam);
    }

    /**
     * 从his端获取检查报告信息
     */
    public interface getBodyCheckListService {
        @POST("GetHistoryBodyCheckReportList")
        Observable<D<String>> getCheckList(@Body JsonParam<CheckListRequestBean> jsonParam);
    }

    /**
     * HOS新增授权
     */
    public interface addAuthorizeService {
        @POST("user/addAuthorize")
        Observable<ResquestResult> addAuthorize(@Body AddAuthorizeRequestBean request);
    }

    /**
     * 获取最近的在线诊所排队信息
     * user/currentQueue
     */
    public interface currentQueueService {
        @POST("user/currentQueue")
        Observable<ResquestResult<QueueBean>> currentQueue(@Body UserIdRequestBean request);
    }

    /**
     * 在线诊室排队
     */
    public interface queueService {
        @POST("user/queue")
        Observable<ResquestResult<QueueBean>> addQueue(@Body QueueRequestBean request);
    }

    /**
     * 退出在线诊所排队
     */
    public interface quitQueueService {
        @POST("user/quitQueue")
        Observable<ResquestResult> quitQueue(@Body QuitQueueRequestBean request);
    }
}
