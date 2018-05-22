package com.study.kotlin.kotlinstudy.kakaosearch

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.study.kotlin.kotlinstudy.R
import com.study.kotlin.kotlinstudy.adapters.KakaoSearchAdapter
import kotlinx.android.synthetic.main.fragment_kakao_search.*


/**
 * Created by younghoon on 2018-05-08.
 */
class KakaoSearchFragment : Fragment(), View.OnClickListener, KakaoSearchContract.View {
    private var presenter: KakaoSearchPresenter? = null
    private lateinit var searchAdapter: KakaoSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = KakaoSearchPresenter(AdapterLoadSearchData(1, ArrayList()), this)
        searchAdapter = KakaoSearchAdapter(context, presenter?.adapterLoadSearchData!!)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_kakao_search, container, false)
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
                    presenter?.loadMore(search_edit.text.toString())
                }
            }
        })

        search_button.setOnClickListener(this)
    }

    override fun clearFocusEditText() {
        search_edit.clearFocus()
    }

    override fun hideKeyboard() {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(search_edit.applicationWindowToken, 0)
    }

    override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
        searchAdapter.notifyItemRangeInserted(positionStart, itemCount)
        Log.d("notifyItemRangeInserted : ", "notifyItemRangeInserted = postionStart: $positionStart   itemcount : $itemCount")
//        searchAdapter.notifyDataSetChanged()
    }



    override fun onClick(view: View?) {
        presenter?.search(search_edit.text.toString())
    }

}