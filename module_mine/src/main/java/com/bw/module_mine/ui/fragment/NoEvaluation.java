package com.bw.module_mine.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.RegexUtils;
import com.bw.library_base.model.BaseModel;
import com.bw.library_base.presenter.BasePresenter;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_base.view.IView;
import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.cargo.CarGoDao;
import com.bw.library_common.router.cargo.CarGoDataBase;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.NoEvaluationAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class NoEvaluation extends BaseFragment<BasePresenter> {
    private RecyclerView noEvaluationRv;
    private CarGoDao carGoDao;
    private NoEvaluationAdapter noEvaluationAdapter;
    private List<CarGo> gos;



    @Override
    public int bindLayout() {
        return R.layout.noevaluation_layout;
    }

    @Override
    public void initView() {
        noEvaluationRv = (RecyclerView) getViewById(R.id.no_evaluation_rv);
        noEvaluationRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        carGoDao = CarGoDataBase.getCarGoDataBase(getActivity()).getCarGoDao();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        List<CarGo> all = carGoDao.findAll();
        gos = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isReceiving()){
                if (!all.get(i).isEvaluate()){
                    gos.add(all.get(i));
                }
            }
        }
        noEvaluationAdapter = new NoEvaluationAdapter(gos);
        noEvaluationRv.setAdapter(noEvaluationAdapter);
        noEvaluationAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.mine_noevaluation_item_evaluation){
                    Intent intent = new Intent(getActivity(), EvaluationActivity.class);
                    intent.putExtra("img",gos.get(position).getPic());
                    intent.putExtra("title",gos.get(position).getTitle());
                    intent.putExtra("id",gos.get(position).getId());
                    startActivity(intent);
                }
            }
        });
    }

    @Subscribe
    public void getMsg(String msg){
        if (msg.equals("335")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<CarGo> all1 = carGoDao.findAll();
                    List<CarGo> all2 = new ArrayList<>();
                    for (int i = 0; i < all1.size(); i++) {
                        if (all1.get(i).isReceiving()){
                            if (!all1.get(i).isEvaluate()){
                                all2.add(all1.get(i));
                            }
                        }
                    }
                    noEvaluationAdapter.getData().clear();
                    noEvaluationAdapter.getData().addAll(all2);
                    LoggerUtils.i(all2.size()+"333");
                    noEvaluationAdapter.notifyDataSetChanged();
                }
            }).start();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
