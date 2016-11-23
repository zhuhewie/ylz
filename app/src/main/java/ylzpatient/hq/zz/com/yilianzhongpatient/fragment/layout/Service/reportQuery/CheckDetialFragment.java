package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.reportQuery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CheckBean;

/**
 * Created by Administrator on 2016-11-7.
 * 检查详情界面
 */

public class CheckDetialFragment extends SuperFragment {

    private static final String ARG_CHECK_ITEM = "arg_check_item";
    @BindView(R.id.check_part)
    TextView checkPart;
    @BindView(R.id.check_image_seen)
    TextView checkImageSeen;
    @BindView(R.id.check_result)
    TextView checkResult;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.btn_check_img)
    Button btnCheckImg;
    private CheckBean.CheckItemListBean checkItem;

    public static CheckDetialFragment newInstance(CheckBean.CheckItemListBean checkItem) {
        CheckDetialFragment fragment = new CheckDetialFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CHECK_ITEM, checkItem);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            checkItem = getArguments().getParcelable(ARG_CHECK_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_detial, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData();
        return view;
    }

    private void loadingData() {

    }

    private void addClick() {
        //返回键
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //检查报告详情图片
        RxView.clicks(btnCheckImg)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        switchFatherContent(CheckDetialFragment.this,ReportImgFragment.newInstance(checkItem.getReportURL()));
                    }
                });
    }

    private void initView() {
        checkPart.setText(checkItem.getCheckPart());
        checkImageSeen.setText(checkItem.getImageSeen());
        checkResult.setText(checkItem.getImageResult());

        textViewName.setText(checkItem.getCheckPart());
    }
}
