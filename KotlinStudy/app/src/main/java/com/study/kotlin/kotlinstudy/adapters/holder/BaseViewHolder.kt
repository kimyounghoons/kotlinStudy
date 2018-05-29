package com.study.kotlin.kotlinstudy.adapters.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by yun on 2018. 5. 29..
 */
abstract class BaseViewHolder<T>(resource: Int, val context: Context, parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(resource, parent, false)) {

    abstract fun bind(item: T, position: Int, isBoolean : Boolean)
}