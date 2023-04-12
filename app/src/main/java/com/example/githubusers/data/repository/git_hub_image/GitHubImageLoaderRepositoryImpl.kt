package com.example.githubusers.data.repository.git_hub_image

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.domain.repository.git_hub_image.IGitHubImageLoaderRepository
import com.example.githubusers.domain.repository.storage_directory.IImageLoaderStorageDirectory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class GitHubImageLoaderRepositoryImpl: IGitHubImageLoaderRepository {

    init {
        GitHubUsersApp.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoaderStorageDirectory: IImageLoaderStorageDirectory

    @Inject
    lateinit var remoteDataSourceGitHubImage: RemoteDataSourceGitHubImage

    override fun loadGitHubImage(): Single<ResponseBody> =
        remoteDataSourceGitHubImage.callAPIGitHubImage()

    override fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable =
        imageLoaderStorageDirectory.saveGitHubImageJpg(responseBodyGitHubImage)
}