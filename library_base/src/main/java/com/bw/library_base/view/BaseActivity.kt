package com.bw.library_base.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bw.library_base.presenter.IPresenter

abstract class BaseActivity<P:IPresenter> : AppCompatActivity(),IActivity,IView {

    lateinit var mPresenter:P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout())
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}