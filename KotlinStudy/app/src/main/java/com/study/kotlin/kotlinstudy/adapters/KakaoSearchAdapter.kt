package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.viewholders.ProgressViewHolder
import com.study.kotlin.kotlinstudy.viewholders.SearchViewHolder

class KakaoSearchAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var searchList: ArrayList<Documents> = ArrayList()
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
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
        return searchList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SearchViewHolder) {
            Glide.with(context).load(searchList.get(position).thumbnail_url).into(holder.imageView)
            holder.displaySiteName.text = searchList.get(position).display_sitename
            holder.thumbnailUrl.text = searchList.get(position).thumbnail_url
        }

        if (holder is ProgressViewHolder) {
            holder.progressbar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (searchList[position] != null) {
            return VIEW_ITEM
        }
        return VIEW_PROG
    }

    fun setItems(lists: ArrayList<Documents>) {
        searchList = lists
        notifyDataSetChanged()
    }

    fun addItem(document: Documents){
        searchList.add(document)
    }

}
