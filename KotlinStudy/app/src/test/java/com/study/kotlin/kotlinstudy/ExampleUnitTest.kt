package com.study.kotlin.kotlinstudy

import android.util.Log
import com.study.kotlin.kotlinstudy.models.Post
import com.study.kotlin.kotlinstudy.models.User
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var post : Post

    @Before
    fun setUp(){
        post = Post("144", User("123"))
    }

    @Test
    fun addition_isCorrect() {
        System.out.println(post.user.Idx)
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getUserIdx() {
        System.out.println(post.user.Idx)
    }

}
