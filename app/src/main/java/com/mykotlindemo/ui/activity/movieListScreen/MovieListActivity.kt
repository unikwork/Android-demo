package com.mykotlindemo.ui.activity.movieListScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.R
import com.mykotlindemo.ViewModelFactory
import com.mykotlindemo.databinding.ActivityMovieListBinding

class MovieListActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMovieListBinding
    private lateinit var viewModel: MovieListViewModel<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = setContentView(this,R.layout.activity_movie_list)
        viewModel = ViewModelProvider(this,ViewModelFactory(this,mBinding))[MovieListViewModel::class.java] as MovieListViewModel<Any>
    }

}