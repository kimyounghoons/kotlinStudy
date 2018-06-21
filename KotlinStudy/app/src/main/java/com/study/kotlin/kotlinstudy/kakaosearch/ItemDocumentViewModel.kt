package com.study.kotlin.kotlinstudy.kakaosearch

import android.databinding.BaseObservable
import com.study.kotlin.kotlinstudy.data.Documents

class ItemDocumentViewModel(var document: Documents) : BaseObservable() {

    fun setDocuments(document: Documents){
        this.document = document
        notifyChange()
    }
}