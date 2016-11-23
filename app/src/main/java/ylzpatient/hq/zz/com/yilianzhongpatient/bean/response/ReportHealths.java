package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-14.
 * 健康档案健康信息
 */

public class ReportHealths {

    /**
     * Source : sample string 1
     * ReportName : sample string 2
     * ReportType : sample string 3
     * Time : 2016-11-14 16:59:34
     */

    private String Source;      //信息来源
    private String ReportName;  //报告名称
    private String ReportType;  //报告类型（02体检/03经络仪）
    private String Time;        //报告时间

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String ReportName) {
        this.ReportName = ReportName;
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
