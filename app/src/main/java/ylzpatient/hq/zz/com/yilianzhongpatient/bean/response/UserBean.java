package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by admin on 2016/9/10.
 */
public class UserBean {
    public static String URL = "http://mp.hope-bridge.com:8066/api/user/login/";


    /**
     * LoginName : zhuqijie
     * LoginPwd : 123456
     * Mobile : 13710716542
     * UserId : qyszyy-12345678913233
     * UserName : null
     * UserEngName : null
     * Sex : null
     * Birth : null
     * Head : http://120.24.68.32:8088/UploadImages/HQ_1001/Doctors/朱其杰.jpg
     * Photo : null
     * CardType : 0
     * CardNo : 442210198501050111
     * Email : null
     * OpenId : null
     * WxGrantDate : null
     * QQId : null
     * QQGrantDate : null
     * WbId : null
     * WbGrantDate : null
     * ZfbId : null
     * ZfbGrantDate : null
     * modType : 0
     */

    private String LoginName;
    private String LoginPwd;
    private String Mobile;
    private String UserId;
    private Object UserName;
    private Object UserEngName;
    private Object Sex;
    private Object Birth;
    private String Head;
    private Object Photo;
    private int CardType;
    private String CardNo;
    private Object Email;
    private Object OpenId;
    private Object WxGrantDate;
    private Object QQId;
    private Object QQGrantDate;
    private Object WbId;
    private Object WbGrantDate;
    private Object ZfbId;
    private Object ZfbGrantDate;
    private int modType;

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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Object getUserName() {
        return UserName;
    }

    public void setUserName(Object UserName) {
        this.UserName = UserName;
    }

    public Object getUserEngName() {
        return UserEngName;
    }

    public void setUserEngName(Object UserEngName) {
        this.UserEngName = UserEngName;
    }

    public Object getSex() {
        return Sex;
    }

    public void setSex(Object Sex) {
        this.Sex = Sex;
    }

    public Object getBirth() {
        return Birth;
    }

    public void setBirth(Object Birth) {
        this.Birth = Birth;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String Head) {
        this.Head = Head;
    }

    public Object getPhoto() {
        return Photo;
    }

    public void setPhoto(Object Photo) {
        this.Photo = Photo;
    }

    public int getCardType() {
        return CardType;
    }

    public void setCardType(int CardType) {
        this.CardType = CardType;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    public Object getEmail() {
        return Email;
    }

    public void setEmail(Object Email) {
        this.Email = Email;
    }

    public Object getOpenId() {
        return OpenId;
    }

    public void setOpenId(Object OpenId) {
        this.OpenId = OpenId;
    }

    public Object getWxGrantDate() {
        return WxGrantDate;
    }

    public void setWxGrantDate(Object WxGrantDate) {
        this.WxGrantDate = WxGrantDate;
    }

    public Object getQQId() {
        return QQId;
    }

    public void setQQId(Object QQId) {
        this.QQId = QQId;
    }

    public Object getQQGrantDate() {
        return QQGrantDate;
    }

    public void setQQGrantDate(Object QQGrantDate) {
        this.QQGrantDate = QQGrantDate;
    }

    public Object getWbId() {
        return WbId;
    }

    public void setWbId(Object WbId) {
        this.WbId = WbId;
    }

    public Object getWbGrantDate() {
        return WbGrantDate;
    }

    public void setWbGrantDate(Object WbGrantDate) {
        this.WbGrantDate = WbGrantDate;
    }

    public Object getZfbId() {
        return ZfbId;
    }

    public void setZfbId(Object ZfbId) {
        this.ZfbId = ZfbId;
    }

    public Object getZfbGrantDate() {
        return ZfbGrantDate;
    }

    public void setZfbGrantDate(Object ZfbGrantDate) {
        this.ZfbGrantDate = ZfbGrantDate;
    }

    public int getModType() {
        return modType;
    }

    public void setModType(int modType) {
        this.modType = modType;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "LoginName='" + LoginName + '\'' +
                ", LoginPwd='" + LoginPwd + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", UserId='" + UserId + '\'' +
                ", UserName=" + UserName +
                ", UserEngName=" + UserEngName +
                ", Sex=" + Sex +
                ", Birth=" + Birth +
                ", Head='" + Head + '\'' +
                ", Photo=" + Photo +
                ", CardType=" + CardType +
                ", CardNo='" + CardNo + '\'' +
                ", Email=" + Email +
                ", OpenId=" + OpenId +
                ", WxGrantDate=" + WxGrantDate +
                ", QQId=" + QQId +
                ", QQGrantDate=" + QQGrantDate +
                ", WbId=" + WbId +
                ", WbGrantDate=" + WbGrantDate +
                ", ZfbId=" + ZfbId +
                ", ZfbGrantDate=" + ZfbGrantDate +
                ", modType=" + modType +
                '}';
    }
}
