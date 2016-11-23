package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-29.
 * 取消授权的请求实体
 */

public class CancleAuthorizeRequestBean {

    /**
     * AuthorizeId : sample string 1
     */

    private String AuthorizeId;

    public String getAuthorizeId() {
        return AuthorizeId;
    }

    public void setAuthorizeId(String AuthorizeId) {
        this.AuthorizeId = AuthorizeId;
    }

    @Override
    public String toString() {
        return "CancleAuthorizeRequestBean{" +
                "AuthorizeId='" + AuthorizeId + '\'' +
                '}';
    }
}
