package com.mykotlindemo.retrofitApi

import com.mykotlindemo.model.MovieModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("movielist.json")
    fun getMovies():retrofit2.Call<List<MovieModel>>
}