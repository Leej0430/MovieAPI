package com.example.movieapitest

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.showPoster(url:String){
    Picasso.get().load(url).into(this)
}