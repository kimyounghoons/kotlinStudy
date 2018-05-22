package com.study.kotlin.kotlinstudy.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.item_progressbar.view.*

class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val progressbar: ProgressBar = view.progressbar
}