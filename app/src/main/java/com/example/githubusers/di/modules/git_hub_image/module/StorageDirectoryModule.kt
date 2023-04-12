package com.example.githubusers.di.modules.git_hub_image.module

import com.example.githubusers.data.repository.storage_directory.ImageStorageDirectory
import com.example.githubusers.di.modules.git_hub_image.GitHubImageScope
import com.example.githubusers.domain.repository.storage_directory.IImageStorageDirectory
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class StorageDirectoryModule {

    @GitHubImageScope
    @Named("filePng")
    @Provides
    fun filePng(@Named("path") path: File): File = File(path, GIT_HUB_IMAGE_PNG)

    @GitHubImageScope
    @Provides
    fun imageStorageDirectory(
        @Named("fileJpg") fileGpg: File,
        @Named("filePng") filePng: File
    ): IImageStorageDirectory = ImageStorageDirectory (fileGpg, filePng)

    companion object {
        const val GIT_HUB_IMAGE_PNG = "git_hub.png"
    }

}