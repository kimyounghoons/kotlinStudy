package com.study.kotlin.kotlinstudy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.kotlin.kotlinstudy.data.ListItem
import kotlinx.android.synthetic.main.sample_list_item.view.*

/**
 * Created by yun on 2018. 4. 20..
 */
class SampleListAdapter(val context: Context, val dataList: ArrayList<ListItem>) : RecyclerView.Adapter<SampleListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
//        holder!!.tvTitle.setText(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage = itemView.iv_image
        val tvTitle = itemView.tv_title

    }
}