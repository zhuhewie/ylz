package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-14.
 * 健康档案临床报告
 */

public class ClinicalBean {

    /**
     * HospitalName : sample string 1
     * DoctorName : sample string 2
     * ReportType : sample string 3
     * Time : 2016-11-14 16:28:09
     */

    private String HospitalName;//医院名称
    private String DoctorName;  //医生名称
    private String ReportType;  //报告类型（00门诊/01住院）
    private String Time;        //报告时间

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String ReportType) {
        this.ReportType = ReportType;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
