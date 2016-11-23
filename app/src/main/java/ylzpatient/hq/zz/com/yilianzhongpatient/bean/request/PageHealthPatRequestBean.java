package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-11.3
 * 分页获取健康档案患者列表的请求体
 */

public class PageHealthPatRequestBean {

    /**
     * UserId : sample string 1
     * PageNum : 2
     * PageSize : 3
     */

    private String UserId;  //用户ID
    private int PageNum;    //请求页码（从 0 开始）
    private int PageSize;   //页码大小

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
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
        return "PageHealthPatRequestBean{" +
                "UserId='" + UserId + '\'' +
                ", PageNum=" + PageNum +
                ", PageSize=" + PageSize +
                '}';
    }
}
