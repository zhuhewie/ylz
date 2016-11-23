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
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;

/**
 * Created by Administrator on 2016-11-14.
 * 患者健康档案列表的adapter
 */

public class HealthReportAdapter extends RecyclerView.Adapter<HealthReportAdapter.ViewHolder> {
    private List<HealthPatientBean> listPatReport;

    public HealthReportAdapter(List<HealthPatientBean> listPatReport) {
        this.listPatReport = listPatReport;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.health_pat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HealthPatientBean healthReport = listPatReport.get(position);
        holder.sdvPatient.setImageURI(healthReport.getAvatar());
        holder.tvName.setText(healthReport.getName());
        holder.tvAge.setText(healthReport.getAge()+"岁");
        holder.sdvPatient.setImageURI(healthReport.getAvatar());
        holder.tvPatStatus.setVisibility(healthReport.isIsInHospital()?View.VISIBLE:View.GONE); //如果是住院中,显示住院中的状态
        holder.sdvSex.getHierarchy().setPlaceholderImage("男".equals(healthReport.getSex())?R.mipmap.body_ico_male:R.mipmap.body_ico_female);
    }

    @Override
    public int getItemCount() {
        return listPatReport == null ? 0 : listPatReport.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_patient)
        SimpleDraweeView sdvPatient;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.sdv_sex)
        SimpleDraweeView sdvSex;
        @BindView(R.id.tv_pat_status)
        TextView tvPatStatus;
        @BindView(R.id.tv_age)
        TextView tvAge;
        @BindView(R.id.image_next)
        SimpleDraweeView imageNext;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
