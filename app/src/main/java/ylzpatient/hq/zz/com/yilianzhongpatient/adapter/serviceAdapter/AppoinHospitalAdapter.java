package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * 预约医院列表的adapter
 * Created by Administrator on 2016-9-2.
 */
public class AppoinHospitalAdapter extends RecyclerView.Adapter<AppoinHospitalAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private Context mContext;
    private List<Hopital> listHospital;

    public AppoinHospitalAdapter(List<Hopital> listHospital) {
        Context context = UtilApplication.getContextObject();
        this.inflater = LayoutInflater.from(UtilApplication.getContextObject());
        this.listHospital = listHospital;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ser_appoin_hospital_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hopital hospital = listHospital.get(position);
        //holder.imageHospitalLoge.setImageResource(hospital.getOrgLogo());
        holder.textHospitalName.setText(hospital.getOrgName());
        holder.textHospitalLv.setText(hospital.getGradeLevel());

    }

    @Override
    public int getItemCount() {
        return listHospital.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHospitalLoge;  //医院logo
        TextView textHospitalName; //医院名字
        TextView textHospitalLv;   //医院等级

        public ViewHolder(View itemView) {
            super(itemView);
            imageHospitalLoge = (ImageView) itemView.findViewById(R.id.image_hospital_logo);
            textHospitalName = (TextView) itemView.findViewById(R.id.text_hospital_name);
            textHospitalLv = (TextView) itemView.findViewById(R.id.text_hospital_lv);
        }
    }
}
