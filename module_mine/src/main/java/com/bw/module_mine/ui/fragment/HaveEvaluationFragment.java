package com.bw.module_mine.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.cargo.CarGoDao;
import com.bw.library_common.router.cargo.CarGoDataBase;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.HaveEvaluationAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
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
public class HaveEvaluationFragment extends BaseFragment {


    private RecyclerView haveEvaluationRv;
    private CarGoDao carGoDao;
    private HaveEvaluationAdapter haveEvaluationAdapter;


    @Override
    public int bindLayout() {
        return R.layout.haveevaluation_layout;
    }

    @Override
    public void initView() {
        haveEvaluationRv = (RecyclerView) getViewById(R.id.have_evaluation_rv);
        haveEvaluationRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        carGoDao = CarGoDataBase.getCarGoDataBase(getActivity()).getCarGoDao();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        List<CarGo> all = carGoDao.findAll();
        List<CarGo> carGos = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isReceiving()){
                if (all.get(i).isEvaluate()){
                    carGos.add(all.get(i));
                }
            }
        }
        haveEvaluationAdapter = new HaveEvaluationAdapter(carGos);
        haveEvaluationRv.setAdapter(haveEvaluationAdapter);
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
                            if (all1.get(i).isEvaluate()){
                                all2.add(all1.get(i));
                            }
                        }
                    }
                    haveEvaluationAdapter.getData().clear();
                    haveEvaluationAdapter.getData().addAll(all2);
                    LoggerUtils.i(all2.size()+"333");
                    haveEvaluationAdapter.notifyDataSetChanged();
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
