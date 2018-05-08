package com.study.kotlin.kotlinstudy

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.kotlin.kotlinstudy.models.Post
import com.study.kotlin.kotlinstudy.models.User
import org.junit.Test
import java.lang.reflect.Type


/**
 * Created by kimyounghoon on 2018-04-17.
 */
class GsonTest {

    @Test
    fun gsonTest() {
        val gson = Gson()
        val json = gson.toJson(Post("1", User("001", "younghoon")))
        System.out.println(json)

        var post = gson.fromJson<Post>(json, Post::class.java)
        System.out.println("postIdx : " + post.Idx + "\t userIdx : " + post.user.Idx + "\t username : " + post.user.Idx)
    }

    @Test
    fun gsonTopicDataClassTest() {
        //https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples  Gson User Guide 참고 할 수 있다 ㅎㅎ
        var arrayLists = arrayListOf(Account("kim", "1"), Account("young", "2"), Account("hoon", "3"))
        val gson = Gson()
        val json = gson.toJson(arrayLists)
        System.out.println(json)

        val collectionType: Type = object : TypeToken<ArrayList<Account>>() {}.type
        val lists = gson.fromJson<ArrayList<Account>>(json, collectionType)

        System.out.println(lists.forEach { account ->
            System.out.println("AccountId : " + account.id + "\t password : " + account.password)
        })
    }

    data class Account(val id: String, val password: String)
}