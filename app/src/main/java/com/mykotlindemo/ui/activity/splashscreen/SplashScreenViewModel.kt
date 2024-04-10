package com.mykotlindemo.ui.activity.splashscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModel
import com.mykotlindemo.databinding.ActivitySplashScreenBinding
import com.mykotlindemo.ui.activity.movieListScreen.MovieListActivity

@SuppressLint("InlinedApi")
class SplashScreenViewModel<T>(activity: Activity, mBinding: T) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private var activity: Activity?
    private var mBinding: ActivitySplashScreenBinding?

    init {
        this.activity = activity
        this.mBinding = mBinding as ActivitySplashScreenBinding
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        initView()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            activity!!.startActivity(Intent(activity, MovieListActivity::class.java))
        }, 6000)
    }


}
