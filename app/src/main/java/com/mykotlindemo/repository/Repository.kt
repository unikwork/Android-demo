package com.mykotlindemo.repository

import androidx.lifecycle.MutableLiveData
import com.mykotlindemo.model.MovieModel
import com.mykotlindemo.retrofitApi.ApiClient
import com.mykotlindemo.retrofitApi.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    var movieList = MutableLiveData<List<MovieModel>>()
    private var apiInterface: ApiInterface? = null
    private var apiClient: ApiClient? = null

    fun getMovieResult(): MutableLiveData<List<MovieModel>> {
        apiClient = ApiClient()
        apiInterface = apiClient!!.getMovie()!!.create(ApiInterface::class.java)
        apiInterface!!.getMovies().enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                movieList.value = response.body()

            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return movieList
    }
}