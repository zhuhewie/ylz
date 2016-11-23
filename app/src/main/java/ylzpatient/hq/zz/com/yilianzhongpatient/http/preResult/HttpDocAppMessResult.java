package ylzpatient.hq.zz.com.yilianzhongpatient.http.preResult;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.DateWeek;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.D;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorAppMessageBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-21.
 * 将从网络断获取的医生排班信息,解析成list
 */
public class HttpDocAppMessResult implements Func1<D<String>, List<Object>> {


    @Override
    public List<Object> call(D<String> ds) {
        //Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Gson gson = new Gson();
        List<Object> result = new ArrayList<>();
        String s = ds.toString();
        //1:将从服务器端获取的json数据转换成list<bean>
        List<DoctorAppMessageBean> list = gson.fromJson(ds.d, new TypeToken<List<DoctorAppMessageBean>>() {
        }.getType());
        //2:一个有序的map
        Map<String, DoctorAppMessageBean> map = new LinkedHashTreeMap<>();
        //3:把list<bean>里全部元素的ScheduleDate的值,截取,只要年月日(如:2016-09-28)
        for (DoctorAppMessageBean d : list) {
            d.setScheduleDate(d.getScheduleDate().substring(0, 10));
            //4:将list<bean>转换成linkedHashTreeMap(有序的map),
            // 让ScheduleDate(2016-09-28) +  ShiftName(上午)做键,bean做值
            map.put(d.getScheduleDate() + d.getShiftName(), d);
        }
        //5:创建一个新的list ,长度为90
        for (int i = 0; i < 90; i++) {
            int y = i / 3;  //除以3的商,y得值,刚好是天数,以今天为单位,未来的y天.如y=0:代表今天,y=1:代表明天
            String date = Uitls.getTime(y);//天数的字符串类型,如"2019-08-29"
            //如果除以三余0,添加日期和星期,用于表格显示的表头
            if (i % 3 == 0) {
                result.add(new DateWeek(date, Uitls.getWeek(i / 3)));
            } else if (i % 3 == 1) {//如果除3余一,代表是(i/3)天的上午
                //从有序的map中,根据键 (date+"上午")获取第y天上午的值,
                DoctorAppMessageBean appDate = map.get(date + "上午");
                //如果获取到的数据不为空,y天上午有预约号
                if (!Uitls.isEmptyClazz(appDate)) {
                    //添加到新的list中
                    result.add(appDate);

                } else {//如果为空,代表y天的上午不能预约
                    result.add(null);//添加空,用于站位
                }
            } else if (i % 3 == 2) { //如果除3余2,代表是y天的下午
                //从有序的map中,根据键 (date+"下午")获取第y天下午的值,
                DoctorAppMessageBean appDate = map.get(date + "下午");
                //如果获取到的数据不为空,y天下午有预约号
                if (!Uitls.isEmptyClazz(appDate)) {
                    //添加到新的list中
                    result.add(appDate);
                } else {//如果为空,代表y天的下午不能预约
                    result.add(null);//添加空,用于站位
                }
            } else {
                result.add(null);
            }
        }
        return result; //返回这个新的长度为90的list
    }
}
