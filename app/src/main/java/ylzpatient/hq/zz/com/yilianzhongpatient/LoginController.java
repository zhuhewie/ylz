package ylzpatient.hq.zz.com.yilianzhongpatient;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016-8-22.
 */
public class LoginController {
    private Context context;
    private Intent intent = new Intent();

    public LoginController(Context context) {
        this.context = context;
    }

    public void login(){

        intent.setClass(context,MainActivity2.class);
        context.startActivity(intent);
    }
}
