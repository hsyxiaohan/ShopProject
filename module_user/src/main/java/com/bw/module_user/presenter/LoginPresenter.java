package com.bw.module_user.presenter;

import androidx.annotation.NonNull;

import com.bw.library_base.presenter.BasePresenter;
import com.bw.module_user.bean.LoginAndRegBean;
import com.bw.module_user.contract.LoginContract;

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
public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.ILoginView> {

    public LoginPresenter(@NonNull LoginContract.ILoginModel mModel, @NonNull LoginContract.ILoginView mView) {
        super(mModel, mView);
    }

    public void login(LoginAndRegBean.DataBean dataBean){
        mModel.login(dataBean, new Observer<LoginAndRegBean>() {
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
