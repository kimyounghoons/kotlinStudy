package com.study.kotlin.kotlinstudy.data

/**
 * Created by yun on 2018. 4. 20..
 */
data class Documents(
        val collection: String,
        val thumbnail_url: String,
        val image_url: String,
        val width: Integer,
        val height: Integer,
        val display_sitename: String,
        val doc_url: String,
        val dateTime: String
)

