package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CheckBean;

/**
 * Created by Administrator on 2016-11-7.
 * 检查列表的adapter
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.checkHolder> {

    private List<CheckBean> listCheck;

    public void setData(List<CheckBean> listCheck) {
        this.listCheck = listCheck;
        notifyDataSetChanged();
    }

    @Override
    public checkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.check_item, parent, false);
        return new checkHolder(view);
    }

    @Override
    public void onBindViewHolder(checkHolder holder, int position) {
        CheckBean check = listCheck.get(position);
        holder.tvCheckName.setText(check.getReportName());
        holder.tvCheckDate.setText(check.getRptTime());
    }

    @Override
    public int getItemCount() {
        return listCheck == null?0:listCheck.size();
    }

    class checkHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_check_name)
        TextView tvCheckName;
        @BindView(R.id.tv_check_date)
        TextView tvCheckDate;


        public checkHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
