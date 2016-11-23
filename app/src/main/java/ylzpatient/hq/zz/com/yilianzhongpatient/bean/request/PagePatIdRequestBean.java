package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-14.
 * 根据患者PatientId,分页请求的请求体
 */

public class PagePatIdRequestBean {

    /**
     * PatientId : sample string 1
     * PageNum : 2
     * PageSize : 3
     */

    private String PatientId;   //患者ID
    private int PageNum;        //请求页码（从 0 开始）
    private int PageSize;       //页码大小

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
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
}
