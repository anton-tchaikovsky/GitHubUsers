package com.example.githubusers.di.modules

import android.os.Environment
import com.example.githubusers.data.repository.storage_directory.ImageStorageDirectory
import com.example.githubusers.domain.repository.storage_directory.IImageStorageDirectory
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

    @Named("filePng")
    @Provides
    fun filePng(@Named("path") path: File): File = File(path, GIT_HUB_IMAGE_PNG)

    @Singleton
    @Provides
    fun imageStorageDirectory(
        @Named("fileJpg") fileGpg: File,
        @Named("filePng") filePng: File
    ): IImageStorageDirectory = ImageStorageDirectory (fileGpg, filePng)

    companion object {
        const val GIT_HUB_IMAGE_JPG = "git_hub.jpg"
        const val GIT_HUB_IMAGE_PNG = "git_hub.png"
    }

}