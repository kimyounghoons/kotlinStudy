package com.study.kotlin.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.study.kotlin.kotlinstudy.kakaosearch.KakaoSearchFragment

class KakaoSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_search)
        fragmentManager.beginTransaction().replace(R.id.fragment_container, KakaoSearchFragment()).commitAllowingStateLoss()
    }
}