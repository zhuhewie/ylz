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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;

/**
 * Created by Administrator on 2016-10-24.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewsHolder> {

    private Context context;
    private List<IndexBean.NewsBean> listNews;

    public void setData(List<IndexBean.NewsBean> listNews) {
        this.listNews = listNews;
        notifyDataSetChanged();
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.service_news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        IndexBean.NewsBean news = listNews.get(position);
        if (news != null) {
            holder.tvNewsTitle.setText(news.getTitle());
            holder.tvNewsMess.setText(news.getContent());
            if (!TextUtils.isEmpty(news.getLogo())) {
                Picasso.with(context)
                        .load(news.getLogo()) //咨询图片
                        .placeholder(R.mipmap.doctor_icon)//当图片没有加载上的时候，显示的图片
                        .error(R.mipmap.loading_error) // .当图片加载错误的时候，显示图片
                        .tag(JoinHospitalAdapter.class.getName())
                        //.resize(imgWidth, imgWidth) //图片宽高
                        //.centerCrop()
                        .into(holder.imgNews);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listNews == null ? 0 : listNews.size();
    }

    static class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_news)
        ImageView imgNews;
        @BindView(R.id.tv_news_title)
        TextView tvNewsTitle;
        @BindView(R.id.tv_news_mess)
        TextView tvNewsMess;
        private Context context;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
