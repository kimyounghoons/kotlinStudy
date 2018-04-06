package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.helpers.TestHelper
import org.junit.Test

/**
 * Created by kimyounghoon on 2018-04-06.
 */
class MethodTest {
    @Test
    fun methodTest() {
        System.out.println(TestHelper.compareString("kim"))  //oldString 값 default 로 지정 해주었기 때문에 파라미터 하나만 넣어 줘도 가능하다. 안하면 compile error
        System.out.println(TestHelper.compareString("kim", "kim"))
    }

    @Test
    fun addTest(){
        System.out.println(TestHelper.add(1,2,3,4,5,6,7,8,9,10))
    }
}