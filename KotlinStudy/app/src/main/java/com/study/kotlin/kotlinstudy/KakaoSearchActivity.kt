package com.study.kotlin.kotlinstudy

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.study.kotlin.kotlinstudy.databinding.ActivityKakaoSearchBinding
import com.study.kotlin.kotlinstudy.kakaosearch.KakaoSearchFragment

class KakaoSearchActivity : AppCompatActivity() {
    private lateinit var activitySearchActivityBinding : ActivityKakaoSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_kakao_search)
        fragmentManager.beginTransaction().replace(R.id.fragment_container, KakaoSearchFragment()).commitAllowingStateLoss()
    }
}