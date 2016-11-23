package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-31.
 * 提交预约信息给hos的请求实体
 */

public class AddClinicBookingBean {
    /**
     * UserId : sample string 1
     * HospitalId : sample string 2
     * PatientId : sample string 3
     * PatientName : sample string 4
     * Description : sample string 5
     * RegId : sample string 6
     * DepartmentCode : sample string 7
     * DepartmentName : sample string 8
     * DoctorCode : sample string 9
     * DoctorName : sample string 10
     * ScheduleId : sample string 11
     * QueneSn : sample string 12
     * AdviceTime : 2016-10-31 17:37:41
     * BeginTime : sample string 14
     * EndTime : sample string 15
     * RoomNo : sample string 16
     * Mobile : sample string 17
     * Status : 18
     * BookDate : 2016-10-31 17:37:41
     * VisitId : sample string 20
     * VisitCount : 21
     * VisitPayFlag : 22
     */

    private String UserId; //用户ID
    private String HospitalId; //医院ID
    private String PatientId;  //患者ID
    private String PatientName;//患者姓名
    private String Description;//描述
    private String RegId;       //患者在预约中对应的ID
    private String DepartmentCode;//科室编号
    private String DepartmentName; // 科室名称
    private String DoctorCode;  //医生编号
    private String DoctorName;  //医生名称
    private String ScheduleId;  //医生排班号
    private String QueneSn;     //排队号
    private String AdviceTime;  //就诊时间
    private String BeginTime;   //就诊开始时间
    private String EndTime;     //就诊结束时间
    private String RoomNo;      //就诊诊室
    private String Mobile;      //患者电话
    private int Status;         //预约状态
    private String BookDate;    //预约时间
    private String VisitId;     //就诊ID
    private int VisitCount;     //就诊次数
    private int VisitPayFlag;   //就诊付费标志

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

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getRegId() {
        return RegId;
    }

    public void setRegId(String RegId) {
        this.RegId = RegId;
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

    public String getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(String ScheduleId) {
        this.ScheduleId = ScheduleId;
    }

    public String getQueneSn() {
        return QueneSn;
    }

    public void setQueneSn(String QueneSn) {
        this.QueneSn = QueneSn;
    }

    public String getAdviceTime() {
        return AdviceTime;
    }

    public void setAdviceTime(String AdviceTime) {
        this.AdviceTime = AdviceTime;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String BeginTime) {
        this.BeginTime = BeginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {
        this.RoomNo = RoomNo;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getBookDate() {
        return BookDate;
    }

    public void setBookDate(String BookDate) {
        this.BookDate = BookDate;
    }

    public String getVisitId() {
        return VisitId;
    }

    public void setVisitId(String VisitId) {
        this.VisitId = VisitId;
    }

    public int getVisitCount() {
        return VisitCount;
    }

    public void setVisitCount(int VisitCount) {
        this.VisitCount = VisitCount;
    }

    public int getVisitPayFlag() {
        return VisitPayFlag;
    }

    public void setVisitPayFlag(int VisitPayFlag) {
        this.VisitPayFlag = VisitPayFlag;
    }

    @Override
    public String toString() {
        return "AddClinicBookingBean{" +
                "UserId='" + UserId + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", Description='" + Description + '\'' +
                ", RegId='" + RegId + '\'' +
                ", DepartmentCode='" + DepartmentCode + '\'' +
                ", DepartmentName='" + DepartmentName + '\'' +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", ScheduleId='" + ScheduleId + '\'' +
                ", QueneSn='" + QueneSn + '\'' +
                ", AdviceTime='" + AdviceTime + '\'' +
                ", BeginTime='" + BeginTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", RoomNo='" + RoomNo + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Status=" + Status +
                ", BookDate='" + BookDate + '\'' +
                ", VisitId='" + VisitId + '\'' +
                ", VisitCount=" + VisitCount +
                ", VisitPayFlag=" + VisitPayFlag +
                '}';
    }
}
