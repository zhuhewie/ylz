package ylzpatient.hq.zz.com.yilianzhongpatient;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.pgyersdk.update.PgyUpdateManager;

import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperActivity;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.MainFragment;

public class MainActivity2 extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (savedInstanceState == null) {
            //loadRootFragment(R.id.base_frame_layout, MainFragment.newInstance());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.base_frame_layout, MainFragment.newInstance())
                    //.addToBackStack(null)
                    .commit();
        }

        if (!GeneralConfig.TEST) {
            checkUpdate();
        }
    }

    private void checkUpdate() {
        PgyUpdateManager.register(this);//默认对话框，应用管理中定义

        //自定义对话框
        //PgyUpdateManager.register(MainActivity.this, new UpdateManagerListener() {
        //            @Override
        //            public void onUpdateAvailable(final String result) {
        //
        //                // 将新版本信息封装到AppBean中
        //                final AppBean appBean = getAppBeanFromString(result);
        //                new AlertDialog.Builder(MainActivity.this)
        //                        .setTitle("更新")
        //                        .setMessage("")
        //                        .setNegativeButton("确定",
        //                                new DialogInterface.OnClickListener() {
        //                                    @Override
        //                                    public void onClick(
        //                                            DialogInterface dialog,
        //                                            int which) {
        //                                        startDownloadTask(
        //                                                MainActivity.this,
        //                                                appBean.getDownloadURL());
        //                                    }
        //                                }).show();
        //            }
        //
        //            @Override
        //            public void onNoUpdateAvailable() {
        //                Toast.makeText(MainActivity.this, "fuck", Toast.LENGTH_LONG).show();
        //            }
        //        });
    }



    @Override
    public void onBackPressed() {
//        //如果是在医生诊室界面点击返回按钮
//        if (getSupportFragmentManager().findFragmentByTag(ClinicFragment.class.getName()) instanceof ClinicFragment) {
//            //EventBus.getDefault().post(new BackEvent(1));
//        } else {
//            super.onBackPressed();
//        }
            super.onBackPressed();
    }

    //点击屏幕非edittext的地方,隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
