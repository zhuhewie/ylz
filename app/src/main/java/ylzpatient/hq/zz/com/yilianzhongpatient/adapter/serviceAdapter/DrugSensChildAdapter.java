package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.InspectionDetailBean;

/**
 * Created by Administrator on 2016-11-7.
 * 药敏子条目的holder
 */

public class DrugSensChildAdapter extends RecyclerView.Adapter<DrugSensChildAdapter.DrugSensChildeHolder> {
    private List<InspectionDetailBean.DrugSensBean.DrugSensResListBean> listDrugChild;

    public DrugSensChildAdapter(List<InspectionDetailBean.DrugSensBean.DrugSensResListBean> listDrugChild) {
        this.listDrugChild = listDrugChild;
    }


    @Override
    public DrugSensChildeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspec_yaomin_item_2, parent, false);
        return new DrugSensChildeHolder(view);
    }

    @Override
    public void onBindViewHolder(DrugSensChildeHolder holder, int position) {
        InspectionDetailBean.DrugSensBean.DrugSensResListBean childDrug = listDrugChild.get(position);
        holder.tvDrugName.setText(childDrug.getAntimicrobicCnName() + "(" + childDrug.getAntimicrobicEgName() + ")");
        holder.tvDrugValue.setText(childDrug.getSensitivity());
        holder.tvDrugResult.setText(childDrug.getMICResult());
    }



    @Override
    public int getItemCount() {
        return listDrugChild == null?0:listDrugChild.size();
    }

    //药敏子条目的holder
    class DrugSensChildeHolder extends RecyclerView.ViewHolder {
        TextView tvDrugName;
        TextView tvDrugValue;
        TextView tvDrugResult;

        public DrugSensChildeHolder(View itemView) {
            super(itemView);
            tvDrugName = (TextView) itemView.findViewById(R.id.tv_drug_sens_name);
            tvDrugValue = (TextView) itemView.findViewById(R.id.tv_drug_sens_value);
            tvDrugResult = (TextView) itemView.findViewById(R.id.tv_drug_sens_result);
        }
    }
}
