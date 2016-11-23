package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-9-9.
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<DepartmentBean> listDepartment = new ArrayList<>();
    private int type;

    private SparseBooleanArray mBooleanArray;

    private int mLastCheckedPosition = -1;

    public DepartmentAdapter(List<DepartmentBean> listDepartment,int type) {
        mContext = UtilApplication.getContextObject();
        this.inflater = LayoutInflater.from(mContext);
        this.listDepartment = listDepartment;
        this.type = type;
        mBooleanArray = new SparseBooleanArray(listDepartment.size());

    }

    public DepartmentAdapter() {
        mContext = UtilApplication.getContextObject();
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<DepartmentBean> listDepartment,int type){
        this.listDepartment = listDepartment;
        this.type = type;
        mBooleanArray = new SparseBooleanArray(listDepartment.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sa_hosptial_dapartment_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DepartmentBean department = listDepartment.get(position);
        String departmentName = department.getMzDeptName();
        departmentName = TextUtils.isEmpty(departmentName)?"":departmentName;

        //如果type== 2,就是右侧子科室列表,那么就采用交替变换条目背景色
        if (type == 2) {
            holder.departmentName.setText(departmentName + department.getDoctor().get(0).getDeptName());
            if (position % 2 == 0){
                holder.departmentName.setBackgroundResource(R.color.backgroundDepartmentUnselect);
            }else {
                holder.departmentName.setBackgroundResource(R.color.backgroundDepartmentUnselect_2);
            }
        } else { //type不等于2,
            holder.departmentName.setText(departmentName);
            if (mBooleanArray.get(position)) {
                holder.departmentName.setBackgroundResource(R.color.white);
            } else {
                holder.departmentName.setBackgroundResource(R.color.backgroundDepartmentLeft);
            }

        }
    }

    @Override
    public int getItemCount() {
        return listDepartment.size();
    }


    public void setItemChecked(int position) {
        mBooleanArray.put(position, true);
        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
        }

        mLastCheckedPosition = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView departmentName;   //科室名称

        public ViewHolder(View itemView) {
            super(itemView);
            departmentName = (TextView) itemView.findViewById(R.id.text_department_name);
        }
    }
}
