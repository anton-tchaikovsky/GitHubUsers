package com.example.githubusers.di.git_hub_app.modules

import androidx.appcompat.widget.AppCompatImageView
import com.example.githubusers.ui.image.GlideImageLoader
import com.example.githubusers.ui.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<AppCompatImageView> = GlideImageLoader()
}