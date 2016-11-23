package ylzpatient.hq.zz.com.yilianzhongpatient.bean.base;

/**
 * Created by Administrator on 2016-9-14.
 * 用于封装亚玲姐接口的格式实体
 * 发送json示例
 * {
 *   "jsonParam":{
 *        "BeginTime" : "2016-09-15",
 *      "EndTime" :"2016-10-15"
 *     }
 *  }
 */
public class JsonParam<T> {
    /**
     * BeginTime : 2016-09-15
     * EndTime : 2016-10-15
     */

    public T jsonParam;

    public JsonParam() {
    }

    public JsonParam(T jsonParam) {
        this.jsonParam = jsonParam;
    }

    public T getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(T jsonParam) {
        this.jsonParam = jsonParam;
    }
    //public T jsonParam;

}
