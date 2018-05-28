package com.study.kotlin.kotlinstudy

/**
 * Created by yun on 2018. 5. 24..
 */
interface ItemListener {
    fun onItemClick()

    fun onAddItemClick()

    fun onRemoveItem(count: Int)
}