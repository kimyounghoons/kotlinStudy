package com.study.kotlin.kotlinstudy.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.study.kotlin.kotlinstudy.listener.ItemListener
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.KakaoListAdapter
import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener
import kotlinx.android.synthetic.main.fragment_kakao_list.*


/**
 * Created by yun on 2018. 5. 12..
 */
class KakaoNormalListFragment : Fragment() {
    private val PAGING_LIMIT: Int = 16

    private lateinit var listAdapter: KakaoListAdapter

    private var currentPage: Int = 1
    private var isLoading: Boolean = false
    private var query: String = "smile"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kakao_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = KakaoListAdapter(context, itemListener)

        with(recycle_image) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            listAdapter.setCellType(0)
        }

        loadItems()
        loadFooterItems();
        initView()
    }

    private fun loadItems() {
        showProgressBar(true)

        KakaoSearchDAO().getImageSearch(query, currentPage, PAGING_LIMIT, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                currentPage++
                listAdapter.addItem(response.documents)

                processItems(listAdapter.itemCount)
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
                showProgressBar(false)
            }
        })
    }

    private fun loadFooterItems() {
        KakaoSearchDAO().getImageSearch(query, currentPage, PAGING_LIMIT, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                listAdapter.addFooterItem(response.documents)
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
            }
        })
    }


    private fun removeItems() {
        showProgressBar(true)
        processItems(listAdapter.itemCount)
    }

    private fun initView() {
        tv_edit.setOnClickListener {
            setEditMode(true)
        }
        tv_cancel.setOnClickListener {
            setEditMode(false)
        }
        tv_delete.setOnClickListener {
            listAdapter.removeItem()
        }
        layout_all.setOnClickListener {
            var isChecked = !checkbox_all.isChecked
            checkbox_all.isChecked = isChecked
            listAdapter.selectAllItem(isChecked)
        }
    }

    private fun processItems(count: Int) {
        showNoItemsViews(count == 0)
        setEditMode(false)
        showItemsCount(count)
        showProgressBar(false)
    }

    private fun setEditMode(isEditMode: Boolean) {
        listAdapter.setEditMode(isEditMode)
        layout_menu.visibility = if (isEditMode) View.GONE else View.VISIBLE
        layout_menu_edit.visibility = if (isEditMode) View.VISIBLE else View.GONE
        checkbox_all.isChecked = false
    }

    private fun showProgressBar(isShow: Boolean) {
        isLoading = isShow
        view_progress.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showItemsCount(count: Int) {
        tv_count.text = String.format("%d items", count)
    }

    private fun showNoItemsViews(isShow: Boolean) {
        layout_header.visibility = if (isShow) View.GONE else View.VISIBLE
    }

    internal var itemListener: ItemListener = object : ItemListener {

        override fun onItemClick() {
            Toast.makeText(context, "onItemClick", Toast.LENGTH_SHORT).show()
        }

        override fun onAddItemClick() {
            loadItems()
        }

        override fun onRemoveItem(count: Int) {
            removeItems()
        }

        override fun onFooterItemClick() {
            Toast.makeText(context, "onFooterItemClick", Toast.LENGTH_SHORT).show()
        }


    }

}

