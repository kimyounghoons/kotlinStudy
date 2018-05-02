package com.study.kotlin.kotlinstudy

import org.junit.Test


/**
 * 컬렉션
 *
 * Array
 * List
 * Map
 * Set
 */
class Collections {

    /**
     * Array 배열
     */
    @Test
    fun test1() {
        val names = arrayOf("android", "java");
        println(names[0])

        // 빈 배열 생성
        val names2 = emptyArray<String>()


        // 크기가 3 이고 널 값을 포함하는 배열
        val names3 = arrayOfNulls<String>(3)
        names3[0] = "android"
        println(names3[0])


        // char 타입을 갖는 배열
        val chars = charArrayOf('a', 'b', 'c')
        for (i in 0 until chars.size) print(chars[i])
        println()


        // Int 타입을 갖는 배열
        val num = intArrayOf(0, 1, 2, 3)
        for (i in 0 until num.size) print(num[i])
        println()
    }

    /**
     * List 리스트
     */
    @Test
    fun test2() {

        // listOf
        // 읽기 전용 리스트
        val list1 = listOf("android", null, "java", "kotlin")
        println(list1)


        // listOfNotNull
        // 널값을 무시하고 널이 아닌값으로만 구성된 리스트
        val list2 = listOfNotNull(null)
        println(list2)
        val list3 = listOfNotNull("android", null, "java", "kotlin")
        println(list3)


        // mutableListOf
        // 리스트에 포함된 요소를 수정할 수 있는 리스트
        val list4 = mutableListOf(1, 2, 3, 4)
        println(list4)
        list4.add(5)
        println(list4)


        // arrayListOf
        // 리스트에 포함된 요소를 수정할 수 있는 리스트
        val list = arrayListOf(1, 2, 3, 4)
        println(list)
    }

    /**
     * Map 맵
     */
    @Test
    fun test3() {

        // mapOf
        // 읽기 전용 맵
        val map = mapOf('A' to 'a', 'B' to 'b')
        println(map['A'])

        // mapOf
        // 수정 할수 있는 맵
        val map2 = mutableMapOf('A' to 'a', 'B' to 'b')
        map2.put('A', 'c')
        println(map2['A'])

        // hashMapOf
        val map3 = hashMapOf('A' to 'a', 'B' to 'b')
        println(map3)
    }

    /**
     * Set 집합
     *
     * 중복되지 않는 요소들로 구성된 자료구조
     */
    @Test
    fun test4() {

        // setOf
        // 읽기 전용
        val strings = setOf("a", "b", "c", "c")
        println(strings)

        // mutableSetOf
        // 수정 가능한
        val strings2 = mutableSetOf("a", "b", "c", "c")
        strings2.add("d")
        println(strings2)
    }


}
