package com.study.kotlin.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.study.kotlin.kotlinstudy.models.Member
import com.study.kotlin.kotlinstudy.models.Person

class MainActivity : AppCompatActivity() {
    var person: Person = Person("kim", 28)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", person.name + "\n" + "person age : " + person.age)

        person.name = person.name
        person.age = person.age
        Log.d("MainActivity", person.name + "\n" + "person age : " + person.age)

        val mem = Member("young", 1)
        Log.d("MainActivity", mem.name + "\n" + "mem number : " + mem.num)

        mem.name = "222"
        Log.d("MainActivity", mem.name + "\n" + "mem number : " + mem.num)

    }

    data class Mem(var name: String, var num: Int)
}
