package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.reportQuery;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportImgFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportImgFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMGURL = "imgUrl";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.pimg_report)
    ImageView pimgReport;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
//    @BindView(R.id.fbsdv_report)
//    SimpleDraweeView fbsdvReport;

    private String imgUrl;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReportImgFragment.
     */
    public static ReportImgFragment newInstance(String imgUrl) {
        ReportImgFragment fragment = new ReportImgFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMGURL, imgUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imgUrl = getArguments().getString(ARG_IMGURL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //自定义的view缩放
        View view = inflater.inflate(R.layout.fragment_report_img, container, false);

        //fresco的缩放
//        View view = inflater.inflate(R.layout.fragment_report_fresco, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        return view;
    }

    private void initView() {
//        if (!TextUtils.isEmpty(imgUrl)) {
////            fbsdvReport.setImageURI(imgUrl);
//        }
    }

    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(imgUrl)) {
            Picasso.with(UtilApplication.getContextObject())
                    .load(imgUrl)
                    .tag(ReportImgFragment.class.getName())
                    //.transform(new CropSquareTransformation())
                    .into(pimgReport);
        } else {
            Snackbar.make(pimgReport,"没有报告详情图片",Snackbar.LENGTH_LONG).show();
        }
    }
}
