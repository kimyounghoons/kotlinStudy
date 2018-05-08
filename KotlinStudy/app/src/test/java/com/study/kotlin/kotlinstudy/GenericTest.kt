package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.models.GenericClass
import com.study.kotlin.kotlinstudy.models.Post
import com.study.kotlin.kotlinstudy.models.User
import org.junit.Test

/**
 * Created by kimyounghoon on 2018-04-11.
 */
class GenericTest {
    @Test
    fun GenericTest() {
        var genericClass: GenericClass<Post> = GenericClass(Post("123123", User("11")))
        val user = genericClass.genericValue.user
        System.out.println("유저 네임 :\t ${user.name}")
    }
}