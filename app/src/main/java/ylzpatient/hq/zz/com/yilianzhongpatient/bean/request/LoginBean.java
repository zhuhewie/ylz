package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by admin on 2016/9/10.
 */
public class LoginBean {
    public String name;
    public String pwd;

    public LoginBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LoginBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
