package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RatingBarChangeEvent;
import com.jakewharton.rxbinding.widget.RxRatingBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;

/**
 * Created by Administrator on 2016-10-14.
 */

public class RatingDialog extends SuperDialogFragment {
    @BindView(R.id.rb_point)
    RatingBar rbPoint;
    @BindView(R.id.et_qustion)
    EditText etQustion;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.tv_add_qustion)
    TextView tvAddQustion;
    @BindView(R.id.et_add_qustion)
    EditText etAddQustion;

    public static RatingDialog newInstance() {
        RatingDialog dialog = new RatingDialog();
        return dialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
//        if (bundle != null) {
//            mSelectPath = bundle.getStringArrayList(ARG_PATH_LIST);
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_rating, container);
        ButterKnife.bind(this, view);

        addClick();
        addData();

        return view;
    }

    private void addData() {

    }

    private void addClick() {
        //评分监听
//        rbPoint.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                //评分监听
//                //v:现在选中的等级
//            }
//        });
        //提交
        RxView.clicks(btnCommit)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Snackbar.make(btnCommit,"提交",3000).show();
                        dismiss();
                    }
                });
        //占不评价
        RxView.clicks(btnCancel)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Snackbar.make(btnCommit,"取消",3000).show();
                        dismiss();
                    }
                });
        //监听评分选着
        RxRatingBar.ratingChangeEvents(rbPoint)
                .subscribe(new Action1<RatingBarChangeEvent>() {
                    @Override
                    public void call(RatingBarChangeEvent ratingBarChangeEvent) {
                        float f = ratingBarChangeEvent.rating();
                        Log.i(GeneralConfig.LOG_TAG,f+"");
                    }
                });
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // 设置宽度为屏幕宽度的80%,高度包裹内容
        window.setLayout((int) (size.x * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);

        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }


}
