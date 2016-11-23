package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-11-9.
 */

public class QueueBean implements Parcelable{

    /**
     * QueueSn : 1478655176534
     * QueueNo : 1
     * WaitingCount : 1
     * PatientId : 3003
     * PatientName : 杨湘智
     * DoctorId : 1856
     * DoctorUserId : 3
     * DoctorCode : 1001
     * DoctorName : 夏卫红
     * HospitalId : HQ_8001
     * HospitalName : 清远人民医院
     * DoctorAvatar : http://192.168.1.29:8088//app/UpLoad/20161010160852801060.jpg
     * DepartmentCode : A05
     * DepartmentName : 皮肤科
     * Status : 1
     * Time : 2016-11-09 09:32:56
     * ClinicId : 800120161109093436047776
     * CanEnter : true
     */

    private String QueueSn;     //排队ID
    private int QueueNo;        //排队编号
    private int WaitingCount;   //前面还剩多少人
    private String PatientId;   //患者ID
    private String PatientName; //问诊人
    private String DoctorId;    //医生ID
    private String DoctorUserId;//医生用户ID
    private String DoctorCode;  //医生编号
    private String DoctorName;  //医生姓名
    private String HospitalId;  //医院ID
    private String HospitalName;//医院名称
    private String DoctorAvatar;//医生头像
    private String DepartmentCode;//科室编号
    private String DepartmentName;//科室名称
    private int Status;         //状态
    private String Time;        //排队时间
    private String ClinicId;    //问诊ID
    private boolean CanEnter;   //可否进入诊室

    protected QueueBean(Parcel in) {
        QueueSn = in.readString();
        QueueNo = in.readInt();
        WaitingCount = in.readInt();
        PatientId = in.readString();
        PatientName = in.readString();
        DoctorId = in.readString();
        DoctorUserId = in.readString();
        DoctorCode = in.readString();
        DoctorName = in.readString();
        HospitalId = in.readString();
        HospitalName = in.readString();
        DoctorAvatar = in.readString();
        DepartmentCode = in.readString();
        DepartmentName = in.readString();
        Status = in.readInt();
        Time = in.readString();
        ClinicId = in.readString();
        CanEnter = in.readByte() != 0;
    }

    public static final Creator<QueueBean> CREATOR = new Creator<QueueBean>() {
        @Override
        public QueueBean createFromParcel(Parcel in) {
            return new QueueBean(in);
        }

        @Override
        public QueueBean[] newArray(int size) {
            return new QueueBean[size];
        }
    };

    public String getQueueSn() {
        return QueueSn;
    }

    public void setQueueSn(String QueueSn) {
        this.QueueSn = QueueSn;
    }

    public int getQueueNo() {
        return QueueNo;
    }

    public void setQueueNo(int QueueNo) {
        this.QueueNo = QueueNo;
    }

    public int getWaitingCount() {
        return WaitingCount;
    }

    public void setWaitingCount(int WaitingCount) {
        this.WaitingCount = WaitingCount;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String DoctorId) {
        this.DoctorId = DoctorId;
    }

    public String getDoctorUserId() {
        return DoctorUserId;
    }

    public void setDoctorUserId(String DoctorUserId) {
        this.DoctorUserId = DoctorUserId;
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

    public String getDoctorAvatar() {
        return DoctorAvatar;
    }

    public void setDoctorAvatar(String DoctorAvatar) {
        this.DoctorAvatar = DoctorAvatar;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String DepartmentCode) {
        this.DepartmentCode = DepartmentCode;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getClinicId() {
        return ClinicId;
    }

    public void setClinicId(String ClinicId) {
        this.ClinicId = ClinicId;
    }

    public boolean isCanEnter() {
        return CanEnter;
    }

    public void setCanEnter(boolean CanEnter) {
        this.CanEnter = CanEnter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(QueueSn);
        dest.writeInt(QueueNo);
        dest.writeInt(WaitingCount);
        dest.writeString(PatientId);
        dest.writeString(PatientName);
        dest.writeString(DoctorId);
        dest.writeString(DoctorUserId);
        dest.writeString(DoctorCode);
        dest.writeString(DoctorName);
        dest.writeString(HospitalId);
        dest.writeString(HospitalName);
        dest.writeString(DoctorAvatar);
        dest.writeString(DepartmentCode);
        dest.writeString(DepartmentName);
        dest.writeInt(Status);
        dest.writeString(Time);
        dest.writeString(ClinicId);
        dest.writeByte((byte) (CanEnter ? 1 : 0));
    }
}
