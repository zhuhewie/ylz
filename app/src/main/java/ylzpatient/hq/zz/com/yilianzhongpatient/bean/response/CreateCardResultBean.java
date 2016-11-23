package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-9-28.
 * 提交新建就诊卡的返回bean
 */
public class CreateCardResultBean {

    /**
     * RegResult : true
     * ErrorMessage :
     * IsRegistered : true
     * PatientID : 000070803400
     * SunCodeNo : null
     */

    private boolean RegResult;
    private String ErrorMessage;
    private boolean IsRegistered;
    private String PatientID;
    private Object SunCodeNo;

    public boolean isRegResult() {
        return RegResult;
    }

    public void setRegResult(boolean RegResult) {
        this.RegResult = RegResult;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public boolean isIsRegistered() {
        return IsRegistered;
    }

    public void setIsRegistered(boolean IsRegistered) {
        this.IsRegistered = IsRegistered;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public Object getSunCodeNo() {
        return SunCodeNo;
    }

    public void setSunCodeNo(Object SunCodeNo) {
        this.SunCodeNo = SunCodeNo;
    }

    @Override
    public String toString() {
        return "CreateCardResultBean{" +
                "RegResult=" + RegResult +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                ", IsRegistered=" + IsRegistered +
                ", PatientID='" + PatientID + '\'' +
                ", SunCodeNo=" + SunCodeNo +
                '}';
    }
}
