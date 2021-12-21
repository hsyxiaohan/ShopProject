package com.bw.module_classify.model;

import com.bw.library_network.RetrofitManager;
import com.bw.module_classify.bean.Api;
import com.bw.module_classify.bean.ClassGoodsBean;
import com.bw.module_classify.bean.ClassTextBean;
import com.bw.module_classify.contract.ClassContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
public class ClassModel implements ClassContract.ITextModel {
    @Override
    public void destroy() {

    }

    @Override
    public void getText(Observer<ClassTextBean> observer) {
        RetrofitManager.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getText()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void getGoods(String keyword, String type, Observer<ClassGoodsBean> observer) {
        RetrofitManager.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getGoods(keyword, type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
