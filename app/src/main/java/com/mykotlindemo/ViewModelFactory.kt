package com.mykotlindemo

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.ui.activity.movieListScreen.MovieListViewModel
import com.mykotlindemo.ui.activity.notification.NotificationViewModel
import com.mykotlindemo.ui.activity.splashscreen.SplashScreenViewModel
import com.mykotlindemo.ui.fragment.homeFragment.HomeFragmentViewModel
import com.mykotlindemo.ui.fragment.middleFragment.MiddleFragmentViewModel

@Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS")
class ViewModelFactory<T>(private val activity: Activity, private val mBinding: T) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SplashScreenViewModel::class.java){
            return SplashScreenViewModel(activity,mBinding) as T
        }else if (modelClass == MovieListViewModel::class.java){
            return MovieListViewModel(activity,mBinding) as T
        }else if(modelClass == HomeFragmentViewModel::class.java){
            return HomeFragmentViewModel(activity,mBinding) as T
        }else if (modelClass == NotificationViewModel::class.java){
            return NotificationViewModel(activity,mBinding) as T
        }else if (modelClass == MiddleFragmentViewModel::class.java){
            return MiddleFragmentViewModel(activity,mBinding) as T
        }
        return null as T
    }
}