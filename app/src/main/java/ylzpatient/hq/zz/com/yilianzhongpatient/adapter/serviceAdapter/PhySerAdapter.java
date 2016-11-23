package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

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

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.JoinHospitalAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;

/**
 * Created by Administrator on 2016-8-25.
 * 体检套餐列表的Adaptr
 */
public class PhySerAdapter extends RecyclerView.Adapter<PhySerAdapter.PhySerHolder>{

    private Context context;
    private List<IndexBean.MedicalPackagesBean> list = new ArrayList<>();

    public PhySerAdapter() {
    }

    public void setData(List<IndexBean.MedicalPackagesBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public PhySerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PhySerHolder(LayoutInflater.from(context)
                .inflate(R.layout.service_physical_examination_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PhySerHolder holder, int position) {
        IndexBean.MedicalPackagesBean phySer = list.get(position);
        if (phySer != null) {
            holder.mealName.setText(phySer.getName());
            holder.mealNewPrice.setText(phySer.getDiscountPrice()+"");
            holder.mealOldPrice.setText(phySer.getStandardPrice() + "");
            if (!TextUtils.isEmpty(phySer.getLogo())){
                Picasso.with(context)
                        .load(phySer.getLogo()) //咨询图片
                        .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                        .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                        .tag(JoinHospitalAdapter.class.getName())
                        //.resize(imgWidth, imgWidth) //图片宽高
                        //.centerCrop()
                        .into(holder.mealImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    class PhySerHolder extends RecyclerView.ViewHolder {

        ImageView mealImage;
        TextView mealName;
        TextView mealNewPrice;
        TextView mealOldPrice;

        public PhySerHolder(View itemView) {
            super(itemView);
            mealImage = (ImageView) itemView.findViewById(R.id.meal_image);
            mealName = (TextView) itemView.findViewById(R.id.meal_name);
            mealNewPrice = (TextView) itemView.findViewById(R.id.meal_meony);
            mealOldPrice = (TextView) itemView.findViewById(R.id.meal_old_meony);
        }
    }

}
