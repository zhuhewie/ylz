package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-3.
 * 患者的就诊卡信息.HOS返回的
 */

public class CardBean {

    /**
     * UserId : sample string 1
     * PatientId : sample string 2
     * PatientName : sample string 3
     * PatientMobile : sample string 4
     * HospitalId : sample string 5
     * HospitalName : sample string 6
     * PatientIdCardNo : sample string 7
     * IsActived : true
     */

    private String UserId;//用户ID
    private String PatientId;//患者ID
    private String PatientName;//患者姓名
    private String PatientMobile;//患者电话
    private String HospitalId;//医院ID
    private String HospitalName;//医院名称
    private String PatientIdCardNo;//就诊卡编号
    private boolean IsActived;//是否选中

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

    public String getPatientMobile() {
        return PatientMobile;
    }

    public void setPatientMobile(String PatientMobile) {
        this.PatientMobile = PatientMobile;
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

    public String getPatientIdCardNo() {
        return PatientIdCardNo;
    }

    public void setPatientIdCardNo(String PatientIdCardNo) {
        this.PatientIdCardNo = PatientIdCardNo;
    }

    public boolean isIsActived() {
        return IsActived;
    }

    public void setIsActived(boolean IsActived) {
        this.IsActived = IsActived;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "UserId='" + UserId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", PatientMobile='" + PatientMobile + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", HospitalName='" + HospitalName + '\'' +
                ", PatientIdCardNo='" + PatientIdCardNo + '\'' +
                ", IsActived=" + IsActived +
                '}';
    }
}
