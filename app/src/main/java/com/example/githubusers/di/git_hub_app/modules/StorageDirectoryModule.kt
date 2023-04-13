package com.example.githubusers.di.git_hub_app.modules

import android.os.Environment
import com.example.githubusers.data.repository.storage_directory.ImageLoaderStorageDirectory
import com.example.githubusers.domain.repository.storage_directory.IImageLoaderStorageDirectory
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageDirectoryModule {

    @Named("path")
    @Provides
    fun path(): File = Environment.getExternalStorageDirectory()

    @Named("fileJpg")
    @Provides
    fun fileJpg(@Named("path") path: File): File = File(path, GIT_HUB_IMAGE_JPG)

    @Singleton
    @Provides
    fun imageLoaderStorageDirectory(
        @Named("fileJpg") fileGpg: File
    ): IImageLoaderStorageDirectory = ImageLoaderStorageDirectory (fileGpg)

    companion object {
        const val GIT_HUB_IMAGE_JPG = "git_hub.jpg"
    }

}