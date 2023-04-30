package com.devatrii.dailynews2023

import android.content.Context
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun loadImageWithGlide(url: String, imageView: ImageView, context: Context) {
    Glide.with(context)
        .load(url)
        .transition(withCrossFade())
        .thumbnail(0.5f)
        .into(imageView)
}

fun showMessage(context: Context, message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateFormat(dateTimeString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM,d,yyyy", Locale.ENGLISH)
    val dateTime = LocalDateTime.parse(dateTimeString, inputFormatter)
    return outputFormatter.format(dateTime)
}