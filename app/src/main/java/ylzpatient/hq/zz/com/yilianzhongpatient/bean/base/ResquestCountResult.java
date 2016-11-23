package ylzpatient.hq.zz.com.yilianzhongpatient.bean.base;


/**
 * Created by Administrator on 2016-10-20.
 * 分页请求的返回解析
 */

public class ResquestCountResult<T> {
    public int ResultCount;
    public String ResultCode;
    public String ResultMsg;
    public T ResultData;
}
