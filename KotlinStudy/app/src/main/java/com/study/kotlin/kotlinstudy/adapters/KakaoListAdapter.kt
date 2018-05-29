package com.study.kotlin.kotlinstudy.adapters

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.study.kotlin.kotlinstudy.listener.ItemListener
import com.study.kotlin.kotlinstudy.adapters.holder.FooterViewHolder
import com.study.kotlin.kotlinstudy.adapters.holder.GridViewHolder
import com.study.kotlin.kotlinstudy.adapters.holder.NormalViewHolder
import com.study.kotlin.kotlinstudy.data.Documents


/**
 * Created by yun on 2018. 4. 20..
 */
class KakaoListAdapter(val context: Context, val itemListener: ItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList: ArrayList<Documents> = ArrayList()
    private var footerItemList: ArrayList<Documents> = ArrayList()

    private lateinit var gridLayoutManager: GridLayoutManager

    private var isEditMode: Boolean = false
    private var cellType: Int = VIEW_TYPE_ITEM_GRID

    companion object {
        val VIEW_TYPE_ITEM_NORMAL = 0
        val VIEW_TYPE_ITEM_GRID = 1
        val VIEW_TYPE_FOOTER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM_NORMAL -> NormalViewHolder(context, parent, itemListener)
            VIEW_TYPE_ITEM_GRID -> GridViewHolder(context, parent, itemListener)
            else -> FooterViewHolder(context, parent, itemListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == getItemCount() - 1) VIEW_TYPE_FOOTER else
            (if (cellType == VIEW_TYPE_ITEM_GRID) VIEW_TYPE_ITEM_GRID else VIEW_TYPE_ITEM_NORMAL)
    }

    override fun getItemCount(): Int {
        return itemList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is NormalViewHolder -> holder.bind(getItem(position), position, isEditMode)
            is GridViewHolder -> holder.bind(getItem(position), position, isEditMode)
            is FooterViewHolder -> holder.bind(footerItemList, position, itemList.isEmpty())
        }
    }

    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (getItemViewType(position) == VIEW_TYPE_FOOTER) 3 else 1
        }
    }

    private fun getItem(position: Int) = itemList[position]

    fun addItem(list: List<Documents>) {
        itemList.addAll(list)
        reload()
    }

    fun removeItem() {
        var selectedItemList = itemList.filter { it.isSelected == true }

        for (document in selectedItemList) {
            itemList.remove(document)
        }
        itemListener.onRemoveItem(itemCount)
    }

    fun selectAllItem(isChecked: Boolean) {
        for (i in itemList.indices) {
            itemList[i].isSelected = isChecked
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

    fun addFooterItem(list: List<Documents>) {
        footerItemList.addAll(list)
        reload()
    }

}