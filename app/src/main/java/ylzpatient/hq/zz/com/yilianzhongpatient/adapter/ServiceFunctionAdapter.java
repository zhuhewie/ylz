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

/**
 * Created by Administrator on 2016-8-24.
 * 服务界面的功能列表的Adapter
 */
public class ServiceFunctionAdapter extends RecyclerView.Adapter<ServiceFunctionAdapter.myViewHolder>{


    private List<String> data;
    private LayoutInflater mInflater;
    private Context context;

    private String[] text
//            = {"门诊预约", "排号", "报告查询", "健康档案",
//            "费用查询", "体检商城", "就诊指南", "在线诊室"}
            ;

    private int[] img
//            = {R.mipmap.bottom_btn_menzhenyuyue2x,R.mipmap.bottom_btn_paihao2x,
//            R.mipmap.bottom_btn_baogaochaxun2x,R.mipmap.bottom_btn_jiankangdangan2x,
//            R.mipmap.bottom_btn_feiyongchaxun2x,R.mipmap.bottom_btn_tijianshangcheng2x,
//            R.mipmap.bottom_btn_jiuzhenzhinan2x,R.mipmap.bottom_btn_zaixianzhenshi2x
//    }
            ;

    //private int width;//屏幕的宽度
    //final float scale;
    //int px;  //1dp抓换成px的值
        //WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        width = wm.getDefaultDisplay().getWidth();//屏幕宽度
//        scale = context.getResources().getDisplayMetrics().density;  //根据手机的分辨率从 dp 的单位 转成为 px(像素)
//        px = (int)(1.0f * scale + 0.5f); //1dp转换成px的值

    public ServiceFunctionAdapter(Context context,String[] s,int[] img) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.img = img;
        this.text = s;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.service_function_item, parent, false);


        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.imageView.setImageResource(img[position]);
        holder.textView.setText(text[position]);
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public myViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.funct_img);
            textView = (TextView) itemView.findViewById(R.id.funct_text);

//            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) textView.getLayoutParams(); //取控件textView当前的布局参数
//
//            linearParams.weight = (width/4) - px;// 控件的宽强制修改
//            textView.setLayoutParams(linearParams);//使设置好的布局参数应用到控件
        }
    }
}
