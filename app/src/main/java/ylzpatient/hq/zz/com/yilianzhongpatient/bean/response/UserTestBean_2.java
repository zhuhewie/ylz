package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-10-25.
 */

public class UserTestBean_2 {

    /**
     * LoginName : admin5
     * Name : 温秀青
     * LoginPwd : 123456
     * Mobile : 18888888888
     * UserId : 2
     * Head : http://192.168.1.29:8088//app/UpLoad/20161010160852801060.jpg
     * CardType : 0
     * CardNo :
     * Email : Asfdasf@qq.com
     * OpenId :
     * QQId :
     * WbId :
     */

    private String LoginName;
    private String Name;
    private String LoginPwd;
    private String Mobile;
    private String UserId;
    private String Head;
    private int CardType;
    private String CardNo;
    private String Email;
    private String OpenId;
    private String QQId;
    private String WbId;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public String getHead() {
        return Head;
    }

    public void setHead(String Head) {
        this.Head = Head;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String OpenId) {
        this.OpenId = OpenId;
    }

    public String getQQId() {
        return QQId;
    }

    public void setQQId(String QQId) {
        this.QQId = QQId;
    }

    public String getWbId() {
        return WbId;
    }

    public void setWbId(String WbId) {
        this.WbId = WbId;
    }

    @Override
    public String toString() {
        return "UserTestBean_2{" +
                "LoginName='" + LoginName + '\'' +
                ", Name='" + Name + '\'' +
                ", LoginPwd='" + LoginPwd + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Head='" + Head + '\'' +
                ", CardType=" + CardType +
                ", CardNo='" + CardNo + '\'' +
                ", Email='" + Email + '\'' +
                ", OpenId='" + OpenId + '\'' +
                ", QQId='" + QQId + '\'' +
                ", WbId='" + WbId + '\'' +
                '}';
    }
}
