package com.study.kotlin.kotlinstudy.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.study.kotlin.kotlinstudy.R

class SearchViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val imageView : ImageView = view.findViewById(R.id.image)
    val displaySiteName: TextView = view.findViewById(R.id.display_sitename)
    val thumbnailUrl : TextView = view.findViewById(R.id.thumbnail_url)
}