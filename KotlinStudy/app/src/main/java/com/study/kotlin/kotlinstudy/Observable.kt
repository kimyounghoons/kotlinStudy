package com.study.kotlin.kotlinstudy

import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by kimyounghoon on 2018-04-12.
 */
open class Observable {
    private val observers: CopyOnWriteArrayList<Observer> = CopyOnWriteArrayList()

    val observersCount: Int
        get() = observers.size

    fun registerObserver(observer: Observer?) {
        if (observer == null)
            throw NullPointerException()
        if (!contains(observer)) {
            observers.add(observer)
        }
    }

    private operator fun contains(observer: Observer): Boolean {
        return indexOf(observer) >= 0
    }

    private fun indexOf(observer: Observer): Int {
        for (i in observers.indices) {
            val registeredObserver = observers[i]
            if (registeredObserver === observer) {
                return i
            }
        }
        return -1
    }

    fun unregisterObserver(observer: Observer) {
        val i = indexOf(observer)
        if (i >= 0)
            observers.removeAt(i)
    }

    fun notifyObservers(data: Any) {
        val observersToRemove = ArrayList<Observer>()

        observers.forEach { registeredObserver ->
            if (registeredObserver == null) {
                observersToRemove.add(registeredObserver)
            } else {
                registeredObserver.update(this, data)
            }
        }

        observers.removeAll(observersToRemove)
    }

    fun deleteObservers() {
        observers.clear()
    }
}