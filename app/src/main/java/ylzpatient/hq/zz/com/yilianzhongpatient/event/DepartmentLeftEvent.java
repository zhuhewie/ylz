package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;

/**
 * Created by Administrator on 2016-9-9.
 */
public class DepartmentLeftEvent {


    public DepartmentBean checkDepartment;

    public DepartmentLeftEvent(DepartmentBean checkDepartment) {
        this.checkDepartment = checkDepartment;
    }
}
