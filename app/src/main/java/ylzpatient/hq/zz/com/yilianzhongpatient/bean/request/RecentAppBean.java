package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-25.
 * 分页获取最近预约的请求bean
 */

public class RecentAppBean {

    /**
     * UserId : qyszyy-12345678913233
     * PageNum : 0
     * PageSize : 10
     */

    private String UserId;
    private int PageNum;
    private int PageSize;

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
        return "RecentAppBean{" +
                "UserId='" + UserId + '\'' +
                ", PageNum=" + PageNum +
                ", PageSize=" + PageSize +
                '}';
    }
}
