package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-9.
 * 进入诊室的请求体
 */

public class EnterClinicRequestBean {

    /**
     * QueueSn : sample string 1
     * UserId : sample string 2
     * PatientId : sample string 3
     * PatientName : sample string 4
     * HospitalId : sample string 5
     * DepartmentCode : sample string 6
     * DepartmentName : sample string 7
     * DoctorCode : sample string 8
     * DoctorName : sample string 9
     */

    private String QueueSn;     //排队主键
    private String UserId;      //用户ID
    private String PatientId;   //患者ID
    private String PatientName; //患者姓名
    private String HospitalId;  //医院ID
    private String DepartmentCode;//科室编号
    private String DepartmentName;//科室名称
    private String DoctorCode;  //医生编号
    private String DoctorName;  //医生姓名

    public String getQueueSn() {
        return QueueSn;
    }

    public void setQueueSn(String QueueSn) {
        this.QueueSn = QueueSn;
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

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
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

    @Override
    public String toString() {
        return "EnterClinicRequestBean{" +
                "QueueSn='" + QueueSn + '\'' +
                ", UserId='" + UserId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", DepartmentCode='" + DepartmentCode + '\'' +
                ", DepartmentName='" + DepartmentName + '\'' +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                '}';
    }
}
