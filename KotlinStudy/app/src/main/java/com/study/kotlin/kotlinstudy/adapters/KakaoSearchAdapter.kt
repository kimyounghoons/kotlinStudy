package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.viewholders.SearchViewHolder

class KakaoSearchAdapter(val context: Context) : RecyclerView.Adapter<SearchViewHolder>() {
    var searchList: List<Documents> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_kakao_search, parent, false)
        return SearchViewHolder(v)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        Glide.with(context).load(searchList.get(position).thumbnail_url).into(holder?.imageView)
        holder?.displaySiteName?.text = searchList.get(position).display_sitename
        holder?.thumbnailUrl?.text = searchList.get(position).thumbnail_url
    }

    fun setItems(lists: List<Documents>) {
        searchList = lists
        notifyDataSetChanged()
    }

}
