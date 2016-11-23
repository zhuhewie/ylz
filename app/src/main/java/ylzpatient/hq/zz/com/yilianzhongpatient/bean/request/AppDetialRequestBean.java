package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-1.
 * 获取预约信息详情的的请求实体
 * hos取消预约的请求实体
 */

public class AppDetialRequestBean {

    /**
     * BookingSn : 1
     */

    private String BookingSn;

    public String getBookingSn() {
        return BookingSn;
    }

    public void setBookingSn(String BookingSn) {
        this.BookingSn = BookingSn;
    }

    @Override
    public String toString() {
        return "AppDetialRequestBean{" +
                "BookingSn='" + BookingSn + '\'' +
                '}';
    }
}
