package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.models.Post
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by kimyounghoon on 2018-04-12.
 */
object ModelManager {  //singleton class
    private var posts: ConcurrentHashMap<String, Post>? = null

    init {
        posts = ConcurrentHashMap()
    }

    fun putPost(post: Post) {
        posts?.put(post.Idx, post)
    }

    fun getPost(postIdx: String): Post? {
        return posts?.get(postIdx)
    }

}