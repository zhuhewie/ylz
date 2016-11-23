package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;

/**
 * Created by Administrator on 2016-9-9.
 * 预约科室,右侧科室列表的点击事件
 */
public class DepartmentRightEvent {


    public DepartmentBean departmentName;

    public DepartmentRightEvent(DepartmentBean departmentName) {
        this.departmentName = departmentName;
    }
}
