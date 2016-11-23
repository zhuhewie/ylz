package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.DateWeek;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorAppMessageBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-26.
 * 医生预约排班列表的adapter
 */
public class DoctorAppDateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //预约信息的布局标签,可点击
    private static final int NORMAL_TYPE = 0;
    //显示时间和星期的布局标签
    private static final int TITLE_TYPE = 1;
    private static final int BLANK_TYPE = 2;

    private List<DateWeek> dateWeekList = new ArrayList<>();
    private List<Object> appMesList = new ArrayList<>();
    private LinearLayout.LayoutParams params;

    public DoctorAppDateAdapter() {
    }

    public void setData(List<Object> appMesList) {
        //addDateWeek();
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 10);
        this.appMesList = appMesList;
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TITLE_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sadd_app_date_item_1, parent, false);
            return new TitleHolder(view);
        } else if (viewType == NORMAL_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sadd_app_date_item_2, parent, false);
            return new NormalHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sadd_app_date_item_3, parent, false);
            return new BlankHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // DateWeek dateWeek = dateWeekList.get(position);
        if (holder instanceof TitleHolder) {
            DateWeek itemDateWeek = (DateWeek) appMesList.get(position);
            ((TitleHolder) holder).date.setText(itemDateWeek.getDate().substring(5));
            ((TitleHolder) holder).week.setText(itemDateWeek.getWeek());
//            ((TitleHolder) holder).dateWeekLayout.setLayoutParams(params);
        } else if (holder instanceof NormalHolder) {
            DoctorAppMessageBean itemLvMoney = (DoctorAppMessageBean) appMesList.get(position);

            ((NormalHolder) holder).lv.setText(itemLvMoney.getClinicResponceName());
            ((NormalHolder) holder).money.setText("￥" + itemLvMoney.getClinicResponceFee());
            //((NormalHolder) holder).layout.setBackgroundResource(R.drawable.touch_bg_green_rectangle);
            if (clickListener != null) {
                ((NormalHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onClick(view, position);
                    }
                });


            }

        }
    }

    /**
     * 条目多布局标签
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int result = position % 3 == 0 ? TITLE_TYPE : NORMAL_TYPE;
        if (result == NORMAL_TYPE && appMesList.get(position) == null) {
            result = BLANK_TYPE;
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return appMesList.size();
    }

    //日期显示的holder
    class TitleHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView week;
        LinearLayout dateWeekLayout;

        public TitleHolder(View itemView) {
            super(itemView);
            dateWeekLayout = (LinearLayout) itemView.findViewById(R.id.layout_date_week);
            date = (TextView) itemView.findViewById(R.id.text_date);
            week = (TextView) itemView.findViewById(R.id.text_week);
        }

    }

    //挂号显示的holder
    class NormalHolder extends RecyclerView.ViewHolder {
        TextView lv;
        TextView money;
        LinearLayout layout;

        public NormalHolder(View itemView) {
            super(itemView);
            lv = (TextView) itemView.findViewById(R.id.text_am_lv);
            money = (TextView) itemView.findViewById(R.id.text_am_app_money);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_am_lv_money);
        }
    }

    class BlankHolder extends RecyclerView.ViewHolder {
        LinearLayout blankLayout;

        public BlankHolder(View itemView) {
            super(itemView);
            blankLayout = (LinearLayout) itemView.findViewById(R.id.layout_blank);
        }
    }

    private void addDateWeek() {
        for (int i = 0; i < 30; i++) {
            dateWeekList.add(new DateWeek(Uitls.getTimeAndhms(i, null), Uitls.getWeek(i)));
        }
    }
}
