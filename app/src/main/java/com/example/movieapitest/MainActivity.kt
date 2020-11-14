package com.example.movieapitest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
const val KEY_MOVIE_ITEM: String="MainActivity_KEY_MOVIE_ITEM"
class MainActivity : AppCompatActivity() {

    lateinit var  recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view_movies)
        getMovies()
    }
    private fun getMovies(){
        MoviesApi.initRetrofit().getMovies().enqueue(
            object: Callback<List<MovieItem>>{
                override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,
                        "Error",
                        Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<List<MovieItem>>,
                    response: Response<List<MovieItem>>
                ) {
                   if(response.isSuccessful){
                       response.body()?.let {
                           recyclerView.layoutManager=
                               GridLayoutManager(this@MainActivity,
                                   2)
                           recyclerView.adapter=
                               MovieAdapter(it,
                                   this@MainActivity::openActivityDetails)

                       }
                   }
                }
            }
        )
    }
    private fun getMContext() = this.baseContext
    private fun openActivityDetails(movieItem: MovieItem){
        if(findViewById<FrameLayout>(R.id.fragment_detail_container)==null) {
            val intent = Intent()
            intent.setClass(this, DetailActivity::class.java)
            intent.putExtra(KEY_MOVIE_ITEM, movieItem)
            startActivity(intent)
        }else{
        val fragment = DetailFragment.newInstance(movieItem)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_detail_container,
            fragment)
            .commit()
        }
    }
}