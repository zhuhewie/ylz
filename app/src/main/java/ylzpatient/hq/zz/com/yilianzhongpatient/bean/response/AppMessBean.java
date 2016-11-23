package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-10-25.
 * 预约信息的实体,
 */

public class AppMessBean implements Parcelable {

    /**
     * BookingSn : 1477280779647
     * HospitalName : 清远中医院
     * Department : 妇科
     * Doctor : 唐良玉
     * DoctorTitle :
     * PatientName : 蔡文
     * Time : 2016-10-26 09:00:00
     * TimeSpan : 9:00-12:00
     * Status : 8
         0：初始状态;
         1：预约成功;
         2：报到成功;
         3：候诊中;
         4：已候诊;
         5：已缴费;
         6：就诊结束
         8:取消

     */

    private String BookingSn;
    private String HospitalName;
    private String Department;
    private String Doctor;
    private String DoctorTitle;
    private String PatientName;
    private String Time;
    private String TimeSpan;
    private int Status;

    protected AppMessBean(Parcel in) {
        BookingSn = in.readString();
        HospitalName = in.readString();
        Department = in.readString();
        Doctor = in.readString();
        DoctorTitle = in.readString();
        PatientName = in.readString();
        Time = in.readString();
        TimeSpan = in.readString();
        Status = in.readInt();
    }

    public static final Creator<AppMessBean> CREATOR = new Creator<AppMessBean>() {
        @Override
        public AppMessBean createFromParcel(Parcel in) {
            return new AppMessBean(in);
        }

        @Override
        public AppMessBean[] newArray(int size) {
            return new AppMessBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(BookingSn);
        parcel.writeString(HospitalName);
        parcel.writeString(Department);
        parcel.writeString(Doctor);
        parcel.writeString(DoctorTitle);
        parcel.writeString(PatientName);
        parcel.writeString(Time);
        parcel.writeString(TimeSpan);
        parcel.writeInt(Status);
    }


    public String getBookingSn() {
        return BookingSn;
    }

    public void setBookingSn(String BookingSn) {
        this.BookingSn = BookingSn;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String Doctor) {
        this.Doctor = Doctor;
    }

    public String getDoctorTitle() {
        return DoctorTitle;
    }

    public void setDoctorTitle(String DoctorTitle) {
        this.DoctorTitle = DoctorTitle;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String TimeSpan) {
        this.TimeSpan = TimeSpan;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "AppMessBean{" +
                "BookingSn='" + BookingSn + '\'' +
                ", HospitalName='" + HospitalName + '\'' +
                ", Department='" + Department + '\'' +
                ", Doctor='" + Doctor + '\'' +
                ", DoctorTitle='" + DoctorTitle + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", Time='" + Time + '\'' +
                ", TimeSpan='" + TimeSpan + '\'' +
                ", Status=" + Status +
                '}';
    }
}
