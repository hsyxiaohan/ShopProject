package com.bw.module_user.presenter;

import androidx.annotation.NonNull;

import com.bw.library_base.presenter.BasePresenter;
import com.bw.module_user.bean.LoginAndRegBean;
import com.bw.module_user.contract.RegContract;

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
public class RegPresenter extends BasePresenter<RegContract.IRegModel,RegContract.IRegView> {
    public RegPresenter(@NonNull RegContract.IRegModel mModel, @NonNull RegContract.IRegView mView) {
        super(mModel, mView);
    }

    public void reg(LoginAndRegBean.DataBean dataBean){
        mModel.Reg(dataBean, new Observer<LoginAndRegBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull LoginAndRegBean loginAndRegBean) {
                mView.showMsg(loginAndRegBean);
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
