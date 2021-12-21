package com.bw.module_main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.library_base.presenter.IPresenter
import com.bw.library_base.view.BaseActivity
import com.bw.library_common.router.router.ARouterActivityPath
import com.bw.library_common.router.router.ARouterFragmentPath
import com.bw.library_common.router.utils.LoggerUtils
import com.bw.library_common.router.utils.SpUtils
import com.chaychan.library.BottomBarItem
import com.chaychan.library.BottomBarLayout
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@Route(path = ARouterActivityPath.Main.PAGER_MAIN)
class MainActivity : BaseActivity<IPresenter>(),BottomBarLayout.OnItemSelectedListener {

     var HomeFragment:Fragment? = null
     var ClassifyFragment:Fragment? = null
     var ShopFragment:Fragment? = null
     var MessageFragment:Fragment? = null
     var MineFragment:Fragment? = null
    private val des = arrayOf("在这里\n你可以听到周围人的心声", "在这里\nTA会在下一秒遇见你", "在这里\n不再错过可以改变你一生的人")


    override fun bindLayout(): Int {
        return R.layout.activity_main
    }


    companion object{
        fun start(context: Context){
            SpUtils.getInstance("isFirst", MODE_PRIVATE,context).put("isfirst",false);
            context.startActivity(Intent(context,MainActivity::class.java))
        }
    }

    @Subscribe
    fun getEvent(num:String) {
        main_bbl.currentItem = num.toInt()
        EventBus.getDefault().postSticky("111")
    }


    override fun initView() {
        main_bbl.setOnItemSelectedListener(this)
        ImmersionBar.with(this)
            .statusBarColor("#00a9f4")
            .navigationBarColor(R.color.black)
            .fitsSystemWindows(true)
            .autoDarkModeEnable(true)
            .init()
        main_bbl.currentItem = 0
        EventBus.getDefault().register(this)
    }

    override fun initData() {
        Log.i("TAG", "initData: "+11111)
    }



    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    fun hideFragment(transaction: FragmentTransaction){
        if (HomeFragment != null){
            transaction.hide(HomeFragment!!)
        }
        if (ClassifyFragment != null){
            transaction.hide(ClassifyFragment!!)
        }
        if (ShopFragment != null){
            transaction.hide(ShopFragment!!)
        }
        if (MessageFragment != null){
            transaction.hide(MessageFragment!!)
        }
        if (MineFragment != null){
            transaction.hide(MineFragment!!)
        }
    }

    override fun onItemSelected(bottomBarItem: BottomBarItem?, previousPosition: Int, currentPosition: Int) {
        var Transaction = supportFragmentManager.beginTransaction()
        when(currentPosition){
            0 -> {
                hideFragment(Transaction)
                if (HomeFragment == null){
                    HomeFragment = ARouter.getInstance().build(ARouterFragmentPath.Home.PAGER_HOME).navigation() as Fragment?
                    HomeFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                HomeFragment?.let { Transaction.show(it) }
                Transaction.commit()
            }
            1 -> {
                hideFragment(Transaction)
                if (ClassifyFragment == null){
                    ClassifyFragment = ARouter.getInstance().build(ARouterFragmentPath.Classify.PAGER_CLASSIFY).navigation() as Fragment?
                    ClassifyFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                ClassifyFragment?.let { Transaction.show(it) }
                Transaction.commit()
            }
            2 -> {
                hideFragment(Transaction)
                if (ShopFragment == null){
                    ShopFragment = ARouter.getInstance().build(ARouterFragmentPath.Shopping.PAGER_SHOP).navigation() as Fragment?
                    ShopFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                ShopFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()
            }
            3 -> {
                hideFragment(Transaction)
                if (MessageFragment == null){
                    MessageFragment = ARouter.getInstance().build(ARouterFragmentPath.Message.PAGER_MESSAGE).navigation() as Fragment?
                    MessageFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                MessageFragment?.let { Transaction.show(it) }
                Transaction.commit()
            }
            4 -> {
                hideFragment(Transaction)
                if (MineFragment == null){
                    MineFragment = ARouter.getInstance().build(ARouterFragmentPath.Mine.PAGER_MINE).navigation() as Fragment?
                    MineFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                MineFragment?.let { Transaction.show(it) }
                Transaction.commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}