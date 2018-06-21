package com.study.kotlin.kotlinstudy.viewholders

import android.support.v7.widget.RecyclerView
import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.databinding.ItemKakaoSearchBinding
import com.study.kotlin.kotlinstudy.kakaosearch.ItemDocumentViewModel

class SearchViewHolder(var itemKakaoSearchBinding: ItemKakaoSearchBinding) : RecyclerView.ViewHolder(itemKakaoSearchBinding.root) {

    fun bindDocuments(documents: Documents) {
        if (itemKakaoSearchBinding.viewModel == null) {
            itemKakaoSearchBinding.viewModel= ItemDocumentViewModel(documents)
            return
        }
        itemKakaoSearchBinding.viewModel!!.setDocuments(documents)
    }
}