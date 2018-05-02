@file:JvmName("SearchApiKt")

package com.androidhuman.example.simplegithub.api

import android.content.Context
import com.study.kotlin.kotlinstudy.api.SearchApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

fun searchApi(context: Context): SearchApi = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SearchApi::class.java)


private val _timeout: Long = 5000

fun createOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(_timeout, TimeUnit.MILLISECONDS)
        .writeTimeout(_timeout, TimeUnit.MILLISECONDS)
        .readTimeout(_timeout, TimeUnit.MILLISECONDS)
        .addInterceptor(HeaderSettingInterceptor())
        .build()

private class HeaderSettingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val chainRequest = chain.request()

        val request = chainRequest.newBuilder().apply {
            addHeader("Authorization", "KakaoAK 50e04803909cff9da49abb7c08254186")
        }.build()

        return chain.proceed(request)
    }
}


