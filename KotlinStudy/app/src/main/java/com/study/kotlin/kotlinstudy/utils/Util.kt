package com.study.kotlin.kotlinstudy.utils

import android.os.Build
import com.study.kotlin.kotlinstudy.BuildConfig

/**
 * Created by kimyounghoon on 2018-04-05.
 */
class Util {
    companion object {
        fun hasOreo(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        }

        /*
        *  코틀린에서는 .equals 사용 안하고 == 사용
        *  Java Class 에서 kotlin static method 사용 하려면 @JvmStatic annotation 필요
        * */
        fun isDebugBuild(): Boolean {
            return BuildConfig.BUILD_TYPE == "debug"
        }
    }
}