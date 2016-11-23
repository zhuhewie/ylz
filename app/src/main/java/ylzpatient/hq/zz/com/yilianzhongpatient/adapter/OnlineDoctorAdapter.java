package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult.OnlineDoctorFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.CircleTransform;

/**
 * Created by Administrator on 2016-9-30.
 * 在线医生的Adapter
 */
public class OnlineDoctorAdapter extends RecyclerView.Adapter<OnlineDoctorAdapter.OnDoHolder> {


    private List<OnlineDoctorBean> listDoctor;
    private Context context;

    public OnlineDoctorAdapter() {
        listDoctor = new ArrayList<>();
    }

    private OnItemChildClickListener clickListener;
    private OnItemChildClickListener clickWenZenListener;

    public void setChildClickListener(OnItemChildClickListener clickListener) {
        this.clickListener = clickListener;
        this.clickWenZenListener = clickListener;
    }

    public interface OnItemChildClickListener {
        void onClick(View view, int position);

        void onWenZenClick(View view, int position);
    }


    public void setData(List<OnlineDoctorBean> listDoctor) {
        this.listDoctor = listDoctor;
    }

    @Override
    public OnDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new OnDoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.online_doctor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final OnDoHolder holder, final int position) {
        OnlineDoctorBean itemDoctor = listDoctor.get(position);
        holder.tvDoctorName.setText(itemDoctor.getDoctorName());
        holder.tvDoctorLv.setText(itemDoctor.getDoctorTitle());
        holder.tvHospitalDep.setText(itemDoctor.getHospitalName() + "\n" + itemDoctor.getDepartment());
        holder.tvDoctorGood.setText(itemDoctor.getBeGoodAt());
        holder.tvWaitNumber.setText(itemDoctor.getWaitingCount() + "");
        if (!TextUtils.isEmpty(itemDoctor.getHospitalServer() + itemDoctor.getDoctorAvatar())) {
            Picasso.with(context)
                    .load(itemDoctor.getHospitalServer() + itemDoctor.getDoctorAvatar()) //医生头像
                    .transform(new CircleTransform()) //圆
                    .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                    .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                    .tag(OnlineDoctorFragment.class.getName())
                    //.resize(imgWidth, imgWidth) //图片宽高
                    //.centerCrop()
                    .into(holder.imgDoctor);
        }

        holder.tvDoctorHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EventBus.getDefault().post();
                clickListener.onClick(view, position);
            }
        });
        holder.tvWenzen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickWenZenListener.onWenZenClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }

    class OnDoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_doctor)
        ImageView imgDoctor;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_doctor_lv)
        TextView tvDoctorLv;
        @BindView(R.id.tv_wait_number)
        TextView tvWaitNumber;
        @BindView(R.id.tv_hospital_dep)
        TextView tvHospitalDep;
        @BindView(R.id.tv_doctor_good)
        TextView tvDoctorGood;
        @BindView(R.id.tv_doctor_homepage)
        TextView tvDoctorHomepage;
        @BindView(R.id.tv_wenzen)
        TextView tvWenzen;

        public OnDoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
