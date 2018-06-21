package com.study.kotlin.kotlinstudy.kakaosearch

import com.study.kotlin.kotlinstudy.data.Documents

class AdapterLoadSearchData(var page: Int = 1, var searchList: ArrayList<Documents>, var loading: Boolean = false) {

    fun initData() {
        searchList = ArrayList()
        page = 1
    }

    fun getItemCount(): Int {
        return searchList.size
    }

    fun isLoading(): Boolean {
        return loading
    }
}