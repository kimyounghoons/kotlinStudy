package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.databinding.ItemKakaoSearchBinding
import com.study.kotlin.kotlinstudy.databinding.ItemKakaoSearchEmptyBinding
import com.study.kotlin.kotlinstudy.databinding.ItemProgressbarBinding
import com.study.kotlin.kotlinstudy.kakaosearch.AdapterLoadSearchData
import com.study.kotlin.kotlinstudy.viewholders.EmptyViewHolder
import com.study.kotlin.kotlinstudy.viewholders.ProgressViewHolder
import com.study.kotlin.kotlinstudy.viewholders.SearchViewHolder

class KakaoSearchAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_EMPTY = 0
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 2

    private var adapterLoadSearchData: AdapterLoadSearchData = AdapterLoadSearchData(1, ArrayList())
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_EMPTY -> {
                val itemKakaoSearchEmptyBinding: ItemKakaoSearchEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.item_kakao_search_empty, parent, false)
                EmptyViewHolder(itemKakaoSearchEmptyBinding)
            }
            VIEW_ITEM -> {
                val itemKakaoSearchBinding: ItemKakaoSearchBinding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.item_kakao_search, parent, false)
                SearchViewHolder(itemKakaoSearchBinding)
            }
            else -> {
                val itemProgressbarBinding: ItemProgressbarBinding = DataBindingUtil
                        .inflate(LayoutInflater.from(parent!!.context), R.layout.item_progressbar, parent, false)
                ProgressViewHolder(itemProgressbarBinding)
            }
        }
    }

    fun setAdapterLoadSearchData(adapterLoadSearchData: AdapterLoadSearchData) {
        this.adapterLoadSearchData = adapterLoadSearchData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return (adapterLoadSearchData.getItemCount() + if (adapterLoadSearchData.isLoading()) 1 else 0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SearchViewHolder) {
            holder.bindDocuments(adapterLoadSearchData.searchList[position])
        }

        if (holder is ProgressViewHolder) {
            holder.itemProgressbarBinding.progressbar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (adapterLoadSearchData.searchList.size == 0 && !adapterLoadSearchData.isLoading()) {
            return VIEW_EMPTY
        }
        if (position < adapterLoadSearchData.searchList.size)
            return VIEW_ITEM

        return VIEW_PROG
    }

}
