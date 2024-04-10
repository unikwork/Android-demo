package com.mykotlindemo.ui.activity.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.R
import com.mykotlindemo.ViewModelFactory
import com.mykotlindemo.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityNotificationBinding
    private lateinit var viewModel: NotificationViewModel<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_notification)
        viewModel = ViewModelProvider(this,ViewModelFactory(this,mBinding))[NotificationViewModel::class.java] as NotificationViewModel<Any>

    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackPressed()
    }
}