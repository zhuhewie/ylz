package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-26.
 * v2
 * 新增就诊卡的请求实体
 */

public class AddPatientCerdBean {

    /**
     * UserId : qyszyy-12345678913233
     * PatientId : 000042536600
     * HospitalId : HQ_0004
     * Name : 欧阳启明
     * Sex : 男
     * SocialNo : 432822700619861
     * Birthday : 1970-06-19 00:00:00
     * Mobile : 13213142939
     * CardNo : 0264745
     */

    private String UserId;
    private String PatientId;
    private String HospitalId;
    private String Name;
    private String Sex;
    private String SocialNo;
    private String Birthday;
    private String Mobile;
    private String CardNo;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getSocialNo() {
        return SocialNo;
    }

    public void setSocialNo(String SocialNo) {
        this.SocialNo = SocialNo;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    @Override
    public String toString() {
        return "AddPatientCerdBean{" +
                "UserId='" + UserId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                ", SocialNo='" + SocialNo + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", CardNo='" + CardNo + '\'' +
                '}';
    }
}
