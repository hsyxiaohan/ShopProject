package com.bw.module_mine.presenter;

import androidx.annotation.NonNull;

import com.bw.library_base.presenter.BasePresenter;
import com.bw.module_mine.bean.LogisticsBean;
import com.bw.module_mine.contract.MineContract;
import com.bw.module_mine.model.MineModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public class MinePresenter extends BasePresenter<MineContract.ILogisticsModel, MineContract.ILogisticsView> {
    public MinePresenter(@NonNull MineContract.ILogisticsModel mModel, @NonNull MineContract.ILogisticsView mView) {
        super(mModel, mView);
    }

    public void getLogistics(){
        mModel.getLogistics(new Observer<LogisticsBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull LogisticsBean logisticsBean) {
                mView.initAdapter(logisticsBean.getData());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
