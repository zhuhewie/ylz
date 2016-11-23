package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-10-20.
 */

public class OnlineDoctorBean implements Parcelable {

    /**
     * UserId : 1
     * DoctorId : 1
     * DoctorCode : 1002
     * DoctorName : 欧阳永红
     * DoctorTitle : 主任医师
     * DoctorAvatar : http://192.168.1.29:8088//Image/Booking/aa4.jpg
     * HospitalId : HQ_1000
     * HospitalName : 广州惠侨
     * HospitalServer : http://192.168.1.211:8006/wm.aspx/
     * DepartmentCode :
     * Department :
     * BeGoodAt :
     * WaitingCount : 0
     * MaxWaitingCount : 10
     * StartTime : 8:00
     * EndTime : 20:00
     */

    private String UserId;      //医生用户ID
    private String DoctorId;       //医生ID
    private String DoctorCode;  //医生编号
    private String DoctorName;  //医生姓名
    private String DoctorTitle; //医生职称
    private String DoctorAvatar;//医生头像
    private String HospitalId;  //医院ID
    private String HospitalName;//医院名称
    private String HospitalServer;//医院服务器地址
    private String DepartmentCode;//科室编号
    private String Department;  //科室
    private String BeGoodAt;    //擅长
    private int WaitingCount;   //等待人数
    private int MaxWaitingCount;//可等待总人数
    private String StartTime;   //问诊开始时间
    private String EndTime;     //问诊结束时间

    public OnlineDoctorBean() {
    }

    protected OnlineDoctorBean(Parcel in) {
        UserId = in.readString();
        DoctorId = in.readString();
        DoctorCode = in.readString();
        DoctorName = in.readString();
        DoctorTitle = in.readString();
        DoctorAvatar = in.readString();
        HospitalId = in.readString();
        HospitalName = in.readString();
        HospitalServer = in.readString();
        DepartmentCode = in.readString();
        Department = in.readString();
        BeGoodAt = in.readString();
        WaitingCount = in.readInt();
        MaxWaitingCount = in.readInt();
        StartTime = in.readString();
        EndTime = in.readString();
    }

    public static final Creator<OnlineDoctorBean> CREATOR = new Creator<OnlineDoctorBean>() {
        @Override
        public OnlineDoctorBean createFromParcel(Parcel in) {
            return new OnlineDoctorBean(in);
        }

        @Override
        public OnlineDoctorBean[] newArray(int size) {
            return new OnlineDoctorBean[size];
        }
    };

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String DoctorId) {
        this.DoctorId = DoctorId;
    }

    public String getDoctorCode() {
        return DoctorCode;
    }

    public void setDoctorCode(String DoctorCode) {
        this.DoctorCode = DoctorCode;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public String getDoctorTitle() {
        return DoctorTitle;
    }

    public void setDoctorTitle(String DoctorTitle) {
        this.DoctorTitle = DoctorTitle;
    }

    public String getDoctorAvatar() {
        return DoctorAvatar;
    }

    public void setDoctorAvatar(String DoctorAvatar) {
        this.DoctorAvatar = DoctorAvatar;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getHospitalServer() {
        return HospitalServer;
    }

    public void setHospitalServer(String HospitalServer) {
        this.HospitalServer = HospitalServer;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String DepartmentCode) {
        this.DepartmentCode = DepartmentCode;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getBeGoodAt() {
        return BeGoodAt;
    }

    public void setBeGoodAt(String BeGoodAt) {
        this.BeGoodAt = BeGoodAt;
    }

    public int getWaitingCount() {
        return WaitingCount;
    }

    public void setWaitingCount(int WaitingCount) {
        this.WaitingCount = WaitingCount;
    }

    public int getMaxWaitingCount() {
        return MaxWaitingCount;
    }

    public void setMaxWaitingCount(int MaxWaitingCount) {
        this.MaxWaitingCount = MaxWaitingCount;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UserId);
        dest.writeString(DoctorId);
        dest.writeString(DoctorCode);
        dest.writeString(DoctorName);
        dest.writeString(DoctorTitle);
        dest.writeString(DoctorAvatar);
        dest.writeString(HospitalId);
        dest.writeString(HospitalName);
        dest.writeString(HospitalServer);
        dest.writeString(DepartmentCode);
        dest.writeString(Department);
        dest.writeString(BeGoodAt);
        dest.writeInt(WaitingCount);
        dest.writeInt(MaxWaitingCount);
        dest.writeString(StartTime);
        dest.writeString(EndTime);
    }

    @Override
    public String toString() {
        return "OnlineDoctorBean{" +
                "UserId='" + UserId + '\'' +
                ", DoctorId=" + DoctorId +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", DoctorTitle='" + DoctorTitle + '\'' +
                ", DoctorAvatar='" + DoctorAvatar + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", HospitalName='" + HospitalName + '\'' +
                ", HospitalServer='" + HospitalServer + '\'' +
                ", DepartmentCode='" + DepartmentCode + '\'' +
                ", Department='" + Department + '\'' +
                ", BeGoodAt='" + BeGoodAt + '\'' +
                ", WaitingCount=" + WaitingCount +
                ", MaxWaitingCount=" + MaxWaitingCount +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                '}';
    }
}
