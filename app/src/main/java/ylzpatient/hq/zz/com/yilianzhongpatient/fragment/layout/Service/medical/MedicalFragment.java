package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.medical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MedicalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalFragment extends SuperFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.rv_medical)
    RecyclerView rvMedical;
    @BindView(R.id.srl_medical)
    SwipeRefreshLayout srlMedical;
    @BindView(R.id.sp_package_type)
    AppCompatSpinner spPackageType;
    @BindView(R.id.sp_package_age)
    AppCompatSpinner spPackageAge;
    @BindView(R.id.sp_package_sort)
    AppCompatSpinner spPackageSort;

    private String mParam1;
    private String mParam2;
    private ArrayAdapter<String> spAdapter;
    private List<String> spList;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MedicalFragment.
     */
    public static MedicalFragment newInstance() {
        MedicalFragment fragment = new MedicalFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_medical, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        spList = new ArrayList<>();
        spList.add("全部");
        spList.add("5-20岁");
        spList.add("20-30岁");
        spAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,spList);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPackageAge.setAdapter(spAdapter);
        spPackageSort.setAdapter(spAdapter);
        spPackageType.setAdapter(spAdapter);
    }
}
