package com.study.kotlin.kotlinstudy.kakaosearch

interface KakaoSearchContract {
    interface View {
        fun clearFocusEditText()
        fun hideKeyboard()
        fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    }

    interface Presenter {
        fun search(searchText: String)
        fun loadMore(searchText: String)
    }
}