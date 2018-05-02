package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.data.User
import org.junit.Test


/**
 * 널 안전성
 */
class NullSafety {

    /**
     * 널 허용 여부 표기
     *
     * ? 를 타입 뒤에 붙여 준다 (널 값 허용)
     */
    @Test
    fun test1() {
        val user: String = formatUser("yun", null)
        System.out.println(user)
    }

    // name 은 null 값을 허용 하지 않음
    // address 은 null 값 허용
    fun formatUser(name: String, id: String?): String {
        return name + "\n" + id
    }


    /**
     * 널 값을 대신 하는 방법
     *
     * ?: 엘비스
     */
    @Test
    fun test2() {
        val user: User = User("yun", null)

        // 값이 없을 경우 처리
        val id: String = user.id ?: "testId"
        System.out.println(id)
    }


    /**
     * 널 값 확인과 처리
     *
     * ?. safe call (안전한 호출)
     */
    @Test
    fun test3() {
        val user: User = User("yun", null)

        // user 가 null 이 아닌 경우에만 해당 값을 대입
        // 그렇지 않은 경우는 null 을 대압
        val name: String = user?.name
        System.out.println(name)
    }

    /**
     * ?: elvis 엘비스
     * ?. safe call
     */
    @Test
    fun test4() {
        val user: User = User("yun", "")

        // info 가 없거나 name 가 없을 경우 "No name" 반환
        val name: String = user.info?.name ?: "No name"
        System.out.println(name)
    }


    /**
     * 안전한 자료형 변환
     *
     * as? 연산자
     */
    @Test
    fun test5() {
        val default: Int = 12345
        val userId: String = "12345"

        // 자료형 변환에 실패할 경우 예외 발생 대신, null 반환
        val bar = userId as? Int
        System.out.println(bar)

        // 자료형 변환에 실패할 경우 기본값 설정
        val bar2 = userId as? Int ?: default
        System.out.println(bar2)
    }


    /**
     * 널 값이 아님을 명시
     *
     * !! 비널 값 보증 (non-null assertions)
     */
    @Test
    fun test6() {
        class ProductInfo(val title: String?, val price: Int)

        class Product(productInfo: ProductInfo) {
            val title: String
            val price: Int

            init {
                title = productInfo.title!!
                price = productInfo.price
            }
        }

        var productInfo: ProductInfo = ProductInfo("product title", 9999)
        var product: Product = Product(productInfo)
        System.out.println(product.title)
    }

}
