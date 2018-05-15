package com.study.kotlin.kotlinstudy.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.KakaoSearchGridListAdapter
import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener
import kotlinx.android.synthetic.main.fragment_kakao_search_grid_list.*


/**
 * Created by yun on 2018. 5. 12..
 */
class KakaoSearchGridListFragment : Fragment() {
    private val PAGING_LIMIT: Int = 15

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvCount: TextView
    private lateinit var searchGridAdapter: KakaoSearchGridListAdapter

    private var currentPage: Int = 1
    private var isLoading: Boolean = false
    private var query: String = "smile"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kakao_search_grid_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view_progress
        tvCount = tv_count
        searchGridAdapter = KakaoSearchGridListAdapter(){
            loadData()
        }

        with(recycle_image) {
            adapter = searchGridAdapter
            layoutManager = GridLayoutManager(context, 3)
            searchGridAdapter.setLayoutManager(layoutManager as GridLayoutManager)
//            (layoutManager as GridLayoutManager).spanSizeLookup = searchGridAdapter.spanSizeLookup
        }

        loadData()
    }

    fun loadData() {
        showProgressBar(true)

        KakaoSearchDAO().getImageSearch(query, currentPage, PAGING_LIMIT, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                currentPage++
                searchGridAdapter.addData(response.documents)
                tv_count.setText(String.format("총 %d 개", searchGridAdapter.itemCount))
                showProgressBar(false)
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
                showProgressBar(false)
            }
        })
    }

    fun showProgressBar(isShow: Boolean) {
        isLoading = isShow
        progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

}

