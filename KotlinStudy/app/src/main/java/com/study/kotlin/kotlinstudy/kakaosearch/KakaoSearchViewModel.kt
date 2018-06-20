package com.study.kotlin.kotlinstudy.kakaosearch

import android.util.Log
import java.util.*

class KakaoSearchViewModel(var adapterLoadSearchData: AdapterLoadSearchData, var view: KakaoSearchContract.View): Observable() {
    fun search(searchText: String) {
        view.clearFocusEditText()
        view.hideKeyboard()
        adapterLoadSearchData.initData()
        adapterLoadSearchData.search(searchText, object : AdapterLoadSearchData.LoadSearchCallback {
            override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
                view.notifyItemRangeInserted(positionStart, itemCount)
            }
        })
    }

    fun loadMore(searchText: String) {
        if (!adapterLoadSearchData.isLoading()) {
            adapterLoadSearchData.search(searchText, object : AdapterLoadSearchData.LoadSearchCallback {
                override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
                    Log.d("notifyItemRangeInserted", "positionStart : $positionStart,itemCount :$itemCount")
                    view.notifyItemRangeInserted(positionStart, itemCount)
                }
            })
        }
    }

}