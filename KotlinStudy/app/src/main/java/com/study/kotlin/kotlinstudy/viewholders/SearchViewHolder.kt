package com.study.kotlin.kotlinstudy.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_kakao_search.view.*

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.image
    val displaySiteName: TextView = view.display_sitename
    val thumbnailUrl: TextView = view.thumbnail_url
}