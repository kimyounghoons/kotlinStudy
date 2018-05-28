package com.study.kotlin.kotlinstudy

import com.study.kotlin.kotlinstudy.kakaosearch.AdapterLoadSearchData
import com.study.kotlin.kotlinstudy.kakaosearch.KakaoSearchContract
import com.study.kotlin.kotlinstudy.kakaosearch.KakaoSearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var searchView: KakaoSearchContract.View

    private lateinit var adapterLoadSearchData: AdapterLoadSearchData
    private lateinit var searchPresenter: KakaoSearchPresenter

    @Before
    fun setupSearchPresenter() {
        MockitoAnnotations.initMocks(this)

        adapterLoadSearchData = AdapterLoadSearchData(1, ArrayList())
        searchPresenter = KakaoSearchPresenter(adapterLoadSearchData, searchView)
    }

    @Test
    fun search() {
        searchPresenter.search("aaa")
        verify(searchView, times(1)).clearFocusEditText()
        verify(searchView, times(1)).hideKeyboard()
    }


    @Test
    fun loadMore() {
        searchPresenter.loadMore("aaa")
        verify(searchView, times(0)).clearFocusEditText()
        verify(searchView, times(0)).hideKeyboard()
        Thread.sleep(1000)
        verify(searchView, times(1)).notifyItemRangeInserted(10, 10)
    }
}