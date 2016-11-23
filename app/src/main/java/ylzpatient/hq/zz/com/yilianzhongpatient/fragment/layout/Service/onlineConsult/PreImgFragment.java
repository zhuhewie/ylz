package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorFragment;
import me.nereo.multi_image_selector.event.ImageSelectorEvent;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.PreWaitImgAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;

/**
 * Created by Administrator on 2016-10-11.
 * 候诊界面的症状图的预览界面
 */
public class PreImgFragment extends SuperFragment {

    private static final String ARG_PATH_LIST = "arg_path_list";
    private static final String ARG_POSTION = "arg_postion";
    @BindView(R.id.vp_pre_img)
    ViewPager vpPreImg;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;

    private PreWaitImgAdapter adapter;
    private ArrayList<String> mSelectPath;
    private int postion; //默认显示的图片下标
    private int showingPosition; //当前正在显示的view的下标
    private ArrayList<ImageView> imagelist;

    public static PreImgFragment newInstance(ArrayList<String> mSelectPath, int postion) {
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PATH_LIST, mSelectPath);
        args.putInt(ARG_POSTION, postion);
        PreImgFragment fragment = new PreImgFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSelectPath = bundle.getStringArrayList(ARG_PATH_LIST);
            postion = bundle.getInt(ARG_POSTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.o_wait_img_pre_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();


        return v;
    }

    private void addClick() {
        RxView.clicks(imgFunction)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        imagelist.remove(showingPosition);
                        mSelectPath.remove(showingPosition);
                        if (mSelectPath == null || mSelectPath.size() == 0) {
                            getFragmentManager().popBackStack();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {

        imgFunction.setImageResource(R.mipmap.body_btn_delete_2x);
        textViewName.setText((postion + 1) + "/" + mSelectPath.size());

        imagelist = new ArrayList<ImageView>();
        ImageView iv;
        for (String s : mSelectPath) {
            iv = new ImageView(getContext());
            Picasso.with(getContext())
                    .load(new File(s))
                    //.resize(Uitls.getScreenWidth(getContext()), Uitls.getScreenHeight(getContext()))
                    //.centerCrop()
                    .tag(MultiImageSelectorFragment.TAG)
                    .into(iv);
            iv.setBackgroundResource(R.color.black);
            imagelist.add(iv);
        }
        adapter = new PreWaitImgAdapter(imagelist);
        vpPreImg.setAdapter(adapter);
        vpPreImg.setCurrentItem(postion);
        vpPreImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textViewName.setText((position + 1) + "/" + mSelectPath.size());
                showingPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
//        llManager.setOrientation(LinearLayoutManager.HORIZONTAL); //水平滑动
//        rvWaitImgPre.setLayoutManager(llManager);
//        adapter = new PreWaitImgAdapter();
//        rvWaitImgPre.setAdapter(adapter);
//        adapter.setData(mSelectPath);
        //rvWaitImgPre.scrollToPosition(postion);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().post(new ImageSelectorEvent(mSelectPath));
    }
}
