package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jakewharton.rxbinding.view.RxView;

import java.util.EnumMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;

import static android.content.Context.WINDOW_SERVICE;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;


/**
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ByAuthorizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ByAuthorizeFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_zxing)
    ImageView imgZxing;

    private BarcodeFormat format;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ByAuthorizeFragment.
     */
    public static ByAuthorizeFragment newInstance() {
        ByAuthorizeFragment fragment = new ByAuthorizeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_by_authorize, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        return view;
    }

    /**
     * 初始化
     */
    private void initView() {
        textViewName.setText("二维码");
    }

    /**
     * 界面点击点击事件
     */
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point displaySize = new Point();
            display.getSize(displaySize);
            int width = displaySize.x;
            int height = displaySize.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 7 / 8;

            format = BarcodeFormat.valueOf(BarcodeFormat.QR_CODE.toString());
            Bitmap bitmap = null;
            if (UserManager.getInstance().getCurrentUser() != null) {
                bitmap = encodeAsBitmap(UserManager.getInstance().getCurrentUser().getUserId(), format, smallerDimension);
            }

            if (bitmap == null) {
                Log.w(GeneralConfig.LOG_TAG, "创建二维码失败");
//                showErrorMessage(R.string.msg_encode_contents_failed);
                return;
            }
            imgZxing.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成二维码
     *
     * @return
     * @throws WriterException
     */
    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int dimension) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(contentsToEncode, format, dimension, dimension, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
