package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.MainInspectionBean;

/**
 * Created by Administrator on 2016-11-4.
 * 主检查报告的adapter
 */

public class MainInspectAdapter extends RecyclerView.Adapter<MainInspectAdapter.InspectHolder> {

    private List<MainInspectionBean> listInspe;

    public MainInspectAdapter() {
    }

    public void setData(List<MainInspectionBean> listInspe) {
        this.listInspe = listInspe;
        notifyDataSetChanged();
    }

    @Override
    public InspectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InspectHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_inspect_item, parent, false));
    }

    @Override
    public void onBindViewHolder(InspectHolder holder, int position) {
        MainInspectionBean postionInspe = listInspe.get(position);
        holder.tvCheckoutName.setText(postionInspe.getReportName());
        holder.tvCheckoutDate.setText(postionInspe.getRptTime().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return listInspe == null ? 0 : listInspe.size();
    }

    class InspectHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_checkout_name)
        TextView tvCheckoutName;
        @BindView(R.id.tv_checkout_date)
        TextView tvCheckoutDate;
        @BindView(R.id.img_next)
        ImageView imgNext;
        @BindView(R.id.ll_inspect_item)
        LinearLayout llInspectItem;

        public InspectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
