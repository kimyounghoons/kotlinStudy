package com.study.kotlin.kotlinstudy.helpers

/**
 * Created by kimyounghoon on 2018-04-06.
 */
class TestHelper {
    companion object {
        fun compareString(newString: String, oldString: String = "younghoon"): Boolean = oldString == newString

        fun add(vararg numbers: Int): Int {
            return numbers.sum()
//            for (number in numbers)
//                total += number
//            return total
        }
    }

}