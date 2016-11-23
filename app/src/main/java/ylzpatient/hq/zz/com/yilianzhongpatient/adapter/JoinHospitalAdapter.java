package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;

/**
 * Created by Administrator on 2016-10-24.
 */

public class JoinHospitalAdapter extends RecyclerView.Adapter<JoinHospitalAdapter.JoinHosHolder> {
    private List<IndexBean.HospitalsBean> listHos;
    private Context context;
    private int imgSize = (GeneralConfig.screenWidth / 3)-30;

    public JoinHospitalAdapter() {
    }

    public void setData(List<IndexBean.HospitalsBean> listHos) {
        this.listHos = listHos;
        notifyDataSetChanged();
    }

    @Override
    public JoinHosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new JoinHosHolder(LayoutInflater.from(context).inflate(R.layout.service_hospital_item, parent, false));
    }

    @Override
    public void onBindViewHolder(JoinHosHolder holder, int position) {
        IndexBean.HospitalsBean hos = listHos.get(position);
        if (hos != null) {
            holder.tvHosName.setText(hos.getName());
            if (!TextUtils.isEmpty(hos.getLogo())) {
                Picasso.with(context)
                        .load(hos.getLogo().toString()) //医院logo
//                        .transform(new CircleTransform()) //圆
                        .placeholder(R.mipmap.hospital_image_default)//当图片没有加载上的时候，显示的图片
                        .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                        .tag(JoinHospitalAdapter.class.getName())
                        .resize(imgSize, imgSize-80) //图片宽高
                        .centerCrop()
                        .into(holder.imgHosLogo);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listHos == null ? 0 : listHos.size();
    }

    class JoinHosHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_hos_logo)
        ImageView imgHosLogo;
        @BindView(R.id.tv_hos_name)
        TextView tvHosName;
        @BindView(R.id.ll_hos)
        LinearLayout llHos;


        public JoinHosHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
