package com.example.githubusers.ui.image

import android.graphics.Bitmap

interface IImageLoader<T> {
    fun loadImageInto(url:String, container: T)
    fun loadImageInto (bitmap: Bitmap, container: T)
    fun loadImageInto (resourceId: Int, container: T)
}