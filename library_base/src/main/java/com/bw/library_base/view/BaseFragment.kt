package com.bw.library_base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bw.library_base.model.BaseModel
import com.bw.library_base.presenter.BasePresenter
import com.bw.library_base.presenter.IPresenter

abstract class BaseFragment<P:IPresenter> : Fragment(),IFragment,IView{

    lateinit var mPresenter: P
    lateinit var mView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(bindLayout(),container,false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun <V : View?> getViewById(id: Int): V {
        return mView.findViewById<V>(id)
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}