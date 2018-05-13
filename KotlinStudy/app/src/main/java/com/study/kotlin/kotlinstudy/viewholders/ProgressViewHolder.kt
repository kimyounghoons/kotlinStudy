package com.study.kotlin.kotlinstudy.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.study.kotlin.kotlinstudy.R

class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val progressbar : ProgressBar = view.findViewById(R.id.progressbar)
}