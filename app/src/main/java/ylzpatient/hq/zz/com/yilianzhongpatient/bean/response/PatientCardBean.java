package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-9-22.
 * 患者的就诊卡
 */
public class PatientCardBean implements Parcelable {

    /**
     * UserId : null
     * PatId : 000000000100
     * OrgId : HQ_1001
     * PatName : 陈伙群
     * PatVisCard : 0001400
     * PatInNo : null
     * PatEmpi : null
     * PatSex : null
     * PatBirth : null
     * PatMobile : 15112840868
     * PatSocilNo : null
     * PatInsuranceNo : null
     * PesCheckNo : null
     * BornPlace : null
     * MaritalSattus : null
     * PatAddress : null
     * UserRelation : null
     * IsActive : false
     * IsAttention : false
     * IsHolder : false
     * PatAge : null
     */

    public String UserId;
    public String PatId;
    public String OrgId;
    public String PatName;
    public String PatVisCard;
    public String PatInNo;
    public String PatEmpi;
    public String PatSex;
    public String PatBirth;
    public String PatMobile;
    public String PatSocilNo;
    public String PatInsuranceNo;
    public String PesCheckNo;
    public String BornPlace;
    public String MaritalSattus;
    public String PatAddress;
    public String UserRelation;
    public boolean IsActive;
    public boolean IsAttention;
    public boolean IsHolder;
    public String PatAge;

    public PatientCardBean() {
    }

    protected PatientCardBean(Parcel in) {
        UserId = in.readString();
        PatId = in.readString();
        OrgId = in.readString();
        PatName = in.readString();
        PatVisCard = in.readString();
        PatInNo = in.readString();
        PatEmpi = in.readString();
        PatSex = in.readString();
        PatBirth = in.readString();
        PatMobile = in.readString();
        PatSocilNo = in.readString();
        PatInsuranceNo = in.readString();
        PesCheckNo = in.readString();
        BornPlace = in.readString();
        MaritalSattus = in.readString();
        PatAddress = in.readString();
        UserRelation = in.readString();
        IsActive = in.readByte() != 0;
        IsAttention = in.readByte() != 0;
        IsHolder = in.readByte() != 0;
        PatAge = in.readString();
    }

    public static final Creator<PatientCardBean> CREATOR = new Creator<PatientCardBean>() {
        @Override
        public PatientCardBean createFromParcel(Parcel in) {
            return new PatientCardBean(in);
        }

        @Override
        public PatientCardBean[] newArray(int size) {
            return new PatientCardBean[size];
        }
    };

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getPatId() {
        return PatId;
    }

    public void setPatId(String PatId) {
        this.PatId = PatId;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String OrgId) {
        this.OrgId = OrgId;
    }

    public String getPatName() {
        return PatName;
    }

    public void setPatName(String PatName) {
        this.PatName = PatName;
    }

    public String getPatVisCard() {
        return PatVisCard;
    }

    public void setPatVisCard(String PatVisCard) {
        this.PatVisCard = PatVisCard;
    }

    public String getPatInNo() {
        return PatInNo;
    }

    public void setPatInNo(String PatInNo) {
        this.PatInNo = PatInNo;
    }

    public String getPatEmpi() {
        return PatEmpi;
    }

    public void setPatEmpi(String PatEmpi) {
        this.PatEmpi = PatEmpi;
    }

    public String getPatSex() {
        return PatSex;
    }

    public void setPatSex(String PatSex) {
        this.PatSex = PatSex;
    }

    public String getPatBirth() {
        return PatBirth;
    }

    public void setPatBirth(String PatBirth) {
        this.PatBirth = PatBirth;
    }

    public String getPatMobile() {
        return PatMobile;
    }

    public void setPatMobile(String PatMobile) {
        this.PatMobile = PatMobile;
    }

    public String getPatSocilNo() {
        return PatSocilNo;
    }

    public void setPatSocilNo(String PatSocilNo) {
        this.PatSocilNo = PatSocilNo;
    }

    public String getPatInsuranceNo() {
        return PatInsuranceNo;
    }

    public void setPatInsuranceNo(String PatInsuranceNo) {
        this.PatInsuranceNo = PatInsuranceNo;
    }

    public String getPesCheckNo() {
        return PesCheckNo;
    }

    public void setPesCheckNo(String PesCheckNo) {
        this.PesCheckNo = PesCheckNo;
    }

    public String getBornPlace() {
        return BornPlace;
    }

    public void setBornPlace(String BornPlace) {
        this.BornPlace = BornPlace;
    }

    public String getMaritalSattus() {
        return MaritalSattus;
    }

    public void setMaritalSattus(String MaritalSattus) {
        this.MaritalSattus = MaritalSattus;
    }

    public String getPatAddress() {
        return PatAddress;
    }

    public void setPatAddress(String PatAddress) {
        this.PatAddress = PatAddress;
    }

    public String getUserRelation() {
        return UserRelation;
    }

    public void setUserRelation(String UserRelation) {
        this.UserRelation = UserRelation;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public boolean isIsAttention() {
        return IsAttention;
    }

    public void setIsAttention(boolean IsAttention) {
        this.IsAttention = IsAttention;
    }

    public boolean isIsHolder() {
        return IsHolder;
    }

    public void setIsHolder(boolean IsHolder) {
        this.IsHolder = IsHolder;
    }

    public String getPatAge() {
        return PatAge;
    }

    public void setPatAge(String PatAge) {
        this.PatAge = PatAge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UserId);
        dest.writeString(PatId);
        dest.writeString(OrgId);
        dest.writeString(PatName);
        dest.writeString(PatVisCard);
        dest.writeString(PatInNo);
        dest.writeString(PatEmpi);
        dest.writeString(PatSex);
        dest.writeString(PatBirth);
        dest.writeString(PatMobile);
        dest.writeString(PatSocilNo);
        dest.writeString(PatInsuranceNo);
        dest.writeString(PesCheckNo);
        dest.writeString(BornPlace);
        dest.writeString(MaritalSattus);
        dest.writeString(PatAddress);
        dest.writeString(UserRelation);
        dest.writeByte((byte) (IsActive ? 1 : 0));
        dest.writeByte((byte) (IsAttention ? 1 : 0));
        dest.writeByte((byte) (IsHolder ? 1 : 0));
        dest.writeString(PatAge);
    }

    @Override
    public String toString() {
        return "PatientCardBean{" +
                "UserId='" + UserId + '\'' +
                ", PatId='" + PatId + '\'' +
                ", OrgId='" + OrgId + '\'' +
                ", PatName='" + PatName + '\'' +
                ", PatVisCard='" + PatVisCard + '\'' +
                ", PatInNo='" + PatInNo + '\'' +
                ", PatEmpi='" + PatEmpi + '\'' +
                ", PatSex='" + PatSex + '\'' +
                ", PatBirth='" + PatBirth + '\'' +
                ", PatMobile='" + PatMobile + '\'' +
                ", PatSocilNo='" + PatSocilNo + '\'' +
                ", PatInsuranceNo='" + PatInsuranceNo + '\'' +
                ", PesCheckNo='" + PesCheckNo + '\'' +
                ", BornPlace='" + BornPlace + '\'' +
                ", MaritalSattus='" + MaritalSattus + '\'' +
                ", PatAddress='" + PatAddress + '\'' +
                ", UserRelation='" + UserRelation + '\'' +
                ", IsActive=" + IsActive +
                ", IsAttention=" + IsAttention +
                ", IsHolder=" + IsHolder +
                ", PatAge='" + PatAge + '\'' +
                '}';
    }
}
