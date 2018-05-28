package com.study.kotlin.kotlinstudy

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class KakaoSearchScreenTest{

    @Rule @JvmField
    var kakaoSearchScreenRule = object : ActivityTestRule<KakaoSearchActivity>(KakaoSearchActivity::class.java){
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()

        }
    }

    @Test
    fun search(){
        onView(withId(R.id.search_edit)).perform(click())
        onView(withId(R.id.search_edit)).perform(typeText("rmfla"))

        onView(withId(R.id.search_button)).perform(click())
        Thread.sleep(3000)
    }

    @Test
    fun loadMoreWithSearch(){
        onView(withId(R.id.search_edit)).perform(click())
        onView(withId(R.id.search_edit)).perform(typeText("rmfla"))

        onView(withId(R.id.search_button)).perform(click())
        Thread.sleep(3000)

        onView(withId(R.id.recyclerview)).perform(ViewActions.swipeUp())
        Thread.sleep(3000)
    }
}