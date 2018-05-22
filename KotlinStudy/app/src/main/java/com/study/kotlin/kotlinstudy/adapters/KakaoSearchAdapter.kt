package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.kakaosearch.AdapterLoadSearchData
import com.study.kotlin.kotlinstudy.viewholders.EmptyViewHolder
import com.study.kotlin.kotlinstudy.viewholders.ProgressViewHolder
import com.study.kotlin.kotlinstudy.viewholders.SearchViewHolder

class KakaoSearchAdapter(private val context: Context, private val adapterLoadSearchData: AdapterLoadSearchData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_EMPTY = 0
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 2

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_EMPTY ->{
                val v = LayoutInflater.from(context).inflate(R.layout.layout_kakao_search_item_empty, parent, false)
                EmptyViewHolder(v)
            }
            VIEW_ITEM -> {
                val v = LayoutInflater.from(context).inflate(R.layout.item_kakao_search, parent, false)
                SearchViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(context).inflate(R.layout.item_progressbar, parent, false)
                ProgressViewHolder(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return (adapterLoadSearchData.searchList.size + if (adapterLoadSearchData.isLoading()) 1 else 0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SearchViewHolder) {
            Glide.with(context).load(adapterLoadSearchData.searchList[position].thumbnail_url).into(holder.imageView)
            holder.displaySiteName.text = adapterLoadSearchData.searchList[position].display_sitename
            holder.thumbnailUrl.text = adapterLoadSearchData.searchList[position].thumbnail_url
        }

        if (holder is ProgressViewHolder) {
            holder.progressbar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (adapterLoadSearchData.searchList.size == 0&& !adapterLoadSearchData.isLoading()) {
            return VIEW_EMPTY
        }
        if (position < adapterLoadSearchData.searchList.size)
            return VIEW_ITEM

        return VIEW_PROG
    }

}
