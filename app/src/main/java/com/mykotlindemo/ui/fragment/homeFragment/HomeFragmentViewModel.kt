package com.mykotlindemo.ui.fragment.homeFragment

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mykotlindemo.databinding.FragmentHomeBinding
import com.mykotlindemo.model.MovieModel
import com.mykotlindemo.repository.Repository

@SuppressLint("StaticFieldLeak", "SetTextI18n")
class HomeFragmentViewModel<T>(activity: Activity, mBinding: T) : ViewModel() {

    private var activity: Activity? = null
    private var mBinding: FragmentHomeBinding
    private var movieAdepter: MovieAdepter? = null
    private lateinit var movieList: List<MovieModel>

    init {
        this.activity = activity
        this.mBinding = mBinding as FragmentHomeBinding
        getMovieData()

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        movieAdepter = MovieAdepter(activity)
        mBinding.rcvMovie.layoutManager = layoutManager
        mBinding.rcvMovie.adapter = movieAdepter

        mBinding.header.tvHeaderTitle.text = "Movie List"
    }

    private fun getMovieData() {
        Repository().getMovieResult().observe((activity as LifecycleOwner)) { movieData ->
            movieList = movieData
            movieAdepter!!.setMovieData(movieList)
        }
    }

}