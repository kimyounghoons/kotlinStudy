package com.study.kotlin.kotlinstudy.kakaosearch

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.study.kotlin.kotlinstudy.adapters.KakaoSearchAdapter
import com.study.kotlin.kotlinstudy.databinding.FragmentKakaoSearchBinding
import kotlinx.android.synthetic.main.fragment_kakao_search.*
import java.util.*


/**
 * Created by younghoon on 2018-05-08.
 */
class KakaoSearchFragment : Fragment(), java.util.Observer {

    private lateinit var searchAdapter: KakaoSearchAdapter
    private lateinit var fragmentKakaoSearchBinding: FragmentKakaoSearchBinding
    private lateinit var kakaoSearchViewModel: KakaoSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kakaoSearchViewModel = KakaoSearchViewModel(AdapterLoadSearchData(1, ArrayList()))
        addObserver(kakaoSearchViewModel)
        searchAdapter = KakaoSearchAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentKakaoSearchBinding = FragmentKakaoSearchBinding.inflate(inflater!!, container, false)
        fragmentKakaoSearchBinding.fragment = this
        fragmentKakaoSearchBinding.searchViewModel = kakaoSearchViewModel

        return fragmentKakaoSearchBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = searchAdapter
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleThreshold = 5
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    kakaoSearchViewModel.loadMore(search_edit.text.toString())
                }
            }
        })

    }

    fun clearFocusEditText() {
        search_edit.clearFocus()
    }

    fun hideKeyboard() {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(search_edit.applicationWindowToken, 0)
    }

    fun searchClicked(view: View) {
        if (view == fragmentKakaoSearchBinding.searchButton) {
            performSearch(fragmentKakaoSearchBinding.searchButton.text.toString())
        }
    }

    private fun performSearch(keyword: String) {
        clearFocusEditText()
        hideKeyboard()
        kakaoSearchViewModel.search(keyword)
    }

    private fun addObserver(observable: java.util.Observable) {
        observable.addObserver(this)
    }

    private fun deleteObserver(observable: Observable) {
        observable.deleteObserver(this)
    }

    override fun update(observable: java.util.Observable?, data: Any?) {
        if (observable is KakaoSearchViewModel) {
            searchAdapter.setAdapterLoadSearchData(kakaoSearchViewModel.adapterLoadSearchData)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteObserver(kakaoSearchViewModel)
    }
}