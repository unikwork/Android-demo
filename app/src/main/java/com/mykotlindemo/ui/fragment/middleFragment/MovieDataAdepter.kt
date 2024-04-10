package com.mykotlindemo.ui.fragment.middleFragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mykotlindemo.databinding.MovieListDesignBinding
import com.mykotlindemo.ui.roomdatabse.DataEntity

class MovieDataAdepter(val activity: Activity) : RecyclerView.Adapter<MovieDataAdepter.ViewData>() {

    private var movieList: MutableList<DataEntity> = ArrayList<DataEntity>()
    public var DeleteListener: deleteListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val mBinding = MovieListDesignBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewData(mBinding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.setData(position, movieList, activity, DeleteListener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(movieList: MutableList<DataEntity>) {
        movieList.clear()
        this.movieList = movieList
        notifyDataSetChanged()
    }

    fun setOnClickListener(deleteListener: deleteListener) {
        DeleteListener = deleteListener
    }

    class ViewData(itemView: MovieListDesignBinding) : RecyclerView.ViewHolder(itemView.root) {
        var mBinding: MovieListDesignBinding

        init {
            mBinding = itemView
        }

        fun setData(
            position: Int,
            movieList: List<DataEntity>,
            activity: Activity,
            DeleteListener: deleteListener?
        ) {
            Glide.with(activity).load(movieList[position].movieUrl).into(mBinding.ivMovie)
            mBinding.tvMovieName.text = movieList[position].movieName
            mBinding.tvMovieDesc.text = movieList[position].movieDescription
            mBinding.cvDelete.setOnClickListener {
                DeleteListener!!.setOnClickListener(position)
            }
        }

    }

    public interface deleteListener {
        fun setOnClickListener(position: Int)
    }
}