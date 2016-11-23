package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-20.
 * 获取在线医生的bean
 */

public class PageRequestBean {

    /**
     * PageNum : 1
     * PageSize : 2
     */

    public int PageNum;
    public int PageSize;

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
        return "PageRequestBean{" +
                "PageNum=" + PageNum +
                ", PageSize=" + PageSize +
                '}';
    }
}
