package com.study.kotlin.kotlinstudy

import org.junit.Test


/**
 * 제네릭
 */
class Generics {

    @Test
    fun test1() {
        // 호출 시점에 타입이 정해지는 제네릭을 인자로 받음
        class User<T>(t: T) {
            var name = t
        }

        val user: User<String> = User<String>("yun")
        println(user.name)


        // 타입이 String 으로 정의
        class User2<String>(t: String) {
            var name = t
        }

    }

}
