package ylzpatient.hq.zz.com.yilianzhongpatient;

import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;

/**
 * Created by Administrator on 2016-8-26.
 *
 * 项目的通用字符串,等全局变量的类
 */
public class GeneralConfig {

    //
/////////////////////////////联网使用的URL/////////////////////////////////////////
    public static final String HOSURL =
            "http://mp.hope-bridge.com:8066/api/"; //云服务器地址
    public static final String TEST_HOSURL =
             "http://192.168.1.29:8088/api/"; //杨相知测试电脑

    public static final String HISURL =
//                     "http://192.168.1.149:8090/wm.aspx/"; //公司服务器
                     "http://120.196.145.38:7100/wm.aspx/"; //清远中医院
    public static final String TEST_HISURL =
//                     "http://192.168.1.120:8089/wm.aspx/"; //亚玲测试机
    "http://192.168.1.149:8090/wm.aspx/"; //公司服务器

    //聊天的长连接URL
    public static final String CHAT_URL = "ws://192.168.1.29:8065/";
/////////////////////////////////////////////////////////////////////////////////

    //联网超时时间
    public static final int DEFAULT_TIMEOUT = 5;

    //控件的连击超时时间
    public static final int DOUBLE_HIT = 700;

    //用于SharedPreferences,存取数据
    public static final String SP_USER = "yilianzhonguser";

    //传递用户名
    public static final String ID = "id";
    public static final String PHONE_NUMBER = "手机号";

////////////////时间格式////////////////////////////
    public static final String DATE_FAMART = "yyyy-MM-dd";
    public static final String CHOOSE_START_DATE = "开始时间";
    public static final String CHOOSE_END_DATE = "结束时间";

//////////////////启动界面的type////////////////
    public static final int CLINIC_FRAGMENT = 1;

//////////////////////日志标签/////////////////////////////////
    public static final String LOG_TAG = "医联众";


    //滚动图定时滚动的时间,毫秒
    public static final int TIME = 3000;
    ///////////////////////测试标签//////////////////////////
    //测试的时候ture ,正式的为false
    public static boolean TEST = false;

    ////////////////////////////////图片的左右间距距离///////////////////////////////////
    public static final int bottomMenuWidth = 80;
    public static final int bottomMenuhight = 100;
    public static final int bottomMenupaddingLeft = 0;
    public static final int bottomMenupaddingTop = 13;



    public static UserTestBean_2 user;

    ////////////////////////屏幕的宽高//////////////////////////////////////////////////
    public static int screenWidth;
    public static int screenHeight;

    public static final int PageSize = 15; //分页请求时,每页请求个数

    public static String getHOSBaseURL(){
        return !GeneralConfig.TEST?HOSURL:TEST_HOSURL;
    }

    public static String getHISBaseURL(){
        return !GeneralConfig.TEST?HISURL:TEST_HISURL;
    }

    public static UserTestBean_2 getUser() {
        return user;
    }

    public static void setUser(UserTestBean_2 user) {
        GeneralConfig.user = user;
    }
}
