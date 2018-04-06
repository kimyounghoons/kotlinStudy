package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.models.User
import org.junit.Test

/**
 * Created by kimyounghoon on 2018-04-06.
 */
class InheritTest {

    @Test
    fun getUserData() {
        val user = User("28")
        System.out.println("userIdx : " + user.Idx + "\t" + "user name: " + user.name)
        user.Idx = "29"
        user.name = "younghoons"
        System.out.println("userIdx : " + user.Idx + "\t" + "user name: " + user.name)
    }

}