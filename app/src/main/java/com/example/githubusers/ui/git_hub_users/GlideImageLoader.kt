package com.example.githubusers.ui.git_hub_users


import android.graphics.Bitmap
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class GlideImageLoader:ImageLoader<AppCompatImageView> {
    override fun loadImageInto(url: String, container: AppCompatImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)

    }

    override fun loadImageInto(bitmap: Bitmap, container: AppCompatImageView) {
        Glide.with(container.context)
            .load(bitmap)
            .into(container)
    }

    override fun loadImageInto(resourceId: Int, container: AppCompatImageView) {
        Glide.with(container.context)
            .load(resourceId)
            .into(container)
    }
}