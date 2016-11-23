package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-8.
 * 添加授权的申请实体
 */

public class AddAuthorizeRequestBean {

    /**
     * UserId : sample string 1
     * AgentUserId : sample string 2
     */

    private String UserId;
    private String AgentUserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getAgentUserId() {
        return AgentUserId;
    }

    public void setAgentUserId(String AgentUserId) {
        this.AgentUserId = AgentUserId;
    }

    @Override
    public String toString() {
        return "AddAuthorizeRequestBean{" +
                "UserId='" + UserId + '\'' +
                ", AgentUserId='" + AgentUserId + '\'' +
                '}';
    }
}
