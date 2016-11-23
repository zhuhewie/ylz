package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-11-9.
 * 进入聊天室的返回实体
 */

public class ClinicBean implements Parcelable{

    /**
     * ClinicId : sample string 1
     * DoctorUserId : sample string 2
     * DoctorAvatar : sample string 3
     */

    private String ClinicId;    //问诊ID
    private String DoctorUserId;//医生用户ID
    private String DoctorAvatar;//医生头像

    protected ClinicBean(Parcel in) {
        ClinicId = in.readString();
        DoctorUserId = in.readString();
        DoctorAvatar = in.readString();
    }

    public static final Creator<ClinicBean> CREATOR = new Creator<ClinicBean>() {
        @Override
        public ClinicBean createFromParcel(Parcel in) {
            return new ClinicBean(in);
        }

        @Override
        public ClinicBean[] newArray(int size) {
            return new ClinicBean[size];
        }
    };

    public String getClinicId() {
        return ClinicId;
    }

    public void setClinicId(String ClinicId) {
        this.ClinicId = ClinicId;
    }

    public String getDoctorUserId() {
        return DoctorUserId;
    }

    public void setDoctorUserId(String DoctorUserId) {
        this.DoctorUserId = DoctorUserId;
    }

    public String getDoctorAvatar() {
        return DoctorAvatar;
    }

    public void setDoctorAvatar(String DoctorAvatar) {
        this.DoctorAvatar = DoctorAvatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ClinicId);
        dest.writeString(DoctorUserId);
        dest.writeString(DoctorAvatar);
    }
}
