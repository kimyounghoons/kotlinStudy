package com.study.kotlin.kotlinstudy

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.study.kotlin.kotlinstudy.databinding.ActivitySecondBinding
import com.study.kotlin.kotlinstudy.models.Post
import org.greenrobot.eventbus.EventBus



class SecondActivity : AppCompatActivity(), View.OnClickListener {

    var post: Post? = null
    lateinit var dataBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        dataBinding.increase.setOnClickListener(this)
        dataBinding.decrease.setOnClickListener(this)

        val postIdx = intent.getStringExtra("postIdx")
//        if (post == null)
//            throw ExceptionInInitializerError()
        post = ModelManager.getPost(postIdx)
        dataBinding.userIdx.text = post!!.user.Idx
    }

    override fun onClick(v: View?) {
        when (v) {
            dataBinding.increase -> {
                post!!.user.Idx = post!!.user.Idx.toInt().plus(1).toString()
                dataBinding.userIdx.text = post!!.user.Idx
                post!!.notifyObservers(post!!)
//                EventBus.getDefault().post(post)
            }
            dataBinding.decrease -> {
                post!!.user.Idx = post!!.user.Idx.toInt().minus(1).toString()
                dataBinding.userIdx.text = post!!.user.Idx
                post!!.notifyObservers(post!!)
//                EventBus.getDefault().post(post)
            }
        }
    }
}
