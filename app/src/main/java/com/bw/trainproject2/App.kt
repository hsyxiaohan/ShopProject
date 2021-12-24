package com.bw.trainproject2

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.rtmp.TXLiveBase
import io.rong.imkit.RongIM


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this);
        val licenceURL = "https://license.vod2.myqcloud.com/license/v2/1307602005_1/v_cube.license" // 获取到的 licence url

        val licenceKey = "451842c6465b681860cc36d342e2a8e7" // 获取到的 licence key

        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey)
        val appKey = "vnroth0kvoako"
        RongIM.init(this, appKey)
    }

}