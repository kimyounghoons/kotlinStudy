package com.study.kotlin.kotlinstudy.kakaosearch

import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener

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

    fun search(keyworkd: String, loadSearchCallback: LoadSearchCallback) {
        loading = true
        KakaoSearchDAO().getImageSearch(keyworkd, page, 10, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                super.onResponse(response)
                page += 1
                for (document: Documents in response.documents) {
                    searchList.add(document)
                }
                loadSearchCallback.notifyItemRangeInserted(searchList.size, response.documents.size)
                loading = false
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
                loading = false
            }
        })
    }

    interface LoadSearchCallback {
        fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    }
}