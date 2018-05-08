package com.study.kotlin.kotlinstudy.interfaces.retroservices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

/**
 * Created by younghoon on 2018-05-08.
 */
interface KakaoSearchDAOService{

    @GET("./")
    fun getFaqs(@HeaderMap headerMap: HashMap<String, Any>, @QueryMap fields: HashMap<String, Any>) : Call<ResponseFaqs>
}