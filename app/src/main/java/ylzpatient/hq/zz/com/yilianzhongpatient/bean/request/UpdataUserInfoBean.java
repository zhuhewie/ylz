package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-20.
 */

public class UpdataUserInfoBean {

    /**
     * UserId : sample string 1
     * LoginName : sample string 2
     * OldPassword : sample string 3
     * Password : sample string 4
     * Mobile : sample string 5
     * AvatarFileName : sample string 6
     * Avatar : sample string 7
     * Email : sample string 8
     * UpdateType : 0
     */

    private String UserId;
    private String LoginName;
    private String OldPassword;
    private String Password;
    private String Mobile;
    private String AvatarFileName;
    private String Avatar;
    private String Email;
    private int UpdateType;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getAvatarFileName() {
        return AvatarFileName;
    }

    public void setAvatarFileName(String AvatarFileName) {
        this.AvatarFileName = AvatarFileName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getUpdateType() {
        return UpdateType;
    }

    public void setUpdateType(int UpdateType) {
        this.UpdateType = UpdateType;
    }

    @Override
    public String toString() {
        return "UpdataUserInfoBean{" +
                "UserId='" + UserId + '\'' +
                ", LoginName='" + LoginName + '\'' +
                ", OldPassword='" + OldPassword + '\'' +
                ", Password='" + Password + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", AvatarFileName='" + AvatarFileName + '\'' +
                ", Avatar='" + Avatar + '\'' +
                ", Email='" + Email + '\'' +
                ", UpdateType=" + UpdateType +
                '}';
    }
}
