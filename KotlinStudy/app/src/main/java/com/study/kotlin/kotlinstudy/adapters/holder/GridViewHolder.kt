package com.study.kotlin.kotlinstudy.adapters.holder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.listener.ItemListener
import kotlinx.android.synthetic.main.item_grid_view.view.*

/**
 * Created by yun on 2018. 5. 29..
 */
class GridViewHolder(context: Context, parent: ViewGroup, val itemListener: ItemListener) :
        BaseViewHolder<Documents>(R.layout.item_grid_view, context, parent) {

    override fun bind(item: Documents, position: Int, isEditMode: Boolean) {
        with(itemView) {

            Glide.with(context)
                    .load(item.thumbnail_url)
                    .into(iv_thumbnail)

            if (isEditMode) {
                layout_select.visibility = View.VISIBLE

                val isSelected = item.isSelected
                checkbox.isChecked = isSelected
                view_rect_selected.visibility = if (isSelected) View.VISIBLE else View.GONE

                setOnClickListener {
                    val isChecked = !checkbox.isChecked
                    checkbox.isChecked = isChecked
                    view_rect_selected.visibility = if (isChecked) View.VISIBLE else View.GONE
                    item.isSelected = isChecked
                }

            } else {
                layout_select.visibility = View.GONE
                setOnClickListener {
                    itemListener.onItemClick()
                }
            }

        }
    }

}
