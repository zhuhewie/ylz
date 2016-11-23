package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.CornerTransform;

/**
 * Created by Administrator on 2016-10-10.
 */
public class WaitImgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> mSelectPath;
    //添加图片标签
    private static final int ADDIMG_TYPE = 0;
    //显示图片
    public static final int IMG_TYPE = 1;
    //图片的宽度 屏幕的1/4
    private int imgWidth = GeneralConfig.screenWidth / 4 - 20;

    public WaitImgAdapter(Context context) {
        mSelectPath = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<String> mSelectPath) {
        this.mSelectPath = mSelectPath;
    }

    public void addData(int postion, String file) {
        mSelectPath.add(postion, file);
        notifyDataSetChanged();
    }

    public void removeData(int postion) {
        mSelectPath.remove(postion);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == IMG_TYPE) {
            return new ImgHodler(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.o_wait_img_item, parent, false));
        } else {
            return new AddHodler(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.o_wait_img_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //String file = mSelectPath.get(position);
        if (holder instanceof AddHodler) {
            if ((mSelectPath.size() < 8) && (position == mSelectPath.size())) {
                Picasso.with(context)
                        .load(R.mipmap.bottom_btn_tianjia_pre_2x)
                        //.transform(new CornerTransform()) //圆角
                        .tag(MultiImageSelectorFragment.TAG)
                        .resize(imgWidth, imgWidth)
                        .centerCrop()
                        .into(((AddHodler) holder).imgWait);
                ((AddHodler) holder).imgWait.setBackgroundResource(R.drawable.touch_bg_rectangle_corner_stroke);
                ((AddHodler) holder).imgWait.setPadding(45,0,45,0);
        }
        //提示:position从0开始,size从一开始.
        } else if (holder instanceof ImgHodler){
            Picasso.with(context)
                    .load(new File(mSelectPath.get(position)))
                    .transform(new CornerTransform()) //圆角
                    //.placeholder(me.nereo.multi_image_selector.R.drawable.mis_default_error)
                    .tag("WaitFragment")
                    .resize(imgWidth, imgWidth)
                    .centerCrop()
                    .into(((ImgHodler) holder).imgWait);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int result;
        result = (mSelectPath.size() < 8) && (position == mSelectPath.size()) ? ADDIMG_TYPE : IMG_TYPE;
        return result;
    }

    /**
     * 有多少个条目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mSelectPath.size() < 8 ? mSelectPath.size() + 1 : 8;
    }

    class ImgHodler extends RecyclerView.ViewHolder {
        ImageView imgWait;

        public ImgHodler(View itemView) {
            super(itemView);
            imgWait = (ImageView) itemView.findViewById(R.id.img_wait_choose);
        }
    }

    class AddHodler extends RecyclerView.ViewHolder {
        ImageView imgWait;

        public AddHodler(View itemView) {
            super(itemView);
            imgWait = (ImageView) itemView.findViewById(R.id.img_wait_choose);
        }
    }
}
