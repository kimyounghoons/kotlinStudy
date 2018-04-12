package com.study.kotlin.kotlinstudy.models

/**
 * Created by kimyounghoon on 2018-04-11.
 */
class GenericClass<T>(var genericValue: T) {
    fun get(): T {
        return genericValue
    }
}