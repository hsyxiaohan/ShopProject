package com.bw.module_home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.router.ARouterActivityPath;
import com.bw.library_common.router.router.ARouterFragmentPath;
import com.bw.library_common.router.transformer.AccordionTransformer;
import com.bw.library_common.router.utils.NetWorkSpeedUtils;
import com.bw.library_common.router.utils.NetworkUtils;
import com.bw.library_common.router.utils.SpUtils;
import com.bw.library_common.router.widget.MyRecyclerView;
import com.bw.module_home.adapter.BannerAdapter;
import com.bw.module_home.adapter.BannerTwoAdapter;
import com.bw.module_home.adapter.GoodsAdapter;
import com.bw.module_home.adapter.SpecialAdapter;
import com.bw.module_home.bean.BannerBean;
import com.bw.module_home.bean.GoodsBean;
import com.bw.module_home.contract.GoodsContract;
import com.bw.module_home.model.GoodsModel;
import com.bw.module_home.presenter.GoodsPresenter;
import com.bw.module_home.ui.GoodsItemActivity;
import com.bw.module_home.ui.LiveActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.PageStyle;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<GoodsPresenter> implements GoodsContract.IGoodView, OnRefreshLoadMoreListener {


    private EditText homeSearch;
    private ImageView homeScan;
    private BannerViewPager bannerView;
    List<BannerBean> bannerList;
    private TextBannerView homeTextBannerView;

    private BannerViewPager bannerViewTwo;
    GoodsAdapter goodsAdapter;
    private int page = 0;
    private boolean isRefresh;

    //    PersonDao personDao;
    private Handler mHnadler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Toast.makeText(getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private RecyclerView homeGoodsRv;
    private SmartRefreshLayout homeRefresh;
    private LinearLayout homeQuery;
    private TextView homeLive;
    private MyRecyclerView homeRvOne;

    @Override
    public int bindLayout() {
        return R.layout.home_fragment;
    }


    @Override
    public void initView() {
        homeSearch = (EditText) getViewById(R.id.home_search);
        homeScan = (ImageView) getViewById(R.id.home_scan);
        bannerView = (BannerViewPager) getViewById(R.id.banner_view);
        homeRvOne = getViewById(R.id.home_rv_one);
        homeTextBannerView = (TextBannerView) getViewById(R.id.home_textBannerView);
        bannerViewTwo = (BannerViewPager) getViewById(R.id.banner_view_two);
        homeGoodsRv = (RecyclerView) getViewById(R.id.home_goods_rv);
        homeRefresh = getViewById(R.id.home_refresh);
        homeQuery = getViewById(R.id.home_query);
        homeRefresh.setOnRefreshLoadMoreListener(this);
        homeGoodsRv.setHasFixedSize(true);
        homeGoodsRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mPresenter = new GoodsPresenter(new GoodsModel(), this);
//        personDao = PersonDataBase.getPersonDataBase(getContext()).getPersonDao();
        //初始化头部banner
        bannerView.setLifecycleRegistry(getLifecycle())
                .setAdapter(new BannerAdapter())
                .setPageTransformer(new AccordionTransformer())
                .create();
        //初始化底部banner
        bannerViewTwo.setLifecycleRegistry(getLifecycle())//监听生命周期
                .setAdapter(new BannerTwoAdapter())
                .setIndicatorVisibility(View.INVISIBLE)//指示器是否可见
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)//设置页面样式
                .setRevealWidth(getResources().getDimensionPixelOffset(R.dimen.dp_40))//一屏多页模式下两边item漏出的宽度
                .create();
        homeRvOne.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        homeLive = getViewById(R.id.home_live);
    }

    @Override
    public void initData() {
        //轮播图
        bannerList = new ArrayList<>();
        BannerBean bannerBean = new BannerBean();
        bannerBean.setImagePath(R.mipmap.banner_one);
        bannerList.add(bannerBean);
        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setImagePath(R.mipmap.home_banner_two);
        bannerList.add(bannerBean2);
        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setImagePath(R.mipmap.home_banner_three);
        bannerList.add(bannerBean3);
        initBanner(bannerList);
        //文字轮播
        TextBanner();
        //折扣
        Special();
        //底部轮播图
        bannerTwo();
        homeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String connected = NetworkUtils.getNetworkOperatorName(getContext());
                Toast.makeText(getActivity(), connected + "", Toast.LENGTH_SHORT).show();
                new NetWorkSpeedUtils(getContext(), mHnadler).startShowNetSpeed();
            }
        });
        mPresenter.getGoods(1, 10);
        //直播推拉流
        WatchLive();
    }

    private void WatchLive() {
        homeLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LiveActivity.class));
            }
        });
    }


    private void TextBanner() {
        List<String> strings = new ArrayList<>();
        strings.add("新用户立领1000优惠券");
        strings.add("夏日炎炎，第一波福利还有30秒到达战场");
        homeTextBannerView.setDatas(strings);
    }

    //折扣
    private void Special() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg");
        strings.add("https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg");
        strings.add("https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg");
        strings.add("https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg");
        strings.add("https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg");
        strings.add("https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg");
        strings.add("https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg");
        SpecialAdapter specialAdapter = new SpecialAdapter(strings);
        homeRvOne.setAdapter(specialAdapter);
    }

    //底部banner
    private void bannerTwo() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2069038579,1558413647&fm=26&gp=0.jpg");
        strings.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1975162372,3010774958&fm=26&gp=0.jpg");
        strings.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1034894772,889898861&fm=26&gp=0.jpg");
        strings.add("https://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png");
        strings.add("https://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg");
        bannerViewTwo.refreshData(strings);
    }

    //测试room
    private void testRoom() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //添加
//                personDao.insertData(new Person(null, "小韩", "1110"));
                //查询所有
//                Looper.prepare();
//                List<Person> all = personDao.findAll();
//                LoggerUtils.i(all.size()+"");
//                Looper.loop();
            }
        }).start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    public void initBanner(@NonNull List<BannerBean> list) {
        bannerView.refreshData(list);
    }

    @Override
    public void initAdapter(List<GoodsBean.DataBean> dataBeans) {
        homeRefresh.finishRefresh();
        homeRefresh.finishLoadMore();
        homeQuery.setVisibility(View.VISIBLE);
        SpUtils instance = SpUtils.getInstance("login.xml", Context.MODE_PRIVATE, getContext());
        if (goodsAdapter == null) {
            goodsAdapter = new GoodsAdapter(dataBeans);
            homeGoodsRv.setAdapter(goodsAdapter);
            goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    boolean islogin = (boolean) instance.get("islogin", false);
                    if (islogin){
                        Intent intent = new Intent(getActivity(), GoodsItemActivity.class);
                        intent.putExtra("pic", dataBeans.get(position).getPictUrl());
                        intent.putExtra("text", dataBeans.get(position).getTitle());
                        intent.putExtra("price", dataBeans.get(position).getReservePrice());
                        startActivity(intent);
                    }else {
                        ARouter.getInstance().build(ARouterActivityPath.Login.PAGER_LOGIN).navigation();
                    }
                }
            });
        } else {
            if (isRefresh) {
                goodsAdapter.getData().clear();
            }
            goodsAdapter.getData().addAll(dataBeans);
            goodsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        isRefresh = false;
        page++;
        mPresenter.getGoods(page, 10);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        homeQuery.setVisibility(View.GONE);
        mHnadler.postDelayed(this::refresh, 3000);
    }

    public void refresh() {
        isRefresh = true;
        page = 1;
        mPresenter.getGoods(page, 10);
    }
}
