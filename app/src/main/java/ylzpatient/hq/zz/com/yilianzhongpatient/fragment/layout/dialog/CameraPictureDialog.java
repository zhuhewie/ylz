package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.event.ImageSelectorEvent;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.FileUtils;

/**
 * Created by Administrator on 2016-10-10.
 */
public class CameraPictureDialog extends SuperDialogFragment {
    @BindView(R.id.btn_picture)
    Button btnPicture;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_dismiss)
    Button btnDismiss;

    private static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 110;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private File mTmpFile;
    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_IMAGE = 2;
    private static final String KEY_TEMP_FILE = "key_temp_file";
    private static final String ARG_PATH_LIST = "arg_path_list";

    private ArrayList<String> mSelectPath;

    public static CameraPictureDialog newInstance(ArrayList<String> mSelectPath) {
        CameraPictureDialog frag = new CameraPictureDialog();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PATH_LIST, mSelectPath);
        //args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSelectPath = bundle.getStringArrayList(ARG_PATH_LIST);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_camera_picture, container);
        ButterKnife.bind(this, view);

        addClick();
        addData();

        return view;
    }

    private void addClick() {
        RxView.clicks(btnDismiss)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        RxView.clicks(btnCamera)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showCameraAction();
                        //dismiss();
                    }
                });
        RxView.clicks(btnPicture)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        pickImage();
                        dismiss();
                    }
                });
    }

    private void addData() {

    }

    /**
     * Open camera
     */
    private void showCameraAction() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale_write_storage),
                    REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                try {
                    mTmpFile = FileUtils.createTmpFile(getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mTmpFile != null && mTmpFile.exists()) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
                    this.startActivityForResult(intent, REQUEST_CAMERA);
                } else {
                    Toast.makeText(getActivity(), R.string.mis_error_image_not_exist, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.mis_msg_no_camera, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 权限
     *
     * @param permission
     * @param rationale
     * @param requestCode
     */
    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (shouldShowRequestPermissionRationale(permission)) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }
    }

    /**
     * 跳转到选择图片界面
     */
    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 8;
            MultiImageSelector selector = MultiImageSelector.create(getActivity());
//            selector.showCamera(false);// 是否显示相机. 默认为显示
            selector.count(maxNum); // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//            if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
//                selector.single();// 单选模式
//            } else {
//            }
            selector.multi(); //设置图库多选
            selector.origin(mSelectPath);
            selector.start(getActivity(), REQUEST_IMAGE);
        }
    }



    /**
     * 接收相机拍照的结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //mTmpFile = FileUtils.createTmpFile(getActivity());
            getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mTmpFile)));

            String s = mTmpFile.getAbsolutePath(); //File转换成String
            EventBus.getDefault().post(new ImageSelectorEvent(s));
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_TEMP_FILE, mTmpFile);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mTmpFile = (File) savedInstanceState.getSerializable(KEY_TEMP_FILE);
        }
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // 设置宽度为屏幕宽度的80%,高度包裹内容
        window.setLayout((int) (size.x * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);

        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        super.onResume();
    }


}
