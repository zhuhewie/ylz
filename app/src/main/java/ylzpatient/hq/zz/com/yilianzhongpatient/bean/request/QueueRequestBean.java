package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-11-9.
 *
 */

public class QueueRequestBean implements Parcelable{


    /**
     * HospitalId : sample string 1
     * UserId : sample string 2
     * PatientId : sample string 3
     * PatientName : sample string 4
     * DoctorId : sample string 5
     * DepartmentCode : sample string 6
     * Department : sample string 7
     * DoctorCode : sample string 8
     * DoctorName : sample string 9
     */

    private String HospitalId;      //医院ID
    private String UserId;          //用户ID
    private String PatientId;       //患者ID
    private String PatientName;     //患者姓名
    private String DoctorId;        //医生ID
    private String DepartmentCode;  //科室编号
    private String Department;      //科室名称
    private String DoctorCode;      //医生编号
    private String DoctorName;      //医生姓名

    public QueueRequestBean() {
    }

    protected QueueRequestBean(Parcel in) {
        HospitalId = in.readString();
        UserId = in.readString();
        PatientId = in.readString();
        PatientName = in.readString();
        DoctorId = in.readString();
        DepartmentCode = in.readString();
        Department = in.readString();
        DoctorCode = in.readString();
        DoctorName = in.readString();
    }

    public static final Creator<QueueRequestBean> CREATOR = new Creator<QueueRequestBean>() {
        @Override
        public QueueRequestBean createFromParcel(Parcel in) {
            return new QueueRequestBean(in);
        }

        @Override
        public QueueRequestBean[] newArray(int size) {
            return new QueueRequestBean[size];
        }
    };

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(HospitalId);
        dest.writeString(UserId);
        dest.writeString(PatientId);
        dest.writeString(PatientName);
        dest.writeString(DoctorId);
        dest.writeString(DepartmentCode);
        dest.writeString(Department);
        dest.writeString(DoctorCode);
        dest.writeString(DoctorName);
    }
}
