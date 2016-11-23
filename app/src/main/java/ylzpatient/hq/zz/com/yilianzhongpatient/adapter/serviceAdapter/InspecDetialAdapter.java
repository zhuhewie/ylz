package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.InspectionDetailBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.DividerItemDecoration;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-11-7.
 * 检验详情的holder
 */

public class InspecDetialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ORDINARY_TYPE = 1;//普通条目的布局标签
    private static final int DRUGSENS_TYPE = 2; //药敏的条目标签
    private static final int SMEAR_TYPE = 3; //涂片的条目标签


    private InspectionDetailBean ins;

    public void setData(InspectionDetailBean ins) {
        if (ins != null) {
            this.ins = ins;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ORDINARY_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspec_ordinary_item, parent, false);
            return new OrdHolder(view);
        } else if (viewType == SMEAR_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspec_smear_item, parent, false);
            return new SmeHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspec_yaomin_item_1, parent, false);
            return new DrugSensHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SmeHolder) { //涂片条目
            InspectionDetailBean.SmearCheckBean check = ins.getSmearCheckItemList().get(position);
            ((SmeHolder) holder).tvSmearName.setText(check.getItemResult());
            if ("阴".equals(check.getPositiveFlag())) {
                ((SmeHolder) holder).tvSmearResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_result));
                ((SmeHolder) holder).tvSmearResult.setText(check.getPositiveFlag() + "性");
            } else {
                ((SmeHolder) holder).tvSmearResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                ((SmeHolder) holder).tvSmearResult.setText(check.getPositiveFlag() + "性");
            }
        } else if (holder instanceof OrdHolder) {
            InspectionDetailBean.CheckItemListBean check = ins.getCheckItemList().get(position);
            ((OrdHolder) holder).tvCheckOutName.setText(check.getItemCnName());
            String range = check.getItemRange();
            if (range != "") {
                if (check.getUpLowHint().equals("阳性")) {
                    ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                    ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                    ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult() + check.getUpLowHint());
                    ((OrdHolder) holder).tvCheckConsult.setText(range);
                } else if (check.getUpLowHint().equals("阴性")) {
                    ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult());
                    ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_item));
                    ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_result));
                    ((OrdHolder) holder).tvCheckConsult.setText(range);
                } else {


                    String[] split = range.split("--");
                    try {
                        float start = Float.parseFloat(split[0]);
                        float end = Float.parseFloat(split[1]);
                        float result = Float.parseFloat(check.getItemResult());
                        if (result > end) {
                            ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                            ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                            ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult() + check.getUpLowHint());
                        } else {
                            ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult());
                            ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_item));
                            ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_result));
                        }
                        ((OrdHolder) holder).tvCheckConsult.setText(start + " ~ " + end + "(" + check.getItemUnit() + ")");
                        //出现 数字转换异常的时候
                    } catch (NumberFormatException e) {
                        try {
                            String start = split[0];
                            String end = split[1];
                            String result = check.getItemResult();
                            ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_item));
                            ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                            ((OrdHolder) holder).tvCheckOutResult.setText(result);
                            ((OrdHolder) holder).tvCheckConsult.setText(start + " " + end);
                            //获取参考范围的 出现超出 数组长度的时候
                        } catch (ArrayIndexOutOfBoundsException e1) {
                            String sRange = check.getItemRange();
                            if (sRange.indexOf("阴") != -1) {
                                ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult());
                                ((OrdHolder) holder).tvCheckConsult.setText(sRange);
                                ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_item));
                                ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_result));

                            } else if (sRange.indexOf("阳") != -1) {
                                ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult());
                                ((OrdHolder) holder).tvCheckConsult.setText(sRange);
                                ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                                ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.problem_item));
                            } else {
                                ((OrdHolder) holder).tvCheckOutResult.setText(check.getItemResult());
                                ((OrdHolder) holder).tvCheckConsult.setText(sRange);
                                ((OrdHolder) holder).tvCheckOutName.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_item));
                                ((OrdHolder) holder).tvCheckOutResult.setTextColor(UtilApplication.getContextObject().getResources().getColor(R.color.normal_result));
                            }
                        }
                    }
                    //若结果超出 参考范围 设置 标注颜色
                }
            }
        } else if (holder instanceof DrugSensHolder) {//药敏的条目
            InspectionDetailBean.DrugSensBean drugSens = ins.getDrugSensCheckItemList().get(position);
            ((DrugSensHolder) holder).tvDurgName.setText("菌名:" + drugSens.getBacteriaName());
            List<InspectionDetailBean.DrugSensBean.DrugSensResListBean> drugSensResList = drugSens.getDrugSensResList();
            if (drugSensResList != null && drugSensResList.size() > 0) {
                DrugSensChildAdapter drugChildAdapter = new DrugSensChildAdapter(drugSensResList);
                LinearLayoutManager llManager = new LinearLayoutManager(UtilApplication.getContextObject());
                llManager.setOrientation(LinearLayoutManager.VERTICAL);
                ((DrugSensHolder) holder).rvChildDurd.addItemDecoration(new DividerItemDecoration(UtilApplication.getContextObject(), DividerItemDecoration.VERTICAL_LIST));
                ((DrugSensHolder) holder).rvChildDurd.setLayoutManager(llManager);
                ((DrugSensHolder) holder).rvChildDurd.setAdapter(drugChildAdapter);
            }
        }
    }

    //列表长度
    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (ins != null) {
            if (ins.getReportTypeValue() == ORDINARY_TYPE) {//普通
                itemCount = ins.getCheckItemList() == null ? 0 : ins.getCheckItemList().size();
            } else if (ins.getReportTypeValue() == SMEAR_TYPE) {//涂片
                itemCount = ins.getSmearCheckItemList() == null ? 0 : ins.getSmearCheckItemList().size();
            } else if (ins.getReportTypeValue() == DRUGSENS_TYPE) {
                itemCount = ins.getDrugSensCheckItemList() == null ? 0 : ins.getDrugSensCheckItemList().size();
            }
        }
        return ins == null ? 0 : itemCount;
    }

    //多条目
    @Override
    public int getItemViewType(int position) {
        int result = -1;
        if (ins != null) {
            if (ins.getReportTypeValue() == ORDINARY_TYPE) { //普通条目
                result = ORDINARY_TYPE;
            } else if (ins.getReportTypeValue() == SMEAR_TYPE) { //涂片条目
                result = SMEAR_TYPE;
            } else if (ins.getReportTypeValue() == DRUGSENS_TYPE) { //药敏条目
                result = DRUGSENS_TYPE;
            }
        }
        return result;
    }

    //涂片条目的holder
    class SmeHolder extends RecyclerView.ViewHolder {
        TextView tvSmearName;
        TextView tvSmearResult;

        public SmeHolder(View itemView) {
            super(itemView);
            tvSmearName = (TextView) itemView.findViewById(R.id.tv_smear_name);
            tvSmearResult = (TextView) itemView.findViewById(R.id.tv_smear_result);
        }
    }

    //药敏条目的holder
    class DrugSensHolder extends RecyclerView.ViewHolder {
        TextView tvDurgName;
        RecyclerView rvChildDurd;

        public DrugSensHolder(View itemView) {
            super(itemView);
            tvDurgName = (TextView) itemView.findViewById(R.id.tv_durg_name);
            rvChildDurd = (RecyclerView) itemView.findViewById(R.id.rv_child_durg);
        }
    }


    //普通的holder
    class OrdHolder extends RecyclerView.ViewHolder {
        TextView tvCheckOutName;
        TextView tvCheckOutResult;
        TextView tvCheckConsult;

        public OrdHolder(View itemView) {
            super(itemView);
            tvCheckOutName = (TextView) itemView.findViewById(R.id.tv_checkout_name);
            tvCheckOutResult = (TextView) itemView.findViewById(R.id.tv_checkout_result);
            tvCheckConsult = (TextView) itemView.findViewById(R.id.tv_checkout_consult);
        }
    }

//    //获取到药敏头条目的postion
//    private int getDrugSenSize(List<InspectionDetailBean.DrugSensBean> listDrugSens, int i) {
//        int durgSenSize = 0;
//        for (; i > 0; i--) {
//            durgSenSize = durgSenSize + listDrugSens.get(i).getDrugSensResList().size();
//        }
//        return durgSenSize;
//    }
//
//    /**
//     * 获取药敏时 的条目总长度
//     *
//     * @return
//     */
//    private int getAllDrugsensSize(List<InspectionDetailBean.DrugSensBean> listDrugSens) {
//        if (listDrugSens != null && listDrugSens.size() > 0) {
//            int size = listDrugSens.size();
//            for (int i = 0; i < listDrugSens.size(); i++) {
//                size += listDrugSens.get(i).getDrugSensResList().size();
//            }
//            return size;
//        } else {
//            return 0;
//        }
//    }
}
