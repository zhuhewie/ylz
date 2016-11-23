package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-11-11.
 * 健康档案患者列表的信息
 */

public class HealthPatientBean implements Parcelable {

    /**
     * UserId : sample string 1
     * HospitalId : sample string 2
     * PatientId : sample string 3
     * Avatar : sample string 4
     * Name : sample string 5
     * Sex : sample string 6
     * Age : 7
     * IsInHospital : true
     */

    private String UserId;      //用户ID
    private String HospitalId;  //医院ID
    private String PatientId;   //患者ID
    private String Avatar;      //头像
    private String Name;        //名称
    private String Sex;         //性别
    private int Age;            //年龄
    private boolean IsInHospital;//是否住院中

    protected HealthPatientBean(Parcel in) {
        UserId = in.readString();
        HospitalId = in.readString();
        PatientId = in.readString();
        Avatar = in.readString();
        Name = in.readString();
        Sex = in.readString();
        Age = in.readInt();
        IsInHospital = in.readByte() != 0;
    }

    public static final Creator<HealthPatientBean> CREATOR = new Creator<HealthPatientBean>() {
        @Override
        public HealthPatientBean createFromParcel(Parcel in) {
            return new HealthPatientBean(in);
        }

        @Override
        public HealthPatientBean[] newArray(int size) {
            return new HealthPatientBean[size];
        }
    };

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
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

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public boolean isIsInHospital() {
        return IsInHospital;
    }

    public void setIsInHospital(boolean IsInHospital) {
        this.IsInHospital = IsInHospital;
    }

    @Override
    public String toString() {
        return "HealthPatientBean{" +
                "UserId='" + UserId + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", Avatar='" + Avatar + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", IsInHospital=" + IsInHospital +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UserId);
        dest.writeString(HospitalId);
        dest.writeString(PatientId);
        dest.writeString(Avatar);
        dest.writeString(Name);
        dest.writeString(Sex);
        dest.writeInt(Age);
        dest.writeByte((byte) (IsInHospital ? 1 : 0));
    }
}
