package com.mykotlindemo.ui.fragment.homeFragment

import android.annotation.SuppressLint
import android.app.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mykotlindemo.databinding.MovieItemDesignBinding
import com.mykotlindemo.model.MovieModel
import com.mykotlindemo.ui.activity.notification.NotificationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.*
import java.net.URL


class MovieAdepter(val activity: Activity) : RecyclerView.Adapter<MovieAdepter.ViewData>() {
    private var movieList: List<MovieModel> = ArrayList<MovieModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val mBinding = MovieItemDesignBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewData(mBinding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.setData(position, activity, movieList)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieData(movieList: List<MovieModel>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class ViewData(mBinding: MovieItemDesignBinding) : RecyclerView.ViewHolder(mBinding.root) {
        var mBinding: MovieItemDesignBinding
        var bitmapImage: Bitmap? = null

        init {
            this.mBinding = mBinding
        }

        @SuppressLint("RemoteViewLayout", "CheckResult")
        fun setData(position: Int, activity: Activity, movieList: List<MovieModel>) {
//            notificationManager =
//                activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


            Glide.with(activity).load(movieList[position].imageUrl).into(mBinding.ivMovie)
            mBinding.tvMovieName.text = movieList[position].name
            mBinding.tvMovieDesc.text = movieList[position].desc



            mBinding.cvMovie.setOnClickListener {

                applyImageUrl(movieList[position].imageUrl)
                NotificationHelper(activity,position,bitmapImage).Notification()
            }
        }
        fun applyImageUrl(imageUrl: String) = runBlocking {
            val url = URL(imageUrl)
            withContext(Dispatchers.IO) {
                try {
                    val input = url.openStream()
                    BitmapFactory.decodeStream(input)
                } catch (e: IOException) {
                    null
                }
            }?.let { bitmap ->
                bitmapImage = bitmap
            }
        }



    }
}