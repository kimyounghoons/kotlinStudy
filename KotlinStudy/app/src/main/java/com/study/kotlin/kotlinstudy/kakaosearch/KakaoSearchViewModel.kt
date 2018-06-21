package com.study.kotlin.kotlinstudy.kakaosearch

import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener
import java.util.*

class KakaoSearchViewModel(var adapterLoadSearchData: AdapterLoadSearchData) : Observable() {

    fun search(keyword: String) {
        adapterLoadSearchData.initData()
        performSearch(keyword)
    }

    fun loadMore(keyword: String) {
        if (!adapterLoadSearchData.isLoading()) {
            performSearch(keyword)
        }
    }

    fun performSearch(keyword: String) {
        adapterLoadSearchData.loading =true
        searchNotifyObservers()

        KakaoSearchDAO().getImageSearch(keyword, adapterLoadSearchData.page, 10, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                super.onResponse(response)
                adapterLoadSearchData.page += 1
                for (document: Documents in response.documents) {
                    adapterLoadSearchData.searchList.add(document)
                    adapterLoadSearchData.loading = false
                    searchNotifyObservers()
                }
//                loadSearchCallback.notifyItemRangeInserted(adapterLoadSearchData.searchList.size, response.documents.size)

            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
                adapterLoadSearchData.loading = false
            searchNotifyObservers()
            }
        })
    }

    fun searchNotifyObservers(){
        setChanged()
        notifyObservers()
    }
}