package com.study.kotlin.kotlinstudy.kakaosearch

class KakaoSearchPresenter(var adapterLoadSearchData: AdapterLoadSearchData, var view: KakaoSearchContract.View) : KakaoSearchContract.Presenter {

    override fun search(searchText: String) {
        view.clearFocusEditText()
        view.hideKeyboard()
        adapterLoadSearchData.initData()
        adapterLoadSearchData.search(searchText, object : AdapterLoadSearchData.LoadSearchCallback {
            override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
                view.notifyItemRangeInserted(positionStart, itemCount)
            }
        })
    }

    override fun loadMore(searchText: String) {
        if (!adapterLoadSearchData.isLoading()) {
            adapterLoadSearchData.search(searchText, object : AdapterLoadSearchData.LoadSearchCallback {
                override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
                    view.notifyItemRangeInserted(positionStart, itemCount)
                }
            })
        }
    }

}