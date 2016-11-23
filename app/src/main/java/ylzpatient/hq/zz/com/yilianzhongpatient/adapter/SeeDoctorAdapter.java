package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;

/**
 * Created by Administrator on 2016-9-30.
 */
public class SeeDoctorAdapter extends RecyclerView.Adapter<SeeDoctorAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.see_doctor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvHospital.setText("清远中医院" + position);
        holder.tvName.setText("就诊人:李"+position  );
        holder.tvDate.setText("就诊日期:2016-09-09");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hospital)
        TextView tvHospital;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_depart_name)
        TextView tvDepartName;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
