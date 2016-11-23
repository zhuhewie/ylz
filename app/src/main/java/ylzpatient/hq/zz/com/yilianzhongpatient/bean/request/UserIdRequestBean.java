package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-27.
 * 1:获取他人给予我的授权 和 获取wo  给予他人的授权
 * de 的请求实体
 * 2:获取最近的在线诊所排队信息
 * 3:获取健康档案基本信息
 */

public class UserIdRequestBean {

    /**
     * UserId : sample string 1
     */

    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    @Override
    public String toString() {
        return "UserIdRequestBean{" +
                "UserId='" + UserId + '\'' +
                '}';
    }
}
