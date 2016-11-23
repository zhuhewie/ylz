package ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.D;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;

/**
 * Created by Administrator on 2016-9-18.
 * 处理返回值是
 * {
 * "d": "[]"
 * }
 的格式的数据解析  返回data
 */
public class HttpDeptResult implements Func1<D<String>,List<DepartmentBean>> {

    private static HttpDeptResult INSTANCE = new HttpDeptResult();


    public static HttpDeptResult getInstance() {
        return INSTANCE;
    }


    @Override
    public List<DepartmentBean> call(D<String> stringD) {
        Gson gson = new Gson();
        List<DepartmentBean> list = gson.fromJson(stringD.d, new TypeToken<List<DepartmentBean>>(){}.getType());
        return list;
    }
}
