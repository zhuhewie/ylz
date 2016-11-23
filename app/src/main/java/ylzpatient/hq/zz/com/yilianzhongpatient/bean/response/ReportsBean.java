package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-10-29.
 */

public class ReportsBean {
    /**
     * ReportNo : 报告编号
     * HospitalName : 医院名称
     * ReportType : 报告类型
     * PatientName : 患者姓名
     * ReportName : 报告类型
     * ReportTime : 报告时间 data "2016-10-29 17:25:20"
     */

    private String ReportNo;
    private String HospitalName;
    private String ReportType;
    private String PatientName;
    private String ReportName;
    private String ReportTime;

    public String getReportNo() {
        return ReportNo;
    }

    public void setReportNo(String ReportNo) {
        this.ReportNo = ReportNo;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String ReportType) {
        this.ReportType = ReportType;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String ReportName) {
        this.ReportName = ReportName;
    }

    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String ReportTime) {
        this.ReportTime = ReportTime;
    }

    @Override
    public String toString() {
        return "ReportsBean{" +
                "ReportNo='" + ReportNo + '\'' +
                ", HospitalName='" + HospitalName + '\'' +
                ", ReportType='" + ReportType + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", ReportName='" + ReportName + '\'' +
                ", ReportTime='" + ReportTime + '\'' +
                '}';
    }
}
