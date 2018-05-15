package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import kotlinx.android.synthetic.main.item_footer_view.view.*
import kotlinx.android.synthetic.main.item_grid_view.view.*

/**
 * Created by yun on 2018. 4. 20..
 */
class KakaoSearchGridListAdapter(val clickListener: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_FOOTER = 1

    private var dataList: ArrayList<Documents> = ArrayList()
    private lateinit var gridLayoutManager : GridLayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_ITEM -> return ItemViewHolder(parent)
            VIEW_TYPE_FOOTER -> return FooterViewHolder(parent)
        }
        throw Exception()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == getItemCount() - 1) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(position)
            is FooterViewHolder -> holder.bind()
        }
    }

    inner class ItemViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_grid_view, parent, false)) {

        fun bind(position: Int) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(dataList.get(position).thumbnail_url)
                        .into(iv_thumbnail)
            }
        }
    }

    inner class FooterViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_footer_view, parent, false)) {

        fun bind() {
            with(itemView) {
                tv_add.setOnClickListener {
                    clickListener()
                }
            }
        }
    }

    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (getItemViewType(position) == VIEW_TYPE_FOOTER) 1 else 3
        }
    }

    fun addData(list: List<Documents>) {
        dataList.addAll(list)
//        gridLayoutManager.spanSizeLookup = spanSizeLookup
        notifyDataSetChanged()
    }

    fun setLayoutManager(layoutManager : GridLayoutManager) {
        gridLayoutManager = layoutManager
    }

}