package com.study.kotlin.kotlinstudy.api

import com.androidhuman.example.simplegithub.api.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yun on 2018. 4. 20..
 */
interface SearchApi {
    @GET("v2/search/image")
    fun search(
            @Query("query") query: String,
            @Query("page") page: Int = 1):  Call<SearchResponse>

}