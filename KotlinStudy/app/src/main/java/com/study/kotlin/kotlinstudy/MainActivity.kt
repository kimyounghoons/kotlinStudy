package com.study.kotlin.kotlinstudy

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.study.kotlin.kotlinstudy.databinding.ActivityMainBinding
import com.study.kotlin.kotlinstudy.models.Post
import com.study.kotlin.kotlinstudy.models.User
import io.reactivex.subjects.BehaviorSubject
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), Observer, View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var post: Post
    lateinit var button: Button

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getPost(post: Post) {
        this.post = post
        binding.userIdx.text = post.user.Idx
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        post = Post("aaa", User("111", "kimyounghoon"))
        post.registerObserver(this)
//        EventBus.getDefault().register(this)
        button = binding.button
        button.setOnClickListener(this)

        binding.userIdx.text = post.user.Idx
        io.reactivex.Observable.just("111").subscribe()
    }

    override fun onClick(v: View?) {
        Source.fromNextToComplete()
        var intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("postIdx", post.Idx)
        startActivity(intent)
    }

    override fun onDestroy() {
        post.unregisterObserver(this)
//        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is Post) {
            post = o
            binding.userIdx.text = post.user.Idx
        }
    }

    object Source {
        var source: io.reactivex.Observable<Any>? = null
        var subject: BehaviorSubject<Any>? = null

        init {
        }

        fun fromNextToComplete() {
            source = io.reactivex.Observable.create { it ->
                run {
                    it.onNext(100)
                    it.onComplete()
                }
            }
        }

    }

}
