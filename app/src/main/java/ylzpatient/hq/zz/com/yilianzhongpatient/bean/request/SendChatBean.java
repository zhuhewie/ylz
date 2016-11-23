package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-19.
 * 聊天时发送消息的实体
 */

public class SendChatBean {

    /**
     * fromId : 1
     * clinicId : 1477878918898
     * patientId : 000042536600
     * msg : askdjflkjweoijf
     * time : 1477453940
     */

    private String fromId;
    private String toId;
    private String clinicId;
    private String patientId;
    private String msg;
    private int time;

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SendChatBean{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}
