package com.study.kotlin.kotlinstudy.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by yun on 2018. 5. 23..
 */
class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabNames: ArrayList<String>
    private val fragments: ArrayList<Fragment>

    init {
        tabNames = ArrayList()
        fragments = ArrayList()
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        tabNames.add(title)
        fragments.add(fragment)
    }

}