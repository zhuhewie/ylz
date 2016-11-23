package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;

/**
 * Created by Administrator on 2016-9-1.
 * 按疾病预约的fragment
 */
public class DiseaseFragment extends SuperFragment {
    private static String name = "按疾病预约";

    public static DiseaseFragment newInstance() {

        DiseaseFragment fragment = new DiseaseFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ser_appoin_disease_layout,container,false);
        return v;
    }
}
