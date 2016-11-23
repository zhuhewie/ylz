package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.meAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AuthorizeResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.listener.OnItemClickListener;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-10-18.
 * 我的授权的adapter
 */

public class MyAuthorizeAdapter extends SwipeMenuAdapter<MyAuthorizeAdapter.AuthorizeHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<AuthorizeResultBean> list;
    private int type;
    public static final int SHOW_AUTHORIZE = 1;
    public static final int GONE_AUTHORIZE = 2;

    public MyAuthorizeAdapter(int type) {
        this.type = type;
    }

    public void setData(List<AuthorizeResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.my_authorize_item, parent, false);
    }

    @Override
    public MyAuthorizeAdapter.AuthorizeHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AuthorizeHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(AuthorizeHolder holder, int position) {
        if (list != null && list.size() > 0) {
            AuthorizeResultBean authorize = list.get(position);
            if (!Uitls.isEmptyClazz(authorize)) {
                holder.tvName.setText(authorize.getName());
                holder.tvAge.setText(authorize.getAge()+ "岁");
                holder.tvData.setText(authorize.getTime().substring(0,10));
                //holder.tvAuthorize.setText(authorize.getAuthorizeId());
                int imgMap = authorize.getSex().equals("男")?R.mipmap.body_ico_male:R.mipmap.body_ico_female;
                Picasso.with(UtilApplication.getContextObject())
                        .load(imgMap) //性别照片
                        //.transform(new CircleTransform()) //圆
                        .tag(MyAuthorizeAdapter.class.getName())
                        //.resize(imgWidth, imgWidth) //图片宽高
                        //.centerCrop()
                        .into(holder.imgSex);
            }
        }
        if (type == SHOW_AUTHORIZE) {
            holder.tvAuthorize.setVisibility(View.VISIBLE);
        } else {
            holder.tvAuthorize.setVisibility(View.GONE);
        }
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list == null? 0:list.size();
    }

    static class AuthorizeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvAge;
        private TextView tvData;
        private TextView tvAuthorize;
        private ImageView imgSex;

        OnItemClickListener mOnItemClickListener;


        public AuthorizeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvAuthorize = (TextView) itemView.findViewById(R.id.tv_authorize);
            imgSex = (ImageView) itemView.findViewById(R.id.img_sex);
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
