package com.study.kotlin.kotlinstudy.models.responses

import com.study.kotlin.kotlinstudy.data.Documents
import com.study.kotlin.kotlinstudy.data.Meta

/**
 * Created by younghoon on 2018-05-08.
 */
data class ResponseImageSearch(val meta: Meta, val documents : Documents)