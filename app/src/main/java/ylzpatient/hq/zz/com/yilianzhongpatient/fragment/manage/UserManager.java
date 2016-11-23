package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.ShareHelper;

/**
 * Created by Administrator on 2016-9-19.
 *
 * 存储和读取登录人的信息
 */
public class UserManager {

    private static final UserManager pInstance = new UserManager();

    public static UserManager getInstance() {
        return pInstance;
    }

    public static void resetInstance() {
        getInstance().setSystemUser(null);
    }

    public void setSystemUser(UserTestBean_2 user) {
        try {
            String userStr = null!=user?new Gson().toJson(user):"";
            //base64加密. data:加密后的数据
            String data = Base64.encodeToString(userStr.getBytes(),Base64.DEFAULT);
            //将加密后的数据用sp保存
            ShareHelper.newIntance().setStringCache(GeneralConfig.SP_USER, data);
        } catch (Exception e) {

        }
    }

    public UserTestBean_2 getCurrentUser() {
        String userStr = ShareHelper.newIntance().getStringCache(GeneralConfig.SP_USER);
        String data = new String(Base64.decode(userStr.getBytes(),Base64.DEFAULT));
        UserTestBean_2 user = new Gson().fromJson(data, new TypeToken<UserTestBean_2>() {}.getType());
        return user;
    }


}
