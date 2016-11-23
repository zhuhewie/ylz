package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-9-12.
 */
public class DoctorBean implements Parcelable {


    public int Sn; //序号
    public String DoctorCode;//医生编码
    public String DoctorName;//医生姓名
    public Object PyCode;//拼音码
    public Object WbCode;//五笔码
    public Object Advantage; //擅长
    public Object JobTitleCode;//
    public Object JobTitleName;//医生职称名
    public Object Introduction;//简介
    public String DeptCode;//科室编号
    public String DeptName;//科室名称
    public Object SecondDeptCode;//第二科室编号
    public String ClinicResponceName;//出诊身份
    public Object Photo;//医生照片
    public Object ScheduleList; //医生排班信息
    public boolean ScheduleExists;//是否有排班(true:有排班,false:无排班)


    public DoctorBean(int sn, String doctorCode, String doctorName, Object pyCode, Object wbCode, Object advantage, Object jobTitleCode, Object jobTitleName, Object introduction, String deptCode, String deptName, Object secondDeptCode, String clinicResponceName, Object photo, Object scheduleList, boolean scheduleExists) {
        Sn = sn;
        DoctorCode = doctorCode;
        DoctorName = doctorName;
        PyCode = pyCode;
        WbCode = wbCode;
        Advantage = advantage;
        JobTitleCode = jobTitleCode;
        JobTitleName = jobTitleName;
        Introduction = introduction;
        DeptCode = deptCode;
        DeptName = deptName;
        SecondDeptCode = secondDeptCode;
        ClinicResponceName = clinicResponceName;
        Photo = photo;
        ScheduleList = scheduleList;
        ScheduleExists = scheduleExists;
    }

    public DoctorBean() {
    }

    public int getSn() {
        return Sn;
    }

    public void setSn(int sn) {
        Sn = sn;
    }

    public String getDoctorCode() {
        return DoctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        DoctorCode = doctorCode;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public Object getPyCode() {
        return PyCode;
    }

    public void setPyCode(Object pyCode) {
        PyCode = pyCode;
    }

    public Object getWbCode() {
        return WbCode;
    }

    public void setWbCode(Object wbCode) {
        WbCode = wbCode;
    }

    public Object getAdvantage() {
        return Advantage;
    }

    public void setAdvantage(Object advantage) {
        Advantage = advantage;
    }

    public Object getJobTitleCode() {
        return JobTitleCode;
    }

    public void setJobTitleCode(Object jobTitleCode) {
        JobTitleCode = jobTitleCode;
    }

    public Object getJobTitleName() {
        return JobTitleName;
    }

    public void setJobTitleName(Object jobTitleName) {
        JobTitleName = jobTitleName;
    }

    public Object getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(Object introduction) {
        Introduction = introduction;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public Object getSecondDeptCode() {
        return SecondDeptCode;
    }

    public void setSecondDeptCode(Object secondDeptCode) {
        SecondDeptCode = secondDeptCode;
    }

    public String getClinicResponceName() {
        return ClinicResponceName;
    }

    public void setClinicResponceName(String clinicResponceName) {
        ClinicResponceName = clinicResponceName;
    }

    public Object getPhoto() {
        return Photo;
    }

    public void setPhoto(Object photo) {
        Photo = photo;
    }

    public Object getScheduleList() {
        return ScheduleList;
    }

    public void setScheduleList(Object scheduleList) {
        ScheduleList = scheduleList;
    }

    public boolean isScheduleExists() {
        return ScheduleExists;
    }

    public void setScheduleExists(boolean scheduleExists) {
        ScheduleExists = scheduleExists;
    }

    public static Creator<DoctorBean> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Sn);
        parcel.writeString(DoctorCode);
        parcel.writeString(DoctorName);
        parcel.writeValue(PyCode);
        parcel.writeValue(WbCode);
        parcel.writeValue(Advantage);
        parcel.writeValue(JobTitleCode);
        parcel.writeValue(JobTitleName);
        parcel.writeValue(Introduction);
        parcel.writeString(DeptCode);
        parcel.writeString(DeptName);
        parcel.writeValue(SecondDeptCode);
        parcel.writeString(ClinicResponceName);
        parcel.writeValue(Photo);
        parcel.writeValue(ScheduleList);
        parcel.writeInt(ScheduleExists ?1:0);
    }

    private DoctorBean(Parcel in) {
        Sn  = in.readInt();
        DoctorCode = in.readString();
        DoctorName = in.readString();
        PyCode = in.readString();
        WbCode = in.readString();
        Advantage = in.readString();
        JobTitleCode = in.readString();
        JobTitleName = in.readString();
        Introduction = in.readString();
        DeptCode = in.readString();
        DeptName = in.readString();
        SecondDeptCode = in.readString();
        ClinicResponceName = in.readString();
        Photo = in.readString();
        ScheduleList = in.readString();
        ScheduleExists = in.readInt()!=0;
    }
    public static final Creator<DoctorBean> CREATOR = new Creator<DoctorBean>() {
        @Override
        public DoctorBean createFromParcel(Parcel in) {
            return new DoctorBean(in);
        }

        @Override
        public DoctorBean[] newArray(int size) {
            return new DoctorBean[size];
        }
    };

    @Override
    public String toString() {
        return "DoctorBean{" +
                "Sn=" + Sn +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", PyCode=" + PyCode +
                ", WbCode=" + WbCode +
                ", Advantage=" + Advantage +
                ", JobTitleCode=" + JobTitleCode +
                ", JobTitleName=" + JobTitleName +
                ", Introduction=" + Introduction +
                ", DeptCode='" + DeptCode + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", SecondDeptCode=" + SecondDeptCode +
                ", ClinicResponceName='" + ClinicResponceName + '\'' +
                ", Photo=" + Photo +
                ", ScheduleList=" + ScheduleList +
                ", ScheduleExists=" + ScheduleExists +
                '}';
    }
}
