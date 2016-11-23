package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.meAdapter.CardManagerAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.DelectCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PatientIdCardsRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.listener.OnItemClickListener;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.isEmptyClazz;

/**
 * Created by Administrator on 2016-10-18.
 * 绑定就诊卡界面
 */

public class CardFragment extends SuperFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.rv_card)
    SwipeMenuRecyclerView rvCard;
    @BindView(R.id.srl_card)
    SwipeRefreshLayout srlCard;
    private CardManagerAdapter adapter;
    private List<CardBean> list;
    private PatientIdCardsRequest cardsRequest;
    private UserTestBean_2 user;

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_card_layout, container, false);

        ButterKnife.bind(this, v);

        initView();
        addClick();
        loadingData();
        return v;
    }

    private void addClick() {
        //返回上一页
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //右上角的图片点击事件
        RxView.clicks(imgFunction)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //进入新增就诊卡界面
                        switchFatherContent(CardFragment.this, BindCardFragment.newInstance());
                    }
                });
        //下拉刷新监听
        srlCard.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });

    }

    private void initView() {
        cardsRequest = new PatientIdCardsRequest();

        textViewName.setText("就诊卡管理");
        imgFunction.setImageResource(R.mipmap.btn_newly_increased_2x);
        list = new ArrayList<>();
//        loadingData();
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        rvCard.setLayoutManager(llManager);// 布局管理器。
//        rvCard.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rvCard.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        //rvCard.addItemDecoration(new ListViewDecoration());// 添加分割线。

        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        rvCard.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        rvCard.setSwipeMenuItemClickListener(menuItemClickListener);

        adapter = new CardManagerAdapter(list);
        adapter.setOnItemClickListener(onItemClickListener);
        rvCard.setAdapter(adapter);
        setSrlColor(srlCard);
    }

    /**
     * 从网络端加载就诊卡列表数据
     */
    private void loadingData() {
        if (!Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
            cardsRequest.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
            srlCard.setRefreshing(true);
            //联网加载就诊卡数据
            HttpMethods.getInstance().getBindCardList(new Subscriber<ResquestResult<List<CardBean>>>() {
                @Override
                public void onCompleted() {
                    srlCard.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    srlCard.setRefreshing(false);
                    try {
                        Snackbar.make(rvCard, "获取数据失败", 2000).show();
                        Log.i(GeneralConfig.LOG_TAG, "获取数据失败" + e.toString());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

                @Override
                public void onNext(ResquestResult<List<CardBean>> card) {
                    list.clear();
                    list.addAll(card.ResultData);
                    adapter.notifyDataSetChanged();
                    Log.i(GeneralConfig.LOG_TAG, "获取就诊卡:" + list.toString());
                }
            }, cardsRequest);
        } else {
            switchFatherContent(CardFragment.this,LoginFragment.newInstance());
        }

    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

//            // 添加左侧的，如果不添加，则左侧不会出现菜单。
//            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_action_add) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
//            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
//                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_delete)
//                        .setText("取消绑定") // 文字，还可以设置文字颜色，大小等。。
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_purple)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
//
                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.color.textDoctorGood)
                        .setText("取消绑定")
                        .setTextColor(Color.WHITE)
                        .setTextSize(20)
                        .setWidth(width * 2)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, long id) {
            //Toast.makeText(getContext(), "我是第" + position + "条。", Toast.LENGTH_SHORT).show();

        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, final int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                //Toast.makeText(getContext(), "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();

                final CardBean patientCard = list.get(adapterPosition); //条目患者
                if (!isEmptyClazz(patientCard) && !isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
                    Snackbar.make(rvCard, patientCard.getPatientName() + "解除绑定中,请稍等...", 5000).show();
                    DelectCardBean delectCard = new DelectCardBean(); //要删除的信息组装
                    delectCard.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
                    delectCard.setHospitalId(patientCard.getHospitalId());
                    delectCard.setPatientId(patientCard.getPatientId());
                    HttpMethods.getInstance().getCancleCardResult(new Subscriber<ResquestResult>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Snackbar.make(rvCard, patientCard.getPatientName() + "解除绑定异常", Snackbar.LENGTH_SHORT).show();
                            Log.e(GeneralConfig.LOG_TAG, patientCard.getPatientName() + "解除绑定出现异常:" + e.toString());
                        }

                        @Override
                        public void onNext(ResquestResult result) {
                            if (TextUtils.isEmpty(result.ResultCode)) {
                                Snackbar.make(rvCard, patientCard.getPatientName() + "解除绑定成功", Snackbar.LENGTH_SHORT).show();
                                Log.i(GeneralConfig.LOG_TAG, patientCard.getPatientName() + "解除绑定成功");
                                list.remove(adapterPosition);
                                adapter.notifyItemRemoved(adapterPosition);
                            } else {
                                Snackbar.make(rvCard, patientCard.getPatientName() + "解除绑定失败:" + result.ResultMsg, Snackbar.LENGTH_SHORT).show();
                                Log.i(GeneralConfig.LOG_TAG, patientCard.getPatientName() + "解除绑定失败:" + result.ResultMsg);
                            }
                        }
                    }, delectCard);
                }

            }
//            else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(getContext(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }

            //  如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
//            if (menuPosition == 0) {// 删除按钮被点击。
//                list.remove(adapterPosition);
//                adapter.notifyItemRemoved(adapterPosition);
//                Toast.makeText(getContext(),  menuPosition+"", Toast.LENGTH_SHORT).show();
//
//            }
        }
    };
}
