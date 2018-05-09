package com.study.kotlin.kotlinstudy.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by younghoon on 2018-05-08.
 */
class RetrofitHelper {
    companion object {
        fun getRetrofit(url: String): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build()
        }
        fun getAuthToken(): String{
            return "61cff82b52c43150874fbe5bae9ef0f5"
        }

        fun getFullUrl() : String{
            return "https://dapi.kakao.com/"
        }
    }
}