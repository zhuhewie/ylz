package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicalBean;

/**
 * Created by Administrator on 2016-11-14.
 * 患者健康档案中临床档案的adapter
 */

public class ReportClinicalAdapter extends RecyclerView.Adapter<ReportClinicalAdapter.ViewHolder> {

    private List<ClinicalBean> listClin;

    public ReportClinicalAdapter(List<ClinicalBean> listClin) {
        this.listClin = listClin;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.report_clinical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClinicalBean healthReport = listClin.get(position);
//        holder.sdvPatient.setImageURI(healthReport.getAvatar());
//        holder.tvName.setText(healthReport.getName());
//        holder.tvAge.setText(healthReport.getAge() + "岁");
//        holder.sdvPatient.setImageURI(healthReport.getAvatar());
//        holder.tvPatStatus.setVisibility(healthReport.isIsInHospital() ? View.VISIBLE : View.GONE); //如果是住院中,显示住院中的状态
//        holder.sdvSex.getHierarchy().setPlaceholderImage("男".equals(healthReport.getSex()) ? R.mipmap.body_ico_male : R.mipmap.body_ico_female);
    }

    @Override
    public int getItemCount() {
        return listClin == null ? 0 : listClin.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hospital)
        TextView tvHospital;
        @BindView(R.id.sdv_new)
        SimpleDraweeView sdvNew;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_doctor)
        TextView tvDoctor;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_data)
        TextView tvData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
