package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;

/**
 * Created by Administrator on 2016-9-22.
 * 预约门诊界面,就诊卡的点击事件
 */
public class PatientCardDialogEvent {
    public PatientCardBean patientCard;

    public PatientCardDialogEvent(PatientCardBean patientCard) {
        this.patientCard = patientCard;
    }
}
