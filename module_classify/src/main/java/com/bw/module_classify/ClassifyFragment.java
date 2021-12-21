package com.bw.module_classify;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.router.ARouterFragmentPath;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_classify.adapter.ClassGoodsAdapter;
import com.bw.module_classify.adapter.ClassTextAdapter;
import com.bw.module_classify.adapter.MyDividerItemDecoration;
import com.bw.module_classify.bean.ClassGoodsBean;
import com.bw.module_classify.bean.ClassTextBean;
import com.bw.module_classify.contract.ClassContract;
import com.bw.module_classify.model.ClassModel;
import com.bw.module_classify.presenter.ClassPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
@Route(path = ARouterFragmentPath.Classify.PAGER_CLASSIFY)
public class ClassifyFragment extends BaseFragment<ClassPresenter> implements ClassContract.ITextView {
    private Toolbar classToolbar;
    private RecyclerView classRvOne;
    private RecyclerView classRvTwo;// 两次点击按钮之间的点击间隔
    private  final int MIN_CLICK_DELAY_TIME = 1000;
    private  long lastClickTime;


    @Override
    public int bindLayout() {
        return R.layout.classify_fragment;
    }

    @Override
    public void initView() {
        classToolbar = (Toolbar) getViewById(R.id.class_toolbar);
        getActivity().setActionBar(classToolbar);
        classRvOne = (RecyclerView) getViewById(R.id.class_rv_one);
        classRvTwo = (RecyclerView) getViewById(R.id.class_rv_two);
        classRvTwo.setHasFixedSize(true);
        classRvTwo.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        mPresenter = new ClassPresenter(new ClassModel(),this);
//        classRvOne.setHasFixedSize(true);
        classRvOne.setLayoutManager(new LinearLayoutManager(getActivity()));
//        classRvOne.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.getText();
        mPresenter.getGoods("女装/女士精品","女装/女士精品");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initTextAdapter(List<ClassTextBean.DataBean> dataBeans) {
        ClassTextAdapter classTextAdapter = new ClassTextAdapter(dataBeans);
        classRvOne.setAdapter(classTextAdapter);

        classTextAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                for (int i = 0; i < dataBeans.size(); i++) {
                    View viewByPosition = adapter.getViewByPosition(i, R.id.text_item);
                    assert viewByPosition != null;
                    TextView viewById = viewByPosition.findViewById(R.id.text_tv);
                    viewById.setTextColor(Color.GRAY);
                    if (i == position){
                        View nowView = adapter.getViewByPosition(i, R.id.text_item);
                        assert nowView != null;
                        TextView viewById1 = nowView.findViewById(R.id.text_tv);
                        viewById1.setTextColor(Color.RED);
                    }
                }
                boolean fastClick = isFastClick();
                if (fastClick){
                    mPresenter.getGoods(dataBeans.get(position).getCategory_name(),dataBeans.get(position).getCategory_name());
                }else {

                }
            }
        });
    }

    @Override
    public void initGoodsAdapter(List<ClassGoodsBean.DataBean> dataBeans) {
        LoggerUtils.i(dataBeans.size()+"");
        ClassGoodsAdapter classGoodsAdapter = new ClassGoodsAdapter(dataBeans);
        classRvTwo.setAdapter(classGoodsAdapter);
    }


    public  boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;

        return flag;
    }

}
