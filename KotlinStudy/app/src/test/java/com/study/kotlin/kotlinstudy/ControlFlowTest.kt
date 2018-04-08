package com.study.kotlin.kotlinstudy

import org.junit.Test

/**
 * Created by younghoon on 2018-04-06.
 */
class ControlFlowTest {

    @Test
    fun forControlFlow() {
        for (n in 2..9) {
            System.out.println(n)
        }
    }

    @Test
    fun forControlFlow2() {
        for (n in 9 downTo 2) {
            System.out.println(n)
        }
    }

    @Test
    fun forControlFlow3() {
        for (n in 9 downTo 2 step 2) {
            System.out.println(n)
        }
    }

    @Test
    fun forControlFlow4(){
        var array : Array<String> = arrayOf("A","B","C")
        for(name: String in array){
            System.out.println(name)
        }

        for(index in 0..array.size-1){
            System.out.println(array.get(index))
        }
    }

    @Test
    fun whenControlFlow(){
        var array : Array<String> = arrayOf("A","B","C")
//        val obj : Any = "String"
//        val obj : Any = 1
        val obj : Any = 1L
            when (obj) {
                1 -> println("One")
                "Hello" -> println("Greeting")
                is Long -> println("Long")
                !is String -> println("Not a string")
                else -> println("Unknown")
            }

        when {
            obj is String -> println("string")
            array.size == 3 -> println("array size 3")
         }
    }

    @Test
    fun arrayList(){
        val list : ArrayList<String?> = ArrayList()
        list.add("11")
        list.add("22")
        list.add(null)
        list.add("33")
        for(string in list.filterNotNull())
        System.out.println(string)
//        for(string in list)
//            System.out.println(string)
    }

}