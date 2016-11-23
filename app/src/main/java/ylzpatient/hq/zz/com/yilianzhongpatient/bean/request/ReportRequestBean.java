package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-29.
 * 获取报告查询列表
 */

public class ReportRequestBean {

    /**
     * UserId : 用户ID
     * PatientId : 患者ID
     * ReportType : 报告类型
     * StartTime : 查询开始时间
     * EndTime : 查询结束时间
     * PageNum :请求页码（从 0 开始）
     * PageSize :页码大小
     */

    private String UserId;
    private String PatientId;
    private String ReportType;
    private String StartTime;
    private String EndTime;
    private int PageNum;
    private int PageSize;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String ReportType) {
        this.ReportType = ReportType;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int PageNum) {
        this.PageNum = PageNum;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    @Override
    public String toString() {
        return "ReportRequestBean{" +
                "UserId='" + UserId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", ReportType='" + ReportType + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", PageNum=" + PageNum +
                ", PageSize=" + PageSize +
                '}';
    }
}
