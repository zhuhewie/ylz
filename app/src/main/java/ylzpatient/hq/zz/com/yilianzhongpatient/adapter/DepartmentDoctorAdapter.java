package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-9-12.
 */
public class DepartmentDoctorAdapter extends RecyclerView.Adapter<DepartmentDoctorAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<DoctorBean> listDoctor;
    private int type;

    public DepartmentDoctorAdapter(List<DoctorBean> listDoctor) {
        this.context = UtilApplication.getContextObject();
        this.inflater = LayoutInflater.from(UtilApplication.getContextObject());
        this.listDoctor = listDoctor;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sad_doctor_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoctorBean doctor = listDoctor.get(position);
//        holder.imgDoctor.setImageResource(doctor.getPhoto());
        holder.textDoctorName.setText(doctor.DoctorName);
        holder.textDoctorLv.setText( doctor.getClinicResponceName());
        holder.textDoctorGood.setText((String) doctor.getAdvantage());
    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDoctor;  //医生头像
        TextView textDoctorName; //医生名字
        TextView textDoctorLv;   //医生等级
        TextView textDoctorGood;   //医生擅长


        public ViewHolder(View itemView) {
            super(itemView);
            imgDoctor = (ImageView) itemView.findViewById(R.id.img_doctor);
            textDoctorName = (TextView) itemView.findViewById(R.id.text_doctor_name);
            textDoctorLv  = (TextView) itemView.findViewById(R.id.text_doctor_lv);
            textDoctorGood = (TextView) itemView.findViewById(R.id.text_doctor_good);

        }
    }
}
