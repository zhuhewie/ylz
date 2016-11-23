package ylzpatient.hq.zz.com.yilianzhongpatient.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;

/**
 * Created by Administrator on 2016-8-30.
 * 项目中fragment 的 基类
 */

public class SuperFragment extends Fragment {
    protected Activity mActivity;

//    private ;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    //防止getActivity空指针
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }


    }


    /**
     * 当Fragment的栈里面有几个fragment的时候，这个时候如果是几个fragment状态是hide，
     * 当你触摸当前fragment的时候，下层的fragment的事件被触发，这是由于Touch事件泄露传到了下层中。
     * 解决方法就是拦截fragment的
     *  在这个方法中,拦截触摸事件,防止点击事件泄露
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // 拦截触摸事件，防止泄露下去
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) { // onTouch事件 将上层的触摸事件拦截
                return true;
            }
        });
    }

    /**
     * 加载默认的fragment
     * @param id
     * @param fragment
     */
    public void addDeulftFragment(int id,Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(id, fragment)
                .commit();
    }

    //防止fragment重复加载多次的fragment启动方式
    public void switchContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            String name = to.getClass().getName();
            transaction.hide(from).add(R.id.fragment_center, to,name).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

    public void clearFarhterContent(){
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    //切换充满屏幕的fragment
    public void switchFatherContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();;
        if (to != null && !to.isAdded()) {    // 先判断是否被add过
            String name = to.getClass().getName();
            transaction
                    .hide(from)
                    .add(R.id.base_frame_layout, to,to.getClass().getName())
                    .addToBackStack(null)
                    .commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction
                    .hide(from)
                    .show(to)
                    .addToBackStack(null)
                    .commit(); // 隐藏当前的fragment，显示下一个
        }
//        }
    }

    /**
     * 切换充满屏幕的fragment 不添加到回退栈
     * @param from
     * @param to
     */
    public void switchFatherView(Fragment from, Fragment to) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();;
        if (to != null && !to.isAdded()) {    // 先判断是否被add过
            String name = to.getClass().getName();
            transaction
                    .hide(from)
                    .add(R.id.base_frame_layout, to,to.getClass().getName())
                    .commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction
                    .hide(from)
                    .show(to)
                    .commit(); // 隐藏当前的fragment，显示下一个
        }
//        }
    }




    public void switchFatherContent(Fragment to) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();;

        if (!to.isAdded()) {
            transaction
                    .replace(R.id.base_frame_layout, to)
                    .addToBackStack(null)
                    .commit();
        } else {
            transaction
                    .show(to)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * 显示dialog的方法
     */
    public void showDialog(SuperDialogFragment dialog) {
        FragmentManager fm = getFragmentManager();
        fm = getFragmentManager();
        dialog.show(fm, dialog.getClass().getName());
    }

    /**
     * 设置下拉刷新的颜色
     * @param srl
     */
    public void setSrlColor(SwipeRefreshLayout srl) {
        //刷新的颜色
        srl.setColorSchemeColors(getResources().getColor(R.color.bule), getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.read), getResources().getColor(R.color.green));
    }
}
