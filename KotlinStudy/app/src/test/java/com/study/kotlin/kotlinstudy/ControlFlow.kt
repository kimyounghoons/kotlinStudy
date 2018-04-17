package com.study.kotlin.kotlinstudy

import org.junit.Test


/**
 * 흐름제어
 * if-else
 * when
 * while
 * for
 *
 * Ranges
 * 범위
 */
class ControlFlow {

    /**
     * if-else
     */
    @Test
    fun test1() {
        val reviewCnt: Int = 54
        val reviewScore: String

        if (reviewCnt in 1..24) { // reviewCnt >= 0 && reviewCnt < 25
            reviewScore = "1점"
        } else if (reviewCnt in 25..49) { //reviewCnt >= 25 && reviewCnt < 50
            reviewScore = "2점"
        } else if (reviewCnt in 50..74) { //reviewCnt >= 50 && reviewCnt < 75
            reviewScore = "3점"
        } else if (reviewCnt in 75..99) { //reviewCnt >= 75 && reviewCnt < 100
            reviewScore = "4점"
        } else if (reviewCnt == 100) {
            reviewScore = "5점"
        } else {
            reviewScore = "0점"
        }
        print(reviewScore)
    }

    @Test
    fun test2() {
        val reviewCnt: Int = 54

        // if-else 값을 반환할 수 있다.
        val reviewScore: String = if (reviewCnt in 1..24) {
            "1점"
        } else if (reviewCnt in 25..49) {
            "2점"
        } else if (reviewCnt in 50..74) {
            "3점"
        } else if (reviewCnt in 75..99) {
            "4점"
        } else if (reviewCnt == 100) {
            "5점"
        } else {
            "0점"
        }
        print(reviewScore)
    }

    @Test
    fun test3() {
        val reviewCnt: Int = 1

        // 반환이 가능 하기에, 자바의 삼항 연산자 (ternary operator) 대체 가능
        // reviewCnt > 0 ?  "리뷰 있음" : "리뷰 없음"
        val message: String = if (reviewCnt > 0) "리뷰 있음" else "리뷰 없음"
        print(message)
    }

    /**
     * when
     *
     * java 의 switch 대체
     */
    @Test
    fun test4() {
        val reviewCnt: Int = 1
        val message: String

        when (reviewCnt) {
            0 -> message = "리뷰 없음"
            1, 2 -> message = "$reviewCnt 개의 리뷰 있음"      // 여러 case 는 쉼표로 구분
            in 2..100 -> message = "$reviewCnt 개의 리뷰 있음"
            else -> message = "리뷰 없음" // default 대신 else 로 표현
        }
        print(message)
    }

    @Test
    fun test5() {
        val reviewCnt: Int = 3

        // when 값을 반환할 수 있다.
        val message: String = when (reviewCnt) {
            0 -> "리뷰 없음"
            1, 2 -> "$reviewCnt 개의 리뷰 있음"
            in 2..100 -> "$reviewCnt 개의 리뷰 있음"
            else -> "리뷰 없음"
        }
        print(message)
    }

    @Test
    fun test6() {
        val str: String = "kotlin"

        when (str) {
            is String -> {
                if (str.startsWith("j")) {
                    print("java")
                } else if (str.startsWith("k")) {
                    print("kotlin")
                }
            }

        }
    }

    /**
     * while
     */
    @Test
    fun test7() {
        var cnt: Int = 11

        while (cnt < 10) {
            print("count=$cnt")
            cnt++
        }

        cnt = 11
        println()

        do {
            print("count=$cnt")
            cnt++
        } while (cnt < 10)
    }


    /**
     * for
     *
     * 코틀린은 for-each 형태만 지원.
     */
    @Test
    fun test8() {
        val names: List<String> = listOf("android", "java", "kotlin")
        for (name in names) {
            println(name)
        }
    }

    /**
     * Ranges
     */
    @Test
    fun test9() {
        val range: IntRange = 0..9 // 0 부터 10 까지, 시작과 끝은 포함하는 범위
        val range2: IntRange = 0 until 10 // 위와 동일

        for (i in range) {
            print(i)
        }
        println()

        for (i in range2) {
            print(i)
        }
        println()

        val foo: Boolean = 5 in range // 5가 포함되어 있는지 확인
        println(foo)

        val bar: Boolean = 5 !in range // 5가 포함되지 않는지 확인
        println(bar)
    }

    @Test
    fun test10() {
        // 반대로 정렬된 범위를 생성하려면 'downTo'
        // 기본적으로 1씩 감소
        for (i in 1..4) print(i) // prints "1234"
        for (i in 1 until 5) print(i) // prints "1234"
    }

    @Test
    fun test11() {
        // 감소폭 변경 'step'
        for (i in 5 downTo 1 step 2) print(i) //  prints "531"
        println()

        // 증가폭 변경 'step'
        for (i in 1..5 step 2) print(i) //  prints "135"
    }

}
