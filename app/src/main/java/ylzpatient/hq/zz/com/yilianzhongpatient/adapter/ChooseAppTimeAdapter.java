package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;

/**
 * Created by Administrator on 2016-9-27.
 */
public class ChooseAppTimeAdapter extends RecyclerView.Adapter<ChooseAppTimeAdapter.ViewHolder>{

    private List<TimeBean> listTime = new ArrayList<>();

    public ChooseAppTimeAdapter() {
    }
    public void setData(List<TimeBean> listTime){
        this.listTime = listTime;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_choose_app_time_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TimeBean itemTime = listTime.get(position);
        holder.textAppTime.setText(itemTime.BeginTime + "-" + itemTime.EndTime);
    }

    @Override
    public int getItemCount() {
        return listTime.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textAppTime;
        CheckBox cbSelect;
        public ViewHolder(View itemView) {
            super(itemView);
            textAppTime = (TextView) itemView.findViewById(R.id.text_app_time);
            cbSelect = (CheckBox) itemView.findViewById(R.id.cb_select_type);
        }
    }

}
