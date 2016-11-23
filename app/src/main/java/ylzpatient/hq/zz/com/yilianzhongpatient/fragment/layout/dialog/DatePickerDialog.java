package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DatePickerEvent;

/**
 * Created by Administrator on 2016-9-23.
 */
public class DatePickerDialog extends SuperDialogFragment implements android.app.DatePickerDialog.OnDateSetListener{
    private String title ;
    TextView tv;
    Date chosenDate;//选中的日期

    public static DatePickerDialog newInstance(String title) {
        DatePickerDialog frag = new DatePickerDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            //hospital = bundle.getParcelable(ARG_HOSPITAL);
            title = bundle.getString("title");
        }
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog dpd = new android.app.DatePickerDialog(getActivity(), R.style.MyDialogTheme, this, year, month, day);
        dpd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dpd.set
        long min = new Date().getTime();
//        BigInteger bi = (BigInteger)(30*24*60*60*1000);
//        BigInteger max = BigInteger.valueOf(min) + bi;
        c.add(Calendar.DAY_OF_MONTH, 30);        //在日历的现在的时间上,加上days天后的日期
        long max = c.getTimeInMillis();

        dpd.getDatePicker().setMinDate(min); //设置最小选择日期
        dpd.getDatePicker().setMaxDate(max); //设置最大选择日期
        tv = new TextView(getActivity());

        // Create a TextView programmatically
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setPadding(10, 10, 10, 10);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tv.setText("选择" + title);
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setBackgroundColor(Color.parseColor("#2AD8C1"));

        dpd.setCustomTitle(tv);
//        dpd.setButton(DialogInterface.BUTTON_POSITIVE, "w确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(),"点击了确定",Toast.LENGTH_SHORT).show();
//                //Snackbar.make(tv,"点击了确定",3000).show();
//                EventBus.getDefault().post(new DatePickerEvent(chosenDate,title));
//            }
//        });
//        dpd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //Toast.makeText(getContext(),"点击了取消",Toast.LENGTH_SHORT).show();
//
//            }
//        });
        return dpd;
        //不同主题样式的日期选择
//        return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,this, year, month, day);
//        return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);
//        return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, year, month, day);
//        return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
//        return new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL, this, year, month, day);
//        return new DatePickerDialog(getActivity(), R.style.DatePickerDialog, this, year, month, day);

    }



    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        // Do something with the chosen date
//        Toast.makeText(getContext(),"选择了日期" + year+month+day,Toast.LENGTH_SHORT).show();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        chosenDate = cal.getTime();
        EventBus.getDefault().post(new DatePickerEvent(chosenDate,title));

    }


}
