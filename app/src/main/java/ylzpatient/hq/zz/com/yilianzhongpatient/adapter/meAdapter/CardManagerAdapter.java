package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.meAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.listener.OnItemClickListener;

/**
 * Created by Administrator on 2016-10-18.
 * 就诊卡管理的adapter
 */

public class CardManagerAdapter extends SwipeMenuAdapter<CardManagerAdapter.CardHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<CardBean> list;

    public CardManagerAdapter(List<CardBean> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.m_card_item, parent, false);
    }

    @Override
    public CardManagerAdapter.CardHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new CardHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        CardBean card = list.get(position);
        holder.tvCardNumber.setText(card.getPatientIdCardNo());
        holder.tvName.setText(card.getPatientName());
        holder.tvHospital.setText(card.getHospitalName());
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvHospital;
        private TextView tvCardNumber;

        OnItemClickListener mOnItemClickListener;


        public CardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvHospital = (TextView) itemView.findViewById(R.id.tv_hospital);
            tvCardNumber = (TextView) itemView.findViewById(R.id.tv_card_number);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getAdapterPosition(), getItemId());
            }
        }
    }
}
