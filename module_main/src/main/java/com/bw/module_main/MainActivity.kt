package com.bw.module_main

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import org.greenrobot.eventbus.ThreadMode

@Route(path = ARouterActivityPath.Main.PAGER_MAIN)
class MainActivity : BaseActivity<IPresenter>(),BottomBarLayout.OnItemSelectedListener {

     var HomeFragment:Fragment? = null
     var ClassifyFragment:Fragment? = null
     var ShopFragment:Fragment? = null
     var MessageFragment:Fragment? = null
     var MineFragment:Fragment? = null
    var isLoad:Boolean = true


    override fun bindLayout(): Int {
        return R.layout.activity_main
    }


    companion object{
        fun start(context: Context){
            SpUtils.getInstance("isFirst", MODE_PRIVATE,context).put("isfirst",false);
            context.startActivity(Intent(context,MainActivity::class.java))
        }

        private const val TAG = "MainActivity"
    }

    @Subscribe
    fun getEvent(num:String) {
        if(num.equals("2")){
            LoggerUtils.i(num)
            main_bbl.currentItem = num.toInt()
            EventBus.getDefault().postSticky("1111")
            isLoad = false
        }
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
        var instance = SpUtils.getInstance("login.xml", MODE_PRIVATE, this)
        when(currentPosition){
            0 -> {
                hideFragment(Transaction)
                if (HomeFragment == null){
                    HomeFragment = ARouter.getInstance().build(ARouterFragmentPath.Home.PAGER_HOME).navigation() as Fragment?
                    HomeFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                HomeFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()
            }
            1 -> {
                hideFragment(Transaction)
                if (ClassifyFragment == null){
                    ClassifyFragment = ARouter.getInstance().build(ARouterFragmentPath.Classify.PAGER_CLASSIFY).navigation() as Fragment?
                    ClassifyFragment?.let { Transaction.add(R.id.main_fl, it) }
                }
                ClassifyFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()
            }
            2 -> {
                hideFragment(Transaction)
                if (ShopFragment == null){
                    if (instance.get("islogin",false) as Boolean){
                        ShopFragment = ARouter.getInstance().build(ARouterFragmentPath.Shopping.PAGER_SHOP).navigation() as Fragment?
                        ShopFragment?.let { Transaction.add(R.id.main_fl, it) }
                    }else{
                        ARouter.getInstance().build(ARouterActivityPath.Login.PAGER_LOGIN).navigation()
                    }
                }
                ShopFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()
            }
            3 -> {
                hideFragment(Transaction)
                if (MessageFragment == null){
                    if (instance.get("islogin", false) as Boolean){
                        MessageFragment = ARouter.getInstance().build(ARouterFragmentPath.Message.PAGER_MESSAGE).navigation() as Fragment?
                        MessageFragment?.let { Transaction.add(R.id.main_fl, it) }
                    }else{
                        ARouter.getInstance().build(ARouterActivityPath.Login.PAGER_LOGIN).navigation()
                    }
                }
                MessageFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()
            }
            4 -> {
                hideFragment(Transaction)
                if (MineFragment == null){
                    if (instance.get("islogin", false) as Boolean){
                        MineFragment = ARouter.getInstance().build(ARouterFragmentPath.Mine.PAGER_MINE).navigation() as Fragment?
                        MineFragment?.let { Transaction.add(R.id.main_fl, it) }

                    }else{
                        ARouter.getInstance().build(ARouterActivityPath.Login.PAGER_LOGIN).navigation()
                    }
                }
                MineFragment?.let { Transaction.show(it) }
                Transaction.commitAllowingStateLoss()

            }
        }
    }



    override fun onResume() {
        super.onResume()
        var instance = SpUtils.getInstance("login.xml", MODE_PRIVATE, this)
        var islogin = instance.get("islogin",false) as Boolean
        if (isLoad && !islogin){
            main_bbl.currentItem = 0
        }else{
            isLoad = true
        }
        LoggerUtils.i("resume")
    }

    override fun onRestart() {
        super.onRestart()
        LoggerUtils.i("restart")
    }

    override fun onStart() {
        super.onStart()
        LoggerUtils.i("start")
    }


    
    

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        Log.i(TAG, "onDestroy: ")
    }
}