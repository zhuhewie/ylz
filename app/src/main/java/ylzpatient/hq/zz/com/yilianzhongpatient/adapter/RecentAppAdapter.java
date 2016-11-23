package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-9-30.
 */
public class RecentAppAdapter extends RecyclerView.Adapter<RecentAppAdapter.ViewHolder> {


    private List<AppMessBean> list;
    private Context context = UtilApplication.getContextObject();

    public void setData(List<AppMessBean> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.see_doctor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppMessBean appMess = list.get(position);
        holder.tvHospital.setText(appMess.getHospitalName());
        holder.tvName.setText(appMess.getPatientName());
        holder.tvDate.setText(appMess.getTime());
        holder.tvDepartName.setText(appMess.getDepartment()+"    |    "+appMess.getDoctor());
        holder.tvState.setText(getState(appMess.getStatus()));
    }

    @Override
    public int getItemCount() {
        return list ==null?0:list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hospital)
        TextView tvHospital;
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

    private String getState(int state) {
        String result = "未知";
        switch (state){
            case 0:
                result = "初始状态";
                break;
            case 1:
                result = "预约成功";
                break;
            case 2:
                result = "报到成功";
                break;
            case 3:
                result = "候诊中";
                break;
            case 4:
                result = "已候诊";
                break;
            case 5:
                result = "已缴费";
                break;
            case 6:
                result = "就诊结束";
                break;
            case 8:
                result = "取消";
                break;
            default:
                break;
        }
        return result;
    }
}
