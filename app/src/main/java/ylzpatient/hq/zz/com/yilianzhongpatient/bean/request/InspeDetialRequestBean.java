package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-4.
 * 检验报告详情的请求实体
 */

public class InspeDetialRequestBean {

    /**
     * ReportTypeValue : 1
     * repID : 102052016102646
     */

    private int ReportTypeValue;
    private String repID;

    public int getReportTypeValue() {
        return ReportTypeValue;
    }

    public void setReportTypeValue(int ReportTypeValue) {
        this.ReportTypeValue = ReportTypeValue;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    @Override
    public String toString() {
        return "InspeDetialRequestBean{" +
                "ReportTypeValue=" + ReportTypeValue +
                ", repID='" + repID + '\'' +
                '}';
    }
}
