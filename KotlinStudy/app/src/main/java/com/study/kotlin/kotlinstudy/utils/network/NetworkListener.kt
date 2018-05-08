package com.study.kotlin.kotlinstudy.utils.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by younghoon on 2018-05-08.
 */
open class NetworkListener {
    open class RetroCallBack<T>(var retroResopnseListener: RetroResopnseListener<T>, var retroErrorListener: RetroErrorListener) : Callback<T> {

        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            if (response.code() == 200) {
                retroResopnseListener.onResponse(response.body()!!)
            } else {
                retroErrorListener.onErrorResponse(response.code())
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            if (call.isCanceled) {
                Log.e(javaClass.toString(), "request 중단됨")
            } else {
                Log.e(javaClass.toString(), t.toString())
            }
        }
    }

    open class RetroResopnseListener<T> {
        open fun onResponse(response: T) {

        }
    }

    open class RetroErrorListener {
        open fun onErrorResponse(errorCode: Int) {

        }
    }
}