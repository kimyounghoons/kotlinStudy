package com.study.kotlin.kotlinstudy.dao

import com.study.kotlin.kotlinstudy.helpers.RetrofitHelper
import com.study.kotlin.kotlinstudy.interfaces.retroservices.KakaoSearchDAOService
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener

/**
 * Created by younghoon on 2018-05-08.
 */
class KakaoSearchDAO {

    fun getImageSearch(keyword: String, page: Int?, size: Int?, responseListener: NetworkListener.RetroResopnseListener<ResponseImageSearch>, errorResponseListener: NetworkListener.RetroErrorListener) {
        val headers = HashMap<String, Any>()
        headers.put("Authorization", "KakaoAK " + RetrofitHelper.getAuthToken())

        val queries = HashMap<String, Any>()
        queries.put("query", keyword)
//        queries.put("sort","recency") // accuracy 정확순과 최신순으로 나뉨  default 정확순
//        if(page!=null) {
//            queries.put("page",1)
//        }
        RetrofitHelper.getRetrofit(RetrofitHelper.getFullUrl())
                .create(KakaoSearchDAOService::class.java)
                .getImageSearch(headers, queries)
                .enqueue(NetworkListener.RetroCallBack(responseListener, errorResponseListener))
    }
}