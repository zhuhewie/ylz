package ylzpatient.hq.zz.com.yilianzhongpatient.http;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.EnterClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageHealthPatRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PagePatIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicalBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthBasicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReportHealths;

/**
 * Created by Administrator on 2016-11-9.
 * 新的HOS联网api
 */

public interface ServiceNewApi {
    //进入在线诊所
    @POST("user/enterClinic")
    Observable<ResquestResult<ClinicBean>> enterClinic(@Body EnterClinicRequestBean request);

    //放弃在线问诊
    @POST("user/quitClinic")
    Observable<ResquestResult<ClinicBean>> quitClinic(@Body QuitClinicRequestBean request);

    //获取健康档案患者列表
    @POST("user/healthReportPatients")
    Observable<ResquestCountResult<List<HealthPatientBean>>> getHealthPatients(@Body PageHealthPatRequestBean request);

    //获取健康档案基本信息
    @POST("user/healthReportBasic")
    Observable<ResquestCountResult<HealthBasicBean>> getReportBasic(@Body UserIdRequestBean request);

    //获取健康档案临床报告
    @POST("user/healthReportClinicals")
    Observable<ResquestCountResult<List<ClinicalBean>>> getReportClinical(@Body PagePatIdRequestBean request);

    //获取健康档案健康信息
    @POST("user/healthReportHealths")
    Observable<ResquestCountResult<List<ReportHealths>>> getReportHealths(@Body PagePatIdRequestBean request);

}
