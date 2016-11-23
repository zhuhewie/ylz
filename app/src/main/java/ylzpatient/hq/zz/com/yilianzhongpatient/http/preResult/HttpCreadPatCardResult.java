package ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult;

import com.google.gson.Gson;

import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.D;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CreateCardResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-28.
 */
public class HttpCreadPatCardResult implements Func1<D<String>,CreateCardResultBean> {
    private static HttpCreadPatCardResult INSTANCE = new HttpCreadPatCardResult();


    public static HttpCreadPatCardResult getInstance() {
        return INSTANCE;
    }

    @Override
    public CreateCardResultBean call(D<String> stringD) {
        Gson gson = new Gson();
        CreateCardResultBean patientCard = gson.fromJson(stringD.d,CreateCardResultBean.class);
        if (Uitls.isEmptyClazz(patientCard)) {
            throw new IllegalArgumentException("创建就诊卡失败");
        }
        return patientCard;
    }
}
