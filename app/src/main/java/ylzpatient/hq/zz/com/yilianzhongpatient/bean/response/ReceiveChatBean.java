package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-10.
 * 接收到的聊天消息的实体
 */

public class ReceiveChatBean {

    /**
     * fromId : 2
     * patientId : 11111
     * clinicId : 12345
     * msg : 内容
     * time : 1589311111
     */

    private String fromId;
    private String patientId;
    private String clinicId;
    private String msg;
    private int time;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
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
        return "ReceiveChatBean{" +
                "fromId='" + fromId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}
