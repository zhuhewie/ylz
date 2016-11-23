package ylzpatient.hq.zz.com.yilianzhongpatient.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016-9-19.
 * 用SharedPreferences存储和读取的封装
 */
public class ShareHelper {
    private Object SPLock = new Object();
    private static final ShareHelper pInstance = new ShareHelper();

    public static ShareHelper newIntance() {

        return pInstance;
    }

    public ShareHelper() {
    }

    public String getStringCache(String key) {
        String result = null;
        Object var3 = this.SPLock;
        synchronized(this.SPLock) {
            SharedPreferences ferences = this.getPreferences();
            result = ferences.getString(key, "");
            return result;
        }
    }

    public void setStringCache(String key, String value) {
        Object var3 = this.SPLock;
        synchronized(this.SPLock) {
            SharedPreferences preferences = this.getPreferences();
            preferences.edit().putString(key, value).commit();
        }
    }

    public int getIntCache(String key) {
        boolean result = false;
        Object var3 = this.SPLock;
        synchronized(this.SPLock) {
            SharedPreferences ferences = this.getPreferences();
            int result1 = ferences.getInt(key, 0);
            return result1;
        }
    }

    public void setIntCache(String key, int value) {
        synchronized(key) {
            SharedPreferences preferences = this.getPreferences();
            preferences.edit().putInt(key, value).commit();
        }
    }

    public void clear(String key) {
        synchronized(key) {
            SharedPreferences ferences = this.getPreferences();
            ferences.edit().remove(key).commit();
        }
    }

    public void clearAll() {
        Object var1 = this.SPLock;
        synchronized(this.SPLock) {
            SharedPreferences preferences = this.getPreferences();
            preferences.edit().clear().commit();
        }
    }

    private SharedPreferences getPreferences() {
        SharedPreferences preferences = UtilApplication.getContextObject().getSharedPreferences("yilianzhonghuanzhe", Context.MODE_PRIVATE);
        return preferences;
    }
}
