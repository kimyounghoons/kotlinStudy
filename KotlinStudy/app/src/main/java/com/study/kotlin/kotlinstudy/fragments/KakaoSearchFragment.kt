package com.study.kotlin.kotlinstudy.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.KakaoSearchAdapter
import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener
import android.content.Context.INPUT_METHOD_SERVICE
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


/**
 * Created by younghoon on 2018-05-08.
 */
open class KakaoSearchFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {
        searchEditText.clearFocus()
        hideSoftKeyboard(context as Activity,searchEditText)
        initData()
        searchKeyword = searchEditText.text.toString()
        search(searchKeyword)
    }

    fun hideSoftKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

    private fun initData() {
        searchAdapter.setItems(ArrayList())
        page = 1
    }

    lateinit var searchEditText: EditText
    lateinit var searchButton: Button
    lateinit var responseImageSearch: ResponseImageSearch
    lateinit var searchAdapter: KakaoSearchAdapter
    lateinit var recyclerView: RecyclerView
    var loading: Boolean = false
    var page: Int = 1
    lateinit var searchKeyword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchAdapter = KakaoSearchAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_kakao_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view!!.findViewById(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = searchAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleThreshold = 5
                var totalItemCount = layoutManager.itemCount
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    search(searchKeyword)
                }
            }
        })

        searchEditText = view!!.findViewById(R.id.search_edit)
        searchButton = view!!.findViewById(R.id.search_button)
        searchButton.setOnClickListener(this)
    }

    fun search(keyworkd: String) {
        loading = true
        KakaoSearchDAO().getImageSearch(keyworkd, page, 10, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                super.onResponse(response)
                page += 1
                responseImageSearch = response
                for (document: Documents in responseImageSearch.documents) {
                    searchAdapter.addItem(document)
                }
                searchAdapter.notifyItemRangeInserted(searchAdapter.searchList.size, responseImageSearch.documents.size)
                loading = false
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
                loading = false
            }
        })
    }
}