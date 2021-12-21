package com.bw.module_message

import com.alibaba.android.arouter.facade.annotation.Route
import com.bw.library_base.presenter.IPresenter
import com.bw.library_base.view.BaseFragment
import com.bw.library_common.router.router.ARouterFragmentPath

@Route(path = ARouterFragmentPath.Message.PAGER_MESSAGE)
class MessageFragment : BaseFragment<IPresenter>(){
    override fun bindLayout(): Int {
        return R.layout.message_fragment
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}