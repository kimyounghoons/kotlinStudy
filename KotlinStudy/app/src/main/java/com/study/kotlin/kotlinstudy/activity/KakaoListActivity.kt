package com.study.kotlin.kotlinstudy.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.PagerAdapter
import com.study.kotlin.kotlinstudy.fragments.KakaoGridListFragment
import com.study.kotlin.kotlinstudy.fragments.KakaoNormalListFragment
import kotlinx.android.synthetic.main.activity_kakao_list.*

class KakaoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_list)

        val pageAdapter = PagerAdapter(supportFragmentManager)
        pageAdapter.addFragment(KakaoGridListFragment(), "Grid")
        pageAdapter.addFragment(KakaoNormalListFragment(), "Normal")

        view_pager.adapter = pageAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}