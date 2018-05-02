package com.study.kotlin.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.androidhuman.example.simplegithub.api.model.SearchResponse
import com.androidhuman.example.simplegithub.api.searchApi
import com.study.kotlin.kotlinstudy.api.SearchApi
import com.study.kotlin.kotlinstudy.data.ListItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : SampleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataList = arrayListOf(
                ListItem("Test 1", ""),
                ListItem("Test 2", ""),
                ListItem("Test 3", ""),
                ListItem("Test 4", "")
        )
        adapter = SampleListAdapter(this, dataList);

        searchRepository("과일")

    }

    private fun searchRepository(query: String) {
        searchCall = api.search(query)
        searchCall!!.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>,
                                    response: Response<SearchResponse>) {

                val searchResult = response.body()
                Log.e("onResponse", searchResult.toString());
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("onFailure",t.message);
            }
        })
    }

    internal val api: SearchApi by lazy { searchApi(this) }

    internal var searchCall: Call<SearchResponse>? = null
}
