package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;

/**
 * Created by Administrator on 2016-8-24.
 */
public class PayFragment extends SuperFragment {


    @BindView(R.id.text_view_name)
    TextView textViewName;
    private ActionBar actionBar;
    private TextView topText;
    private LinearLayout topRightImageLay;

    public static PayFragment newInstance() {
        Bundle args = new Bundle();

        PayFragment fragment = new PayFragment();
        fragment.setArguments(args);
        //fragment.setTargetFragment(f,300);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pay_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        return v;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        textViewName.setText("缴费");
    }

    /**
     * 界面点击事件
     */
    private void addClick() {
    }


}
