package com.mykotlindemo.ui.activity.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.R
import com.mykotlindemo.ViewModelFactory
import com.mykotlindemo.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    lateinit var mBinding : ActivitySplashScreenBinding
    lateinit var viewModel: SplashScreenViewModel<Any?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)
        viewModel = ViewModelProvider(this,ViewModelFactory(this,mBinding))[SplashScreenViewModel::class.java] as SplashScreenViewModel<Any?>

    }
}