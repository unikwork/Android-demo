package com.mykotlindemo.ui.activity.notification

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.mykotlindemo.databinding.ActivityNotificationBinding
import com.mykotlindemo.model.MovieModel
import com.mykotlindemo.repository.Repository
import com.mykotlindemo.ui.activity.movieListScreen.MovieListActivity

class NotificationViewModel<T>(activity: Activity, mBinding: T) :ViewModel() {
    var activity:NotificationActivity
    var mBinding:ActivityNotificationBinding
    lateinit var movieList:List<MovieModel>



    init {
        this.activity = activity as NotificationActivity
        this.mBinding = mBinding as ActivityNotificationBinding
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        mBinding.header.tvHeaderTitle.text = "Movie Description"
        val position = activity.intent.getIntExtra("moviePosition",0)

        Repository().getMovieResult().observe(activity as LifecycleOwner){
            movieList = it
            Glide.with(activity).load(movieList[position].imageUrl).into(mBinding.ivMovie)
            mBinding.tvDescription.text = movieList[position].desc
            mBinding.tvMovieName.text = movieList[position].name
        }



    }

    fun onBackPressed() {
        activity.startActivity(Intent(activity,MovieListActivity::class.java))
    }

}