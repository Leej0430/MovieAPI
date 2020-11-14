package com.example.movieapitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailFragment:Fragment() {
    companion object{
        fun newInstance(movieItem: MovieItem): DetailFragment{
            return DetailFragment().also{
                it.arguments = Bundle().also {
                    it.putParcelable(KEY_MOVIE_ITEM,movieItem)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.fragment_display_detail,
            container,
            false
        )
         arguments?.getParcelable<MovieItem>(KEY_MOVIE_ITEM)?.let {
             view.findViewById<TextView>(R.id.tv_movie_title).text=
                 it.title
             view.findViewById<TextView>(R.id.tv_movie_release_year).text=
                 it.releaseYear.toString()
             view.findViewById<TextView>(R.id.tv_movie_genre).text=
                 it.genre.toString()
             view.findViewById<ProgressBar>(R.id.rating_movie).progress=
                 it.rating.toInt()
             view.findViewById<ImageView>(R.id.iv_movie_poster)
                 .showPoster(it.image)
         }
    return view
    }
}

