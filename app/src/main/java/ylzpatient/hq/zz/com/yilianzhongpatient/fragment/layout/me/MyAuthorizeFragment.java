package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.meAdapter.MyAuthorizeAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.CancleAuthorizeRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AuthorizeResultBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.listener.OnItemClickListener;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.ListViewDecoration;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyAuthorizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 我的授权  子界面
 */
public class MyAuthorizeFragment extends SuperFragment {
    @BindView(R.id.rv_my_authorize)
    SwipeMenuRecyclerView rvMyAuthorize;
    @BindView(R.id.srl_my_auth)
    SwipeRefreshLayout srlMyAuth;


    private MyAuthorizeAdapter adapter;
    private LinearLayoutManager llManager;
    private UserIdRequestBean request;
    private List<AuthorizeResultBean> listAuthorize;
    private CancleAuthorizeRequestBean cancleAuthorizeRequest; //取消授权的请求实体

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyAuthorizeFragment.
     */
    public static MyAuthorizeFragment newInstance() {
        MyAuthorizeFragment fragment = new MyAuthorizeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_power, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData();
        return view;
    }

    private void initView() {
        request = new UserIdRequestBean();
        llManager = new LinearLayoutManager(getContext());
        adapter = new MyAuthorizeAdapter(adapter.SHOW_AUTHORIZE);
        listAuthorize = new ArrayList<>();
        cancleAuthorizeRequest = new CancleAuthorizeRequestBean();


        setSrlColor(srlMyAuth);
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        rvMyAuthorize.setLayoutManager(llManager);// 布局管理器。
        rvMyAuthorize.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rvMyAuthorize.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rvMyAuthorize.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rvMyAuthorize.setSwipeMenuCreator(swipeMenuCreator);  // 设置菜单创建器。

        rvMyAuthorize.setAdapter(adapter);
    }

    private void addClick() {
        // 设置菜单Item点击监听
        rvMyAuthorize.setSwipeMenuItemClickListener(menuItemClickListener);
        adapter.setOnItemClickListener(onItemClickListener);//条目点击事件
        srlMyAuth.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });

    }

    /**
     * 联网架子啊数据
     */
    private void loadingData() {
        if (!Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
            srlMyAuth.setRefreshing(true);
            request.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
            HttpMethods.getInstance().getToAuthorize(new Subscriber<ResquestResult<List<AuthorizeResultBean>>>() {
                @Override
                public void onCompleted() {
                    srlMyAuth.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    srlMyAuth.setRefreshing(false);
                    //获取
                    Snackbar.make(rvMyAuthorize, "获取我给与他人的授权出现异常", Snackbar.LENGTH_SHORT).show();
                    Log.e(GeneralConfig.LOG_TAG, "获取我给与他人的授权出现异常:" + e.toString());
                }

                @Override
                public void onNext(ResquestResult<List<AuthorizeResultBean>> listResult) {
                    if (TextUtils.isEmpty(listResult.ResultCode)) {
                        Snackbar.make(rvMyAuthorize, "获取我的授权成功", Snackbar.LENGTH_SHORT).show();
                        listAuthorize.clear();
                        listAuthorize.addAll(listResult.ResultData);
                        adapter.setData(listAuthorize);
                        Log.i(GeneralConfig.LOG_TAG, "获取我给与他人的授权成功" + listResult.ResultData.toString());
                    } else {
                        Snackbar.make(rvMyAuthorize, "获取我的授权数据失败" + listResult.ResultMsg, Snackbar.LENGTH_SHORT).show();
                        Log.i(GeneralConfig.LOG_TAG, "获取我给与他人的授权错误" + listResult.ResultMsg);
                    }
                }
            }, request);
        } else {
            Snackbar.make(rvMyAuthorize, "获取登录人信息失败,请重新登录", Snackbar.LENGTH_SHORT).show();
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
            {
                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.color.textDoctorGood)
                        .setText("取消授权")
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
            Toast.makeText(getContext(), "我是第" + position + "条。", Toast.LENGTH_SHORT).show();

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
            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION && menuPosition == 0) {
                cancleAuthorizeRequest.setAuthorizeId(listAuthorize.get(adapterPosition).getAuthorizeId());
                HttpMethods.getInstance().getCancleAuthResult(new Subscriber<ResquestResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(rvMyAuthorize, "取消授权失败,出现异常", Snackbar.LENGTH_SHORT).show();
                        Log.e(GeneralConfig.LOG_TAG, "取消授权失败,出现异常:" + e.toString());
                    }

                    @Override
                    public void onNext(ResquestResult result) {
                        if (TextUtils.isEmpty(result.ResultCode)){
                            Snackbar.make(rvMyAuthorize, "取消授权成功", Snackbar.LENGTH_SHORT).show();
                            Log.i(GeneralConfig.LOG_TAG, "取消授权成功");
                            listAuthorize.remove(adapterPosition);
                            adapter.notifyItemRemoved(adapterPosition);
                        } else {
                            Snackbar.make(rvMyAuthorize, "取消授权失败:" + result.ResultMsg, Snackbar.LENGTH_SHORT).show();
                            Log.i(GeneralConfig.LOG_TAG, "取消授权失败" + result.ResultMsg);

                        }
                    }
                },  cancleAuthorizeRequest);
            }

//            else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(getContext(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }
//
//            //  如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
//            if (menuPosition == 0) {// 删除按钮被点击。
////                list.remove(adapterPosition);
////                adapter.notifyItemRemoved(adapterPosition);
//            }
        }
    };

}
