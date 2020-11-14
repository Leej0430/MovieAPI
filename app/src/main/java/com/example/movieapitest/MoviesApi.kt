package com.example.movieapitest

import android.telecom.Call
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.androidhive.info/
// json/movies.json

interface MoviesApi{
    @GET("json/movies.json")
    fun getMovies(): retrofit2.Call<List<MovieItem>>

    companion object{
        fun initRetrofit(): MoviesApi{
            return Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}

//@GET("URL")
//fun getBOOKS(
//        @Query("q") input:String
//
//) Call<BooksResponse>




//Retrofit steps
//1.- Library Dependency
//2.- interface (HTTP Methods)
//3.- Create the Retrofit Object
//4.- Enqueue the process...