package com.bw.library_base.view

import android.view.View

/**
 * lx10.15
 * Date:2021/10/15
 * Time:10:34
 * author:HanXiaoYang
 * Describe:
 */
interface IFragment : IActivity{

    fun <V : View?> getViewById(id: Int): V





}