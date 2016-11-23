package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-10-27.
 * 获取我给予他人的授权
 * 获取他人给予我的授权
 * 的网络返回接收实体
 */

public class AuthorizeResultBean {

    /**
     * AuthorizeId : 3
     * Name : 管理1
     * Age : 1
     * Sex : 女
     * Time : 2016-10-09 16:09:57
     * Days : 5
     */

    private String AuthorizeId;
    private String Name;
    private int Age;
    private String Sex;
    private String Time;
    private String Days;

    public String getAuthorizeId() {
        return AuthorizeId;
    }

    public void setAuthorizeId(String AuthorizeId) {
        this.AuthorizeId = AuthorizeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String Days) {
        this.Days = Days;
    }

    @Override
    public String toString() {
        return "AuthorizeResultBean{" +
                "AuthorizeId='" + AuthorizeId + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Sex='" + Sex + '\'' +
                ", Time='" + Time + '\'' +
                ", Days='" + Days + '\'' +
                '}';
    }
}
