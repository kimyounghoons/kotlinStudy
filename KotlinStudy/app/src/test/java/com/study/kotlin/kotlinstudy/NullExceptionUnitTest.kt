package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.models.Post
import com.study.kotlin.kotlinstudy.models.User
import org.junit.Before
import org.junit.Test

/**
 * Created by younghoon on 2018-04-03.
 */
class NullExceptionUnitTest {
    lateinit var post: Post

    @Before
    fun setUp() {
        post = Post(User("123"))
    }

    @Test
    fun getUserIdx() {
        System.out.println(post.user.userIdx)
    }

    @Test
    fun elvis() {
        var nullableString: String? = null
        nullableString = nullableString ?: "널 가능 스트링"
        System.out.println(nullableString)
    }

    @Test
    fun safetyCallOperator() {
        System.out.println(post.user?.userIdx ?: "userIdx null")
    }

    @Test
    fun asOperator() {
        val a: String = "test"
        val b: Int? = a as? Int ?: 1
        val c: String? = b as? String ?: "123"
        System.out.println(c)
    }


    /*
    * user 가 null이 가능 하고 user 가 null 이 아님을 보증 할때 !! 을 사용
    * 이미 user 가 non null 을 보증하므로 지금은 !! 이 불필요 하다
    * */
    @Test
    fun nonnullAssersion() {
        val post: Post = Post(User("1333"))
        System.out.println(post.user!!.userIdx)
    }

}
