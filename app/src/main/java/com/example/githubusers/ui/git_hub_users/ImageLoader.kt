package com.example.githubusers.ui.git_hub_users

import android.graphics.Bitmap

interface ImageLoader<T> {
    fun loadImageInto(url:String, container: T)
    fun loadImageInto (bitmap: Bitmap, container: T)
    fun loadImageInto (resourceId: Int, container: T)
}