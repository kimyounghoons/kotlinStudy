package com.study.kotlin.kotlinstudy.models

/**
 * Created by younghoon on 2018-04-03.
 */
class User(idx : String, var name: String = "kim") : AbstractIdxModel(idx) //아래는 같은 방법
//class User : AbstractIdxModel {
//    var name: String
//
//    constructor(idx: String, name: String = "kim") : super(idx) {
//        this.name = name
//    }
//}