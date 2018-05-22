package com.study.kotlin.kotlinstudy.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.KakaoSearchAdapter
import com.study.kotlin.kotlinstudy.dao.KakaoSearchDAO
import com.study.kotlin.kotlinstudy.models.responses.ResponseImageSearch
import com.study.kotlin.kotlinstudy.utils.network.NetworkListener
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by younghoon on 2018-05-08.
 */
open class KakaoSearchFragment : Fragment(), View.OnClickListener {

    override fun onClick(p0: View?) {
        search(searchEditText.text.toString())
    }

    lateinit var searchEditText: EditText
    lateinit var searchButton: Button
    lateinit var responseImageSearch: ResponseImageSearch
    lateinit var searchAdapter: KakaoSearchAdapter
    lateinit var recyclerView: RecyclerView
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

        searchEditText = view!!.findViewById(R.id.search_edit)
        searchButton = view!!.findViewById(R.id.search_button)
        searchButton.setOnClickListener(this)
    }

    fun search(keyworkd: String) {
        KakaoSearchDAO().getImageSearch(keyworkd, 1, 10, object : NetworkListener.RetroResopnseListener<ResponseImageSearch>() {
            override fun onResponse(response: ResponseImageSearch) {
                super.onResponse(response)
                responseImageSearch = response
                searchAdapter.setItems(responseImageSearch.documents)
                searchAdapter.notifyDataSetChanged()
            }
        }, object : NetworkListener.RetroErrorListener() {
            override fun onErrorResponse(errorCode: Int) {
                super.onErrorResponse(errorCode)
            }
        })
    }
}