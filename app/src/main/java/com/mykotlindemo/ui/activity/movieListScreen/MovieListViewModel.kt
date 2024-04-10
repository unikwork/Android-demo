package com.mykotlindemo.ui.activity.movieListScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.mykotlindemo.R
import com.mykotlindemo.databinding.ActivityMovieListBinding
import com.mykotlindemo.ui.fragment.homeFragment.HomeFragment
import com.mykotlindemo.ui.fragment.middleFragment.MiddleFragment

@SuppressLint("InlinedApi","StaticFieldLeak")
class MovieListViewModel<T>(activity: Activity, mBinding: T) : ViewModel() ,OnClickListener{
    private var activity:MovieListActivity?
    private var mBinding: ActivityMovieListBinding?



    init {
        this.activity = activity as MovieListActivity
        this.mBinding = mBinding as ActivityMovieListBinding
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        initView()
        mBinding.llHome.setOnClickListener(this)
        mBinding.llMiddle.setOnClickListener(this)
        mBinding.llThird.setOnClickListener(this)
    }

    private fun initView() {
        val homeFragment = HomeFragment()
        loadFragment(homeFragment)
    }

    private fun loadFragment(fragment:Fragment){
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onClick(view: View?) {
        when(view!!.id){
           mBinding!!.llHome.id -> {
                val fragment = HomeFragment()
                loadFragment(fragment)
            }

            mBinding!!.llMiddle.id ->{
                val middleFragment = MiddleFragment()
                loadFragment(middleFragment)
            }

            mBinding!!.llThird.id ->{



            }

        }


    }




}