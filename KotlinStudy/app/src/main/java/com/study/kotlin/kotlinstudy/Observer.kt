package com.study.kotlin.kotlinstudy

/**
 * Created by kimyounghoon on 2018-04-12.
 */
interface Observer {
    fun update(o: Observable?, arg: Any?)
}