package com.bw.library_base.presenter

import com.bw.library_base.model.IModel
import com.bw.library_base.view.IView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<M:IModel,V:IView> : IPresenter{


    protected lateinit var mModel:M
    protected lateinit var mView:V
    var compositedisposable:CompositeDisposable = CompositeDisposable()

    constructor(mModel: M, mView: V) {
        this.mModel = mModel
        this.mView = mView
    }

    fun addDisposable(disposable: Disposable){
        compositedisposable.add(disposable)
    }


    override fun destroy() {
        mModel.destroy()
        if (compositedisposable.size() > 0){
            compositedisposable.clear()
        }
    }
}