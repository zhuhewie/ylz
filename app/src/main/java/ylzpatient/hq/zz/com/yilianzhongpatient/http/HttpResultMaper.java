package ylzpatient.hq.zz.com.yilianzhongpatient.http;

import android.widget.Toast;

import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-9-18.
 * 联网请求结果的预处理
 */
public class HttpResultMaper<T> implements Func1<ResquestResult<T>,T> {

    @Override
    public T call(ResquestResult<T> result) {
        //如果ResultCode 不等于null ,那么就是请求数据失败,失败原因ResultMsg
        if (result.ResultCode != null) {
//            throw new Exception();
            try {
                Toast.makeText(UtilApplication.getContextObject(),"请求失败",Toast.LENGTH_SHORT).show();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {//ResultCode等于null,请求成功,数据在ResultData
            return result.ResultData;
        }
    }
}
