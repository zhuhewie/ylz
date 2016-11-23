package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-20.
 */

public class RegisterBean  {

    /**
     * LoginName : sample string 1
     * LoginPwd : sample string 2
     * Mobile : sample string 3
     */

    private String LoginName;
    private String LoginPwd;
    private String Mobile;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }

    public void setLoginPwd(String LoginPwd) {
        this.LoginPwd = LoginPwd;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "LoginName='" + LoginName + '\'' +
                ", LoginPwd='" + LoginPwd + '\'' +
                ", Mobile='" + Mobile + '\'' +
                '}';
    }
}
