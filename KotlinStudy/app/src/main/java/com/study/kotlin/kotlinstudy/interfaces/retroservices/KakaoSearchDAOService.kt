package com.study.kotlin.kotlinstudy.interfaces.retroservices

import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

/**
 * Created by younghoon on 2018-05-08.
 */
interface KakaoSearchDAOService{

    @GET("v2/search/image")
    fun getImageSearch(@HeaderMap headerMap: HashMap<String, Any>, @QueryMap queries: HashMap<String, Any>) : Call<ResponseImageSearch>

}