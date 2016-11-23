package ylzpatient.hq.zz.com.yilianzhongpatient.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;

/**
 * Created by Administrator on 2016-8-26.
 */
public class Uitls {
    public static SimpleDateFormat format = new SimpleDateFormat(GeneralConfig.DATE_FAMART);
    /**
     * 相片按相框的比例动态缩放
     *
     * @param context
     * @param drawableId 要缩放的图片id
     * @param width      模板宽度
     * @param height     模板高度
     * @return
     */
    public static Bitmap upImageSize(Context context, int drawableId, int width, int height) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), drawableId);
        if (bmp == null) {
            return null;
        }
        // 计算比例
        float scaleX = (float) width / bmp.getWidth();// 宽的比例
        float scaleY = (float) height / bmp.getHeight();// 高的比例
        //新的宽高
        int newW = 0;
        int newH = 0;
        if (scaleX > scaleY) {
            newW = (int) (bmp.getWidth() * scaleX);
            newH = (int) (bmp.getHeight() * scaleX);
        } else if (scaleX <= scaleY) {
            newW = (int) (bmp.getWidth() * scaleY);
            newH = (int) (bmp.getHeight() * scaleY);
        }
        return Bitmap.createScaledBitmap(bmp, newW, newH, true);
    }
    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
    /**
     * 以今天为时间原点,获取未来 Days 天的方法(获取本机的时间)
     *
     * @param days 间隔时间,=0获得今天的时间;=1,获取明天的时间
     * @return 时间的String值, 只有年月日
     */
    public static String getTime(int days) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat(GeneralConfig.DATE_FAMART);
        Date date = null;
        try {
            date = new Date();//初始日期
        } catch (Exception e) {

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.DAY_OF_MONTH, days);        //在日历的现在的时间上,加上days天后的日期
        String strDate = sdf.format(c.getTime());   //的到你想要得days天后的日期
        return strDate;
    }
    /**
     * 以今天为时间原点,获取未来 Days 天的方法(获取本机的时间)
     *
     * @param days 间隔时间,=0获得今天的时间;=1,获取明天的时间
     * @return 时间的String值, 只有年月日
     */
    public static String getTimeAndhms(int days,String dateFormat) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        dateFormat = TextUtils.isEmpty(dateFormat)?"yyyy-MM-dd hh-mm-ss":dateFormat;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = new Date();//初始日期
        } catch (Exception e) {

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.DAY_OF_MONTH, days);          //在日历的现在的时间上,加上days天后的日期
        String strDate = sdf.format(c.getTime());   //的到你想要得days天后的日期
        return strDate;
    }

    /**
     * 以今天为时间原点,获取days天以后是星期几
     * days = 0 ,获取今天是星期几
     * 获取星期几*/
    public static String getWeek(int days){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK,days);
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }


    /**
     * 判断对象是否为空,对象里所有属性是否全部为空
     * obj ==null 或者obj里全部对象 == null,返回ture
     * obj里有对象不为null 返回false
     */
    public static boolean isEmptyClazz(Object obj) {
        boolean result = true;
        try {
            if (obj != null) {
                //获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段
                for (Field f : obj.getClass().getDeclaredFields()) {

                    //obj类中的成员变量为private,故必须进行此操作
                    f.setAccessible(true);


                    //f.get(obj)获取当前对象中当前Field的value
                    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    if (f.get(obj) != null) {
                        result = false;
                        break;
                    }
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据毫秒值,获取时间的字符串
     *
     * @param mills 时间的毫秒值 如果为空,就获取当前的时间的毫秒值
     * @return
     */
    public static String getDateString(long mills) {
        Date date = new Date(mills);
        String dateString = format.format(date);
        return dateString;
    }

    /**
     * 根据日期字符串转换成毫秒值
     * @param dateString
     * @return
     */
    public static long getDate(String dateString) {
        long ms = 0;
        try {
            Date date = null;
            date = format.parse(dateString);
            ms = date.getTime();
            return ms;
        } catch (ParseException e) {
            e.printStackTrace();
            return ms;
        }
    }

    //将文字转化成图片
    public static Bitmap textAsBitmap(String text, float textSize) {

        TextPaint textPaint = new TextPaint();

        // textPaint.setARGB(0x31, 0x31, 0x31, 0);
        textPaint.setColor(Color.WHITE); //文字颜色

        textPaint.setTextSize(textSize);  //文字大小 px

        StaticLayout layout = new StaticLayout(text, textPaint, 450,
                Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + 20,
                layout.getHeight() + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(10, 10);
        canvas.drawColor(Color.TRANSPARENT); //背景色

        layout.draw(canvas);
        Log.d("textAsBitmap",
                String.format("1:%d %d", layout.getWidth(), layout.getHeight()));
        return bitmap;
    }

    /**
     * 获取版本号  versionCode
     * //获取版本号(内部识别号)
     * @return
     */
    public static int getVersionCode(){
        try {
            PackageInfo pi=UtilApplication.getContextObject().getPackageManager().getPackageInfo(UtilApplication.getContextObject().getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取版本名称 versionName
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        // 获取packagemanager的实例
        PackageManager packageManager = UtilApplication.getContextObject().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(UtilApplication.getContextObject().getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    /**
     * 输出日志
     * @param msg
     */
    public static void outLogInfo(String msg) {
        AppLog.logWarn(msg);
    }

    /**
     * 输出日志
     * @param obj
     */
    public static void outBeanLogInfo(Object obj) {
        try {
            outLogInfo(obj==null?"null":new Gson().toJson(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出日志
     * @param msg
     */
    public static void logInfo(String msg) {
        Log.i(GeneralConfig.LOG_TAG, msg);
    }

    /**
     * 输出日志
     * @param obj
     */
    public static void logInfoBean(Object obj) {
        try {
            Log.i(GeneralConfig.LOG_TAG, obj==null?"null":new Gson().toJson(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
