package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.utils.Util
import org.junit.Test

/**
 * Created by kimyounghoon on 2018-04-05.
 */
class StaticUnitTest {

    @Test
    fun isDebugBuild() {
        System.out.println(Util.isDebugBuild())
    }

    @Test
    fun hasOreo(){
        System.out.println(Util.hasOreo())
    }

}