package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.SendChatBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReceiveChatBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.CircleTransform;

/**
 * Created by Administrator on 2016-10-17.
 * 诊室界面医患聊天记录的adapter
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //医生和患者的布局标签
    private static final int ME_TYPE = 1;
    private static final int OTHER_TYPE = 2;

    private Context context;
    private List<Object> list;

    public ChatAdapter(Context context,List<Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ME_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_me_item, parent,false);
            return new meHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_other_item, parent,false);
            return new patientHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof meHolder) {
            SendChatBean postionText = (SendChatBean) list.get(position);
            ((meHolder) holder).tvMeText.setText(postionText.getMsg());
            Picasso.with(context)
                    .load(R.mipmap.huanzhe_image) //患者照片
                    .transform(new CircleTransform()) //圆
                    .tag(ChatAdapter.class.getName())
                    //.resize(imgWidth, imgWidth) //图片宽高
                    //.centerCrop()
                    .into(((meHolder) holder).imgMe);

        } else if (holder instanceof patientHolder){
            ReceiveChatBean receMess = (ReceiveChatBean) list.get(position);
            ((patientHolder) holder).tvOtherText.setText(receMess.getMsg());
            Picasso.with(context)
                    .load(R.mipmap.yisheng_image) //医生照片
                    .transform(new CircleTransform()) //圆
                    .tag(ChatAdapter.class.getName())
                    //.resize(imgWidth, imgWidth) //图片宽高
                    //.centerCrop()
                    .into(((patientHolder) holder).imgOther);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    /**
     * 条目多布局标签
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (list!= null&&list.size()>0) {
            type = list.get(position) instanceof SendChatBean?ME_TYPE:OTHER_TYPE;
        }
        return type;
    }


    //医生的holder
    class meHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_me_text)
        TextView tvMeText;
        @BindView(R.id.img_me)
        ImageView imgMe;

        public meHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

    //患者自己的holder
    class patientHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_other)
        ImageView imgOther;
        @BindView(R.id.tv_other_text)
        TextView tvOtherText;

        public patientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
