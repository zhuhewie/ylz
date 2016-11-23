package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;

/**
 * Created by Administrator on 2016-10-9.
 */
public class PatientCardAdapter extends RecyclerView.Adapter<PatientCardAdapter.PatientCardHolder> {

    private List<PatientCardBean> listPatientCard = new ArrayList<>();

    int lastSelected = 0;

    public void setData(List<PatientCardBean> listPatientCard) {
        this.listPatientCard = listPatientCard;
    }

    public void setSelectIndex(int i) {
        if (lastSelected == i) return;

        lastSelected = i;
        notifyDataSetChanged();
    }

    public int getSelectIndex() {
        return lastSelected;
    }

    @Override
    public PatientCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PatientCardHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.od_choose_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PatientCardHolder holder, int position) {
        PatientCardBean card = listPatientCard.get(position);
        holder.tvName.setText(card.getPatName());
        holder.tvCardNumber.setText(card.getPatId());
        holder.tvDefaultCard.setVisibility(View.GONE);
        holder.imgCheck.setVisibility(lastSelected == position?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return listPatientCard.size();
    }

    class PatientCardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_default_card)
        TextView tvDefaultCard;
        @BindView(R.id.tv_card_number)
        TextView tvCardNumber;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.img_check)
        ImageView imgCheck;


        public PatientCardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
