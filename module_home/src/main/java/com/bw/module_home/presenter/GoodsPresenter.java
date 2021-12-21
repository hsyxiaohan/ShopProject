package com.bw.module_home.presenter;

import androidx.annotation.NonNull;

import com.bw.library_base.presenter.BasePresenter;
import com.bw.module_home.bean.GoodsBean;
import com.bw.module_home.contract.GoodsContract;

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
public class GoodsPresenter extends BasePresenter<GoodsContract.IGoodsModel,GoodsContract.IGoodView> {
    public GoodsPresenter(@NonNull GoodsContract.IGoodsModel mModel, @NonNull GoodsContract.IGoodView mView) {
        super(mModel, mView);
    }

    public void getGoods(int page,int pagesize){
        mModel.getGoods(page, pagesize, new Observer<GoodsBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull GoodsBean goodsBean) {
                mView.initAdapter(goodsBean.getData());
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
