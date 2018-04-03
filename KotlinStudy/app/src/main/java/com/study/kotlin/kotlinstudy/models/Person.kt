package com.study.kotlin.kotlinstudy.models

/**
 * Created by kimyounghoon on 2018-04-03.
 */
class Person (name : String , age : Int){
    var name: String = name
    set(value) {
        field = "person name : "+value
    }

    var age: Int = age
    set(value) {
        field  = value +10
    }
}