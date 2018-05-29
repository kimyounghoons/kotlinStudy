package com.study.kotlin.kotlinstudy.adapters.holder

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.listener.ItemListener
import kotlinx.android.synthetic.main.item_footer_view.view.*
import kotlinx.android.synthetic.main.item_horizontal_view.view.*

/**
 * Created by yun on 2018. 5. 29..
 */
class FooterViewHolder(context: Context, parent: ViewGroup, val itemListener: ItemListener) :
        BaseViewHolder<ArrayList<Documents>>(R.layout.item_footer_view, context, parent) {

    override fun bind(itemList: ArrayList<Documents>, position: Int, isEmpty: Boolean) {
        with(itemView) {

            with(recycler_horizontal) {
                adapter = FooterListAdapter(itemList)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

            if (isEmpty) {
                layout_empty.visibility = View.VISIBLE
                layout_add.visibility = View.GONE
                btn_add.setOnClickListener {
                    itemListener.onAddItemClick()
                }
            } else {
                layout_empty.visibility = View.GONE
                layout_add.visibility = View.VISIBLE
                tv_add.setOnClickListener {
                    itemListener.onAddItemClick()
                }
            }

        }
    }

    inner class FooterListAdapter(private val list: List<Documents>) : RecyclerView.Adapter<HorizontalItemViewHolder>() {
        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: HorizontalItemViewHolder?, position: Int) {
            holder?.bind(list.get(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalItemViewHolder {
            return HorizontalItemViewHolder(parent)
        }
    }

    inner class HorizontalItemViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_horizontal_view, parent, false)) {

        fun bind(item: Documents) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(item.thumbnail_url)
                        .into(iv_thumbnail)

                tv_title.text = item.collection

                setOnClickListener {
                    itemListener.onFooterItemClick()
                }

            }
        }
    }
}