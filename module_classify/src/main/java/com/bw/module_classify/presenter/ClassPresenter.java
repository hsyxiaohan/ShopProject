package com.bw.module_classify.presenter;

import androidx.annotation.NonNull;

import com.bw.library_base.presenter.BasePresenter;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_classify.bean.ClassGoodsBean;
import com.bw.module_classify.bean.ClassTextBean;
import com.bw.module_classify.contract.ClassContract;

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
public class ClassPresenter extends BasePresenter<ClassContract.ITextModel,ClassContract.ITextView> {
    public ClassPresenter(@NonNull ClassContract.ITextModel mModel, @NonNull ClassContract.ITextView mView) {
        super(mModel, mView);
    }

    public void getText(){
        mModel.getText(new Observer<ClassTextBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull ClassTextBean classTextBean) {
                mView.initTextAdapter(classTextBean.getData());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getGoods(String keyword,String type){
        mModel.getGoods(keyword, type, new Observer<ClassGoodsBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull ClassGoodsBean classGoodsBean) {
                mView.initGoodsAdapter(classGoodsBean.getData());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LoggerUtils.i(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
