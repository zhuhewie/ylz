package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-1.
 * 预约详情返回实体
 */

public class AppDetialBean {

    /**
     * BookingSn : 1477280779647
     * ScheduleId : 5408
     * QueueSn : 1
     * Hospital : 清远中医院
     * Department : 妇科
     * Time : 2016-10-26
     * WeekDay : 3
     * TimeSpan : 9:00-12:00
     * Doctor : 唐良玉
     * DoctorTitle :
     * Clinic :
     * Money : 0
     * Patient : 蔡文
     * PatientCardNo : 000024807200
     * Description : Asfsdafsafssfasafas
     * Advice :
     */

    private String BookingSn;
    private String ScheduleId;
    private String QueueSn;
    private String Hospital;
    private String Department;
    private String Time;
    private String WeekDay;
    private String TimeSpan;
    private String Doctor;
    private String DoctorTitle;
    private String Clinic;
    private String Money;
    private String Patient;
    private String PatientCardNo;
    private String Description;
    private String Advice;

    public String getBookingSn() {
        return BookingSn;
    }

    public void setBookingSn(String BookingSn) {
        this.BookingSn = BookingSn;
    }

    public String getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(String ScheduleId) {
        this.ScheduleId = ScheduleId;
    }

    public String getQueueSn() {
        return QueueSn;
    }

    public void setQueueSn(String QueueSn) {
        this.QueueSn = QueueSn;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String Hospital) {
        this.Hospital = Hospital;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getWeekDay() {
        return WeekDay;
    }

    public void setWeekDay(String WeekDay) {
        this.WeekDay = WeekDay;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String TimeSpan) {
        this.TimeSpan = TimeSpan;
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

    public String getClinic() {
        return Clinic;
    }

    public void setClinic(String Clinic) {
        this.Clinic = Clinic;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public String getPatient() {
        return Patient;
    }

    public void setPatient(String Patient) {
        this.Patient = Patient;
    }

    public String getPatientCardNo() {
        return PatientCardNo;
    }

    public void setPatientCardNo(String PatientCardNo) {
        this.PatientCardNo = PatientCardNo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAdvice() {
        return Advice;
    }

    public void setAdvice(String Advice) {
        this.Advice = Advice;
    }

    @Override
    public String toString() {
        return "AppDetialBean{" +
                "BookingSn='" + BookingSn + '\'' +
                ", ScheduleId='" + ScheduleId + '\'' +
                ", QueueSn='" + QueueSn + '\'' +
                ", Hospital='" + Hospital + '\'' +
                ", Department='" + Department + '\'' +
                ", Time='" + Time + '\'' +
                ", WeekDay='" + WeekDay + '\'' +
                ", TimeSpan='" + TimeSpan + '\'' +
                ", Doctor='" + Doctor + '\'' +
                ", DoctorTitle='" + DoctorTitle + '\'' +
                ", Clinic='" + Clinic + '\'' +
                ", Money='" + Money + '\'' +
                ", Patient='" + Patient + '\'' +
                ", PatientCardNo='" + PatientCardNo + '\'' +
                ", Description='" + Description + '\'' +
                ", Advice='" + Advice + '\'' +
                '}';
    }
}
