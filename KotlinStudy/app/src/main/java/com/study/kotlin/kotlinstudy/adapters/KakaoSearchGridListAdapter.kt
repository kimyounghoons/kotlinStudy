package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.data.Documents
import kotlinx.android.synthetic.main.item_footer_view.view.*
import kotlinx.android.synthetic.main.item_grid_view.view.*
import kotlinx.android.synthetic.main.item_normal_view.view.*

/**
 * Created by yun on 2018. 4. 20..
 */
class KakaoSearchGridListAdapter(val clickListener: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM_NORMAL = 0
    private val VIEW_TYPE_ITEM_GRID = 1
    private val VIEW_TYPE_FOOTER = 2

    private var itemList: ArrayList<Documents> = ArrayList()
    private lateinit var gridLayoutManager: GridLayoutManager

    private var isEditMode: Boolean = false
    private var cellType: Int = VIEW_TYPE_ITEM_GRID

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM_NORMAL -> NormalItemViewHolder(parent)
            VIEW_TYPE_ITEM_GRID -> GridItemViewHolder(parent)
            else -> {
                FooterViewHolder(parent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == getItemCount() - 1) VIEW_TYPE_FOOTER else
            (if (cellType == VIEW_TYPE_ITEM_GRID) VIEW_TYPE_ITEM_GRID else VIEW_TYPE_ITEM_NORMAL)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is NormalItemViewHolder -> holder.bind(position)
            is GridItemViewHolder -> holder.bind(position)
            is FooterViewHolder -> holder.bind()
        }
    }

    inner class NormalItemViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_normal_view, parent, false)) {

        fun bind(position: Int) {
            with(itemView) {

                Glide.with(itemView.context)
                        .load(getItem(position).thumbnail_url)
                        .into(iv_normal_thumbnail)

                tv_title.text = getItem(position).collection

                if (isEditMode) {
                    layout_normal_select.visibility = View.VISIBLE

                    val isSelected = getItem(position).isSelected
                    normal_checkbox.isChecked = isSelected

                    layout_container.setOnClickListener {
                        val isChecked = !normal_checkbox.isChecked
                        normal_checkbox.isChecked = isChecked
                        getItem(position).isSelected = isChecked
                    }

                } else {
                    layout_normal_select.visibility = View.GONE
                }

            }
        }
    }

    inner class GridItemViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_grid_view, parent, false)) {

        fun bind(position: Int) {
            with(itemView) {

                Glide.with(itemView.context)
                        .load(getItem(position).thumbnail_url)
                        .into(iv_thumbnail)

                if (isEditMode) {
                    layout_select.visibility = View.VISIBLE

                    val isSelected = getItem(position).isSelected
                    view_selected.visibility = if (isSelected) View.VISIBLE else View.GONE
                    checkbox.isChecked = isSelected

                    iv_thumbnail.setOnClickListener {
                        val isChecked = !checkbox.isChecked
                        checkbox.isChecked = isChecked
                        view_selected.visibility = if (isChecked) View.VISIBLE else View.GONE
                        getItem(position).isSelected = isChecked
                    }

                } else {
                    layout_select.visibility = View.GONE
                }

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
            return if (getItemViewType(position) == VIEW_TYPE_FOOTER) 3 else 1
        }
    }

    fun getItem(position: Int) = itemList.get(position)

    fun addItem(list: List<Documents>) {
        itemList.addAll(list)
        reload()
    }

    fun removeItem() {
        var selectedItemList = itemList.filter { it.isSelected == true }
        for (document in selectedItemList) {
            itemList.remove(document)
        }
        setEditMode(false)
    }

    fun selectAllItem() {
        for (i in itemList.indices) {
            itemList[i].isSelected = true
        }
        reload()
    }

    fun setEditMode(isEdit: Boolean) {
        isEditMode = isEdit

        for (i in itemList.indices) {
            itemList[i].isSelected = false
        }
        reload()
    }

    fun reload() {
        notifyDataSetChanged()
    }

    fun setGridLayoutManager(layoutManager: GridLayoutManager) {
        gridLayoutManager = layoutManager
    }

    fun setCellType(cellType: Int) {
        this.cellType = cellType
        reload()
    }

}